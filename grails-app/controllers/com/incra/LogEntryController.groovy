package com.incra

import com.incra.domain.LogEntrySeverity
import com.incra.pojo.DisplayFilterPojo

/**
 * The <i>LogEntryController</i> is a controller that offers filtered lists of log activity.
 * 
 * @author Jeffrey Risberg
 * @since 10/15/10
 */
class LogEntryController {

	def index = {
		redirect(action: "list", params: params)
	}

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		if (!params.sort) params.sort = "startTimestamp"
		if (!params.order) params.order = "desc"

		List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
		DisplayFilterPojo dfp;

		dfp = new DisplayFilterPojo(name: 'username', label: 'User:', type: 'String')
		filters.add(dfp)
		dfp = new DisplayFilterPojo(name: 'keyId', label: 'Key:', type: 'Select',
				values: LogEntryKey.findAll())
		filters.add(dfp)
		dfp = new DisplayFilterPojo(name: 'severity', label: 'Importance:', type: 'Enumeration',
				values: LogEntrySeverity.selectAll())
		filters.add(dfp)

		// Keep these values so we can rerender while maintaining filter value settings
		flash.userName = params.userName
		flash.keyId = params.keyId
		flash.serverity = params.severity

		List<LogEntrySeverity> severities = new ArrayList<LogEntrySeverity>()

		if (params.severity && params.severity.trim()) {
			LogEntrySeverity minLes = LogEntrySeverity.findByName(params.severity)

			if (minLes) {
				for (LogEntrySeverity les : LogEntrySeverity.selectAll()) {
					if (les.getLevel() >= minLes.getLevel()) {
						severities.add(les)
					}
				}
			}
		}

		def criteria = LogEntry.createCriteria()
		def query = {
			and {
				user {
					if (params.username && params.username.trim()) {
						like("username", '%' + params.username + '%')
					}
				}
				if (params.keyId) {
					def selectedLogEntryKey = LogEntryKey.get(Integer.parseInt(params.keyId))
					if (selectedLogEntryKey) {
						eq('key', selectedLogEntryKey)
					}
				}
				if (params.severity && params.severity.trim()) {
					'in'('severity', severities)
				}
			}
		}

		def results = criteria.list(params, query)

		[filters: filters, logEntryInstanceList: results, logEntryInstanceTotal: results.totalCount]
	}

	def show = {
		def logEntryInstance = LogEntry.get(params.id)
		if (!logEntryInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'logEntry.label', default: 'LogEntry'), params.id])}"
			redirect(action: "list")
		}
		else {
			[logEntryInstance: logEntryInstance]
		}
	}

	def clear = {
		LogEntry.executeUpdate("delete LogEntry")

		redirect(action: "list")
	}
}
