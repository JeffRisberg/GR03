package com.incra

import com.incra.pojo.DisplayFilterPojo;

/**
 * The <i>FlowController</i> class was based on generated code, but has changed
 * greatly since to use ownership information about Accounts and Flows.
 * 
 * @author Jeffrey Risberg
 * @since 10/12/10
 */
class FlowController {
	def pageFrameworkService
	def accountService
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index = {
		redirect(action: "list", params: params)
	}
	
	/**
	 * Show a list of Flows for the current user
	 */
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		User currentUser = pageFrameworkService.getCurrentUser(session)		
		GeoScale geoScale = GeoScale.findByScale(2)
		List<Account> validAccounts = accountService.getValidAccounts(currentUser, geoScale)
		
		Preferences prefs = Preferences.get(1)
		List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
		DisplayFilterPojo dfp;
		
		dfp = new DisplayFilterPojo(name: 'fromAccount', label: 'From Facility:', type: 'Select',
		values: validAccounts)
		filters.add(dfp)
		
		dfp = new DisplayFilterPojo(name: 'toAccount', label: 'To Facility:', type: 'Select',
		values: validAccounts)
		filters.add(dfp)
		
		// Keep these values so we can rerender while maintaining filter value settings
		flash.fromAccount = params.fromAccount
		flash.toAccount = params.toAccount
		
		def criteria = Flow.createCriteria()
		def query = {
			and {
				if (params.fromAccount) {
					def selectedAccount = Account.get(Integer.parseInt(params.fromAccount))
					if (selectedAccount) {
						eq('fromAccount', selectedAccount)
					}
				}
				
				if (params.toAccount) {
					def selectedAccount = Account.get(Integer.parseInt(params.toAccount))
					if (selectedAccount) {
						eq('toAccount', selectedAccount)
					}
				}
				if (currentUser != null) {
					or {
						isNull('user')
						eq('user', currentUser)
					}
				}
				if (currentUser == null) {
					isNull('user')
				}
			}
		}
		
		def results = criteria.list(params, query)
		
		[filters: filters, flowInstanceList: results, flowInstanceTotal: results.totalCount]
	}
	
	/**
	 * Admin function
	 */
	def listAll = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		User currentUser = pageFrameworkService.getCurrentUser(session)
		GeoScale geoScale = GeoScale.findByScale(2)
		List<Account> validAccounts = accountService.getValidAccounts(currentUser, geoScale)
		
		Preferences prefs = Preferences.get(1)
		List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
		DisplayFilterPojo dfp;
		
		dfp = new DisplayFilterPojo(name: 'fromAccount', label: 'From Facility:', type: 'Select',
		values: validAccounts)
		filters.add(dfp)
		
		dfp = new DisplayFilterPojo(name: 'toAccount', label: 'To Facility:', type: 'Select',
		values: validAccounts)
		filters.add(dfp)
		
		// Keep these values so we can rerender while maintaining filter value settings
		flash.fromAccount = params.fromAccount
		flash.toAccount = params.toAccount
		
		def criteria = Flow.createCriteria()
		def query = {
			and {
				if (params.fromAccount) {
					def selectedAccount = Account.get(Integer.parseInt(params.fromAccount))
					if (selectedAccount) {
						eq('fromAccount', selectedAccount)
					}
				}
				
				if (params.toAccount) {
					def selectedAccount = Account.get(Integer.parseInt(params.toAccount))
					if (selectedAccount) {
						eq('toAccount', selectedAccount)
					}
				}
			}
		}
		
		def results = criteria.list(params, query)
		
		[filters: filters, flowInstanceList: results, flowInstanceTotal: results.totalCount]
	}
	
	/**
	 * Show one Flow
	 */
	def show = {
		def flowInstance = Flow.get(params.id)
		if (!flowInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])}"
			redirect(action: "list")
		}
		else {
			[flowInstance: flowInstance]
		}
	}
	
	def create = {
		User currentUser = pageFrameworkService.getCurrentUser(session)
		GeoScale geoScale = GeoScale.findByScale(2)
		
		def flowInstance = new Flow()
		flowInstance.properties = params
		return [flowInstance: flowInstance, 
			accounts: accountService.getValidAccounts(currentUser, geoScale)]
	}
	
	def save = {
		User currentUser = pageFrameworkService.getCurrentUser(session)
		GeoScale geoScale = GeoScale.findByScale(2)
		
		def flowInstance = new Flow(params)
		
		flowInstance.user = currentUser
		
		if (flowInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'flow.label', default: 'Flow'), flowInstance.id])}"
			redirect(action: "show", id: flowInstance.id)
		}
		else {
			render(view: "create", model: [flowInstance: flowInstance,
				accounts: accountService.getValidAccounts(currentUser, geoScale)])
		}
	}
	
	def edit = {
		User currentUser = pageFrameworkService.getCurrentUser(session)
		GeoScale geoScale = GeoScale.findByScale(2)
		
		def flowInstance = Flow.get(params.id)
		if (!flowInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [flowInstance: flowInstance,
				accounts: accountService.getValidAccounts(currentUser, geoScale)]
		}
	}
	
	def update = {
		User currentUser = pageFrameworkService.getCurrentUser(session)
		GeoScale geoScale = GeoScale.findByScale(2)
		
		def flowInstance = Flow.get(params.id)
		if (flowInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (flowInstance.version > version) {
					
					flowInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'flow.label', default: 'Flow')] as Object[], "Another user has updated this Flow while you were editing")
					render(view: "edit", model: [flowInstance: flowInstance,
						accounts: accountService.getValidAccounts(currentUser, geoScale)])
					return
				}
			}
			flowInstance.properties = params
			if (!flowInstance.hasErrors() && flowInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'flow.label', default: 'Flow'), flowInstance.id])}"
				redirect(action: "show", id: flowInstance.id)
			}
			else {
				render(view: "edit", model: [flowInstance: flowInstance,
					accounts: accountService.getValidAccounts(currentUser, geoScale)])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def delete = {
		def flowInstance = Flow.get(params.id)
		if (flowInstance) {
			try {
				flowInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'flow.label', default: 'Flow'), params.id])}"
			redirect(action: "list")
		}
	}
}
