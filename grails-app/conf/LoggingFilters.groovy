import com.incra.User
import com.incra.domain.LogEntrySeverity

/**
 * The <i>LoggingFilters</i> class causes recording of an Http request organized by Grails
 * into a controller and action into a LogEntry record whose content is configured by a
 * loggingInformation specification on the controller classes.
 *
 * @author Jeff Risberg, Spoorthy Ananthaiah
 * @since 10/16/10
 */
class LoggingFilters {

	def logEntryService

	def filters = {
		all(controller:'*', action:'*') {
			before = {
				// Generate default values for keyLabel and logEntrySeverity
				String keyLabel = null
				LogEntrySeverity logEntrySeverity = LogEntrySeverity.MEDIUM
				def loggingInstruction = null

				// Look up the controller via the controllerName, then fetch the logging instructions
				String firstLetter = controllerName?.substring(0, 1)?.toUpperCase()
				String restLetters = controllerName?.substring(1)
				String controllerFullName = "com.incra.${firstLetter}${restLetters}Controller"
				println "--->" + controllerFullName + " " + actionName

				try {
					Class theClass = grailsApplication.getClassForName(controllerFullName)
					def loggingInstructions = theClass.loggingInstructions
					if (loggingInstructions) {
						loggingInstruction = loggingInstructions[actionName ? actionName : "index"]

						if (loggingInstruction) {
							if (loggingInstruction.key) keyLabel = loggingInstruction.key
							if (loggingInstruction.severity) logEntrySeverity = loggingInstruction.severity
						}
					}
				} catch (Exception e) {
					// this simply means that logging instructions were not available
				}

				if (keyLabel && loggingInstruction) {
					User loginUser = session.loginUser

					def challenge = null
					def group = null
					def activity = null
					List<String> parameters = new ArrayList<String>()

					def parameterInstructions = loggingInstruction['parameters']
					if (parameterInstructions) {

						for (Object parameterInstruction : parameterInstructions) {
							def verb = parameterInstruction.mode
							def value = null

							try {
								switch (verb.toLowerCase()) {
									case 'fetch':
										if (true) {
											def paramName = parameterInstruction.param
											def entityClassName = parameterInstruction.entityClass
											if (paramName && entityClassName) {
												long id = params[paramName] as Long
												Class theClass = grailsApplication.getClassForName(entityClassName)
												def record = theClass.get(id)

												value = record.toString()
											}
										}
										break;
									case 'direct':
										if (true) {
											def paramName = parameterInstruction.param
											value = params[paramName]
										}
										break;
									case 'header':
										if (true) {
											def fieldName = parameterInstruction.field
											value = request.getHeader(fieldName)
										}
										break;
									case 'remote-addr':
										if (true) {
											value = request.getRemoteAddr()
										}
										break;
									case 'constant':
										if (true) {
											value = parameterInstruction.value
										}
										break;
									default:
										log.info("Unknown verb ${verb} in logging instructions for ${controllerName}");
								}
							}
							catch (Exception e) {
								log.error(e.getMessage())
								value = e.getMessage()
							}
							if (value)
								parameters.add(value)
						}
					}

					logEntryService.publish(loginUser, group, challenge, activity, keyLabel, logEntrySeverity, parameters)
				}
			}
			after = {
				String firstLetter = controllerName?.substring(0, 1)?.toUpperCase()
				String restLetters = controllerName?.substring(1)
				String controllerFullName = "com.incra.${firstLetter}${restLetters}Controller"
				println "<---" + controllerFullName + " " + actionName
			}
			afterView = {
			}
		}
	}
}
