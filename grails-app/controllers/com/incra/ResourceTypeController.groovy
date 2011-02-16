package com.incra

class ResourceTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [resourceTypeInstanceList: ResourceType.list(params), resourceTypeInstanceTotal: ResourceType.count()]
    }

    def create = {
        def resourceTypeInstance = new ResourceType()
        resourceTypeInstance.properties = params
        return [resourceTypeInstance: resourceTypeInstance]
    }

    def save = {
        def resourceTypeInstance = new ResourceType(params)
        if (resourceTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'resourceType.label', default: 'ResourceType'), resourceTypeInstance.id])}"
            redirect(action: "show", id: resourceTypeInstance.id)
        }
        else {
            render(view: "create", model: [resourceTypeInstance: resourceTypeInstance])
        }
    }

    def show = {
        def resourceTypeInstance = ResourceType.get(params.id)
        if (!resourceTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'resourceType.label', default: 'ResourceType'), params.id])}"
            redirect(action: "list")
        }
        else {
            [resourceTypeInstance: resourceTypeInstance]
        }
    }

    def edit = {
        def resourceTypeInstance = ResourceType.get(params.id)
        if (!resourceTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'resourceType.label', default: 'ResourceType'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [resourceTypeInstance: resourceTypeInstance]
        }
    }

    def update = {
        def resourceTypeInstance = ResourceType.get(params.id)
        if (resourceTypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (resourceTypeInstance.version > version) {
                    
                    resourceTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'resourceType.label', default: 'ResourceType')] as Object[], "Another user has updated this ResourceType while you were editing")
                    render(view: "edit", model: [resourceTypeInstance: resourceTypeInstance])
                    return
                }
            }
            resourceTypeInstance.properties = params
            if (!resourceTypeInstance.hasErrors() && resourceTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'resourceType.label', default: 'ResourceType'), resourceTypeInstance.id])}"
                redirect(action: "show", id: resourceTypeInstance.id)
            }
            else {
                render(view: "edit", model: [resourceTypeInstance: resourceTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'resourceType.label', default: 'ResourceType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def resourceTypeInstance = ResourceType.get(params.id)
        if (resourceTypeInstance) {
            try {
                resourceTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'resourceType.label', default: 'ResourceType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'resourceType.label', default: 'ResourceType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'resourceType.label', default: 'ResourceType'), params.id])}"
            redirect(action: "list")
        }
    }
}
