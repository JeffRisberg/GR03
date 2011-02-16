package com.incra

import com.incra.pojo.DisplayFilterPojo;


/**
 * The <i>TransactionController</i> is based on generated code.
 *
 * @author Jeffrey Risberg
 * @since 10/10/10
 */
class TransactionController {
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index = {
		redirect(action: "list", params: params)
	}
	
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		Preferences prefs = Preferences.get(1)
		List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
		DisplayFilterPojo dfp;
		
		dfp = new DisplayFilterPojo(name: 'account', label: 'Account:', type: 'Select',
		values: Account.list())
		filters.add(dfp)
		
		dfp = new DisplayFilterPojo(name: 'transactionType', label: 'Transaction Type:', type: 'Select',
		values: TransactionType.list())
		filters.add(dfp)
		
		// Keep these values so we can rerender while maintaining filter value settings
		flash.account = params.account
		flash.transactionType = params.transactionType
		
		def criteria = Transaction.createCriteria()
		def query = {
			and {
				if (params.account) {
					def selectedAccount = Account.get(Integer.parseInt(params.account))
					if (selectedAccount) {
						eq('account', selectedAccount)
					}
				}
				
				if (params.transactionType) {
					def selectedTransactionType = TransactionType.get(Integer.parseInt(params.transactionType))
					if (selectedTransactionType) {
						eq('type', selectedTransactionType)
					}
				}
			}
		}
		
		def results = criteria.list(params, query)
		
		[filters: filters, transactionInstanceList: results, transactionInstanceTotal: results.totalCount]
	}
	
	def create = {
		def transactionInstance = new Transaction()
		transactionInstance.properties = params
		return [transactionInstance: transactionInstance]
	}
	
	def save = {
		def transactionInstance = new Transaction(params)
		if (transactionInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'transaction.label', default: 'Transaction'), transactionInstance.id])}"
			redirect(action: "show", id: transactionInstance.id)
		}
		else {
			render(view: "create", model: [transactionInstance: transactionInstance])
		}
	}
	
	def show = {
		def transactionInstance = Transaction.get(params.id)
		if (!transactionInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])}"
			redirect(action: "list")
		}
		else {
			[transactionInstance: transactionInstance]
		}
	}
	
	def edit = {
		def transactionInstance = Transaction.get(params.id)
		if (!transactionInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [transactionInstance: transactionInstance]
		}
	}
	
	def update = {
		def transactionInstance = Transaction.get(params.id)
		if (transactionInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (transactionInstance.version > version) {
					
					transactionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'transaction.label', default: 'Transaction')] as Object[], "Another user has updated this Transaction while you were editing")
					render(view: "edit", model: [transactionInstance: transactionInstance])
					return
				}
			}
			transactionInstance.properties = params
			if (!transactionInstance.hasErrors() && transactionInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'transaction.label', default: 'Transaction'), transactionInstance.id])}"
				redirect(action: "show", id: transactionInstance.id)
			}
			else {
				render(view: "edit", model: [transactionInstance: transactionInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def delete = {
		def transactionInstance = Transaction.get(params.id)
		if (transactionInstance) {
			try {
				transactionInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])}"
			redirect(action: "list")
		}
	}
}
