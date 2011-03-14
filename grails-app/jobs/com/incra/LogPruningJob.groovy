package com.incra


import org.hibernate.Query

class LogPruningJob  {
    static final long CONST_minuteInMillis = 60000l;
    static final long CONST_hourInMillis = 3600000l;
    static final long CONST_fiveMinuteInMillis = 300000l;

    // Make sure jobs do not run concurrently
    def concurrent = false

    public boolean test = false;

    static triggers = {
        cron name: 'pruneLog', cronExpression: "0 0 3 * * ?" // Fire at 3am every day
    }

    def logEntryService
    def sessionFactory
    def serverTimeService

    def execute() {
      synchronized (JobLock.lock) {
        log.info("====================");

        def timeInfo = serverTimeService.getServerNowInfo();
        def serverNow = timeInfo.serverNowCal;
        def serverNowMidnight = timeInfo.serverDayCal;

        log.info("LogPruningJob:          " + serverNow.getTime() + " day=" + serverNowMidnight.getTime());

        def session = sessionFactory.currentSession

        // Prune all the log entries associated with a user according to the longest
        // retention time of each of their groups
        def allUsers = ViveUser.executeQuery("select id from ViveUser")
        Query maxDaysQuery = session.createQuery('select max(logRetentionDays) from UserGroup where id in (select group.id from GroupMember where user.id = :user_id) and parent is null')
        Query deleteLogEntryQuery = session.createQuery('delete from LogEntry where startTimestamp < :date and user.id = :user_id')
        int numRows = 0
        allUsers.each {long id ->
          maxDaysQuery.setLong("user_id", id)
          def maxDays = maxDaysQuery.uniqueResult()
          if (!maxDays)
            maxDays = UserGroup.CONST_logRetentionDaysDefault
          Date date = new Date() - maxDays
          deleteLogEntryQuery.setDate("date", date)
          deleteLogEntryQuery.setLong("user_id", id)
          numRows += deleteLogEntryQuery.executeUpdate()
        }
        log.info("Pruning deleted ${numRows} user-related log entries ")

        // Prune all the log entries not associated with a user according to the default
        // retention time
        deleteLogEntryQuery = session.createQuery('delete from LogEntry where startTimestamp < :date and user.id is null')
        Date date = new Date() - UserGroup.CONST_logRetentionDaysDefault
        deleteLogEntryQuery.setDate("date", date)
        numRows = deleteLogEntryQuery.executeUpdate()
        log.info("Pruning deleted ${numRows} non-user log entries")

        // Flush the Hibernate cache to clean up this thread's memory usage
        sessionFactory.getCurrentSession().flush()
        sessionFactory.getCurrentSession().clear()

        log.info("")
        log.info("Done LogPruningJob:      " + serverNow.getTime() + " day=" + serverNowMidnight.getTime());
        log.info("====================");
      }
    }
}
