package com.incra

class TransactionCategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [transactionCategoryInstanceList: TransactionCategory.list(params), transactionCategoryInstanceTotal: TransactionCategory.count()]
    }

    def create = {
        def transactionCategoryInstance = new TransactionCategory()
        transactionCategoryInstance.properties = params
        return [transactionCategoryInstance: transactionCategoryInstance]
    }

    def save = {
        def transactionCategoryInstance = new TransactionCategory(params)
        if (transactionCategoryInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'transactionCategory.label', default: 'TransactionCategory'), transactionCategoryInstance.id])}"
            redirect(action: "show", id: transactionCategoryInstance.id)
        }
        else {
            render(view: "create", model: [transactionCategoryInstance: transactionCategoryInstance])
        }
    }

    def show = {
        def transactionCategoryInstance = TransactionCategory.get(params.id)
        if (!transactionCategoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'transactionCategory.label', default: 'TransactionCategory'), params.id])}"
            redirect(action: "list")
        }
        else {
            [transactionCategoryInstance: transactionCategoryInstance]
        }
    }

    def edit = {
        def transactionCategoryInstance = TransactionCategory.get(params.id)
        if (!transactionCategoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'transactionCategory.label', default: 'TransactionCategory'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [transactionCategoryInstance: transactionCategoryInstance]
        }
    }

    def update = {
        def transactionCategoryInstance = TransactionCategory.get(params.id)
        if (transactionCategoryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (transactionCategoryInstance.version > version) {
                    
                    transactionCategoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'transactionCategory.label', default: 'TransactionCategory')] as Object[], "Another user has updated this TransactionCategory while you were editing")
                    render(view: "edit", model: [transactionCategoryInstance: transactionCategoryInstance])
                    return
                }
            }
            transactionCategoryInstance.properties = params
            if (!transactionCategoryInstance.hasErrors() && transactionCategoryInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'transactionCategory.label', default: 'TransactionCategory'), transactionCategoryInstance.id])}"
                redirect(action: "show", id: transactionCategoryInstance.id)
            }
            else {
                render(view: "edit", model: [transactionCategoryInstance: transactionCategoryInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'transactionCategory.label', default: 'TransactionCategory'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def transactionCategoryInstance = TransactionCategory.get(params.id)
        if (transactionCategoryInstance) {
            try {
                transactionCategoryInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'transactionCategory.label', default: 'TransactionCategory'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'transactionCategory.label', default: 'TransactionCategory'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'transactionCategory.label', default: 'TransactionCategory'), params.id])}"
            redirect(action: "list")
        }
    }
}
