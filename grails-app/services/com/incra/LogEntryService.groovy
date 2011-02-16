package com.incra

import java.sql.Timestamp 
import com.incra.User

/**
 * The <i>LogEntryService</i> provides methods to write to the LogEntry table, and access routines
 * to query against it.
 * 
 * @author Jeffrey Risberg
 * @since 11/21/10
 */
class LogEntryService {
  
  /**
   * Generate a new LogEntry.  A log entry always has a key and a severity (importance), and may be
   * related to a user.
   */
  public void publish(User user, String keyLabel, LogEntrySeverity severity, List<String> parameters) {
    LogEntryKey logEntryKey = LogEntryKey.findByLabel(keyLabel)
    
    if (logEntryKey == null) {
      logEntryKey = new LogEntryKey(label: keyLabel)
      logEntryKey.save(flush: true)
    }
    
    LogEntry logEntry = new LogEntry(user: user, key: logEntryKey, severity: severity)
    
    for (int i = 0; i < Math.min(7, parameters.size()); i++) {
      if (i == 0) logEntry.parameter1 = shortText(parameters[i], 240)
      if (i == 1) logEntry.parameter2 = shortText(parameters[i], 240)
      if (i == 2) logEntry.parameter3 = shortText(parameters[i], 240)
      if (i == 3) logEntry.parameter4 = shortText(parameters[i], 240)
      if (i == 4) logEntry.parameter5 = shortText(parameters[i], 240)
      if (i == 5) logEntry.parameter6 = shortText(parameters[i], 240)
      if (i == 6) logEntry.parameter7 = shortText(parameters[i], 240)
    }
    logEntry.setStartTimestamp(new Timestamp(System.currentTimeMillis()))
    Object result = logEntry.save(flush:true)
  }
  
  private String shortText(String value, int maxLength) {
    if (value) {
      int length = value.length()
      if (length > maxLength) {
        length = maxLength-5
        return value.substring(0, length) + "..."
      } else {
        return value
      }
    } else {
      return value
    }
  }
}