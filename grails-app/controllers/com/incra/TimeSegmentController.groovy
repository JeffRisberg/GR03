package com.incra

import com.incra.pojo.DisplayFilterPojo;

/**
 * The <i>TimeSegmentController</i> is based on generated code.
 * 
 * @author Jeffrey Risberg
 * @since 10/11/10
 */
class TimeSegmentController {
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index = {
		redirect(action: "list", params: params)
	}
	
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
		DisplayFilterPojo dfp;
		
		dfp = new DisplayFilterPojo(name: 'timeScale', label: 'Time Scale:', type: 'Select',
		values: TimeScale.list())
		filters.add(dfp)
		
		// Keep these values so we can rerender while maintaining filter value settings
		flash.timeScale = params.timeScale
		
		def criteria = TimeSegment.createCriteria()
		def query = {
			and {
				if (params.timeScale) {
					def selectedTimeScale = TimeScale.get(Integer.parseInt(params.timeScale))
					if (selectedTimeScale) {
						eq('timeScale', selectedTimeScale)
					}
				}
			}
		}
		
		def results = criteria.list(params, query)
		
		[filters: filters, timeSegmentInstanceList: results, timeSegmentInstanceTotal: results.totalCount]
	}
	
	def create = {
		def timeSegmentInstance = new TimeSegment()
		timeSegmentInstance.properties = params
		return [timeSegmentInstance: timeSegmentInstance]
	}
	
	def save = {
		def timeSegmentInstance = new TimeSegment(params)
		if (timeSegmentInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'timeSegment.label', default: 'TimeSegment'), timeSegmentInstance.id])}"
			redirect(action: "show", id: timeSegmentInstance.id)
		}
		else {
			render(view: "create", model: [timeSegmentInstance: timeSegmentInstance])
		}
	}
	
	def show = {
		def timeSegmentInstance = TimeSegment.get(params.id)
		if (!timeSegmentInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'timeSegment.label', default: 'TimeSegment'), params.id])}"
			redirect(action: "list")
		}
		else {
			[timeSegmentInstance: timeSegmentInstance]
		}
	}
	
	def edit = {
		def timeSegmentInstance = TimeSegment.get(params.id)
		if (!timeSegmentInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'timeSegment.label', default: 'TimeSegment'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [timeSegmentInstance: timeSegmentInstance]
		}
	}
	
	def update = {
		def timeSegmentInstance = TimeSegment.get(params.id)
		if (timeSegmentInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (timeSegmentInstance.version > version) {
					
					timeSegmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'timeSegment.label', default: 'TimeSegment')] as Object[], "Another user has updated this TimeSegment while you were editing")
					render(view: "edit", model: [timeSegmentInstance: timeSegmentInstance])
					return
				}
			}
			timeSegmentInstance.properties = params
			if (!timeSegmentInstance.hasErrors() && timeSegmentInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'timeSegment.label', default: 'TimeSegment'), timeSegmentInstance.id])}"
				redirect(action: "show", id: timeSegmentInstance.id)
			}
			else {
				render(view: "edit", model: [timeSegmentInstance: timeSegmentInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'timeSegment.label', default: 'TimeSegment'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def delete = {
		def timeSegmentInstance = TimeSegment.get(params.id)
		if (timeSegmentInstance) {
			try {
				timeSegmentInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'timeSegment.label', default: 'TimeSegment'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'timeSegment.label', default: 'TimeSegment'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'timeSegment.label', default: 'TimeSegment'), params.id])}"
			redirect(action: "list")
		}
	}
}
