package com.incra

/**
 * The <i>TickleDatabaseJob</i> is run every 1 hour to make a fetch
 * from the database and hence keep the connection open.  Notice that 
 * the caches are being bypassed so that we really use the connection.
 * 
 * @author Jeff Risberg
 * @since 12/08/10
 */
class TickleDatabaseJob {
	def timeout = 1 * 60 * 60 * 1000 // execute job once in 1 hour
	
	def sessionFactory
	
	def execute() {
		def session = sessionFactory.currentSession
		
		def query = session.createSQLQuery(
				"select sql_no_cache language.id from language")
		query.list()
		println new Date().toString() + " database connection tickle"
	}
}
