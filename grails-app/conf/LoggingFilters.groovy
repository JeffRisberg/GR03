import com.incra.LogEntry 

/**
 * The <i>LoggingFilters</i> class causes recording of the user/controller/action information into a
 * LogEntry record.
 *  
 * @author Jeffrey Risberg
 * @since 10/16/10
 */
class LoggingFilters {
	
	def filters = {
		all(controller:'*', action:'*') {
			before = {
				// Don't log requests to view the log
				if (controllerName != "logEntry") {
					String parameters = ""
					LogEntry logEntry = new LogEntry(controllerName: controllerName, actionName: actionName, 
					parameters: parameters)
					logEntry.save()
				}
			}
			after = {
			}
			afterView = {
			}
		}
	}
}
