package com.incra

class ProjectTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectTypeInstanceList: ProjectType.list(params), projectTypeInstanceTotal: ProjectType.count()]
    }

    def create = {
        def projectTypeInstance = new ProjectType()
        projectTypeInstance.properties = params
        return [projectTypeInstance: projectTypeInstance]
    }

    def save = {
        def projectTypeInstance = new ProjectType(params)
        if (projectTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'projectType.label', default: 'ProjectType'), projectTypeInstance.id])}"
            redirect(action: "show", id: projectTypeInstance.id)
        }
        else {
            render(view: "create", model: [projectTypeInstance: projectTypeInstance])
        }
    }

    def show = {
        def projectTypeInstance = ProjectType.get(params.id)
        if (!projectTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectType.label', default: 'ProjectType'), params.id])}"
            redirect(action: "list")
        }
        else {
            [projectTypeInstance: projectTypeInstance]
        }
    }

    def edit = {
        def projectTypeInstance = ProjectType.get(params.id)
        if (!projectTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectType.label', default: 'ProjectType'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [projectTypeInstance: projectTypeInstance]
        }
    }

    def update = {
        def projectTypeInstance = ProjectType.get(params.id)
        if (projectTypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (projectTypeInstance.version > version) {
                    
                    projectTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'projectType.label', default: 'ProjectType')] as Object[], "Another user has updated this ProjectType while you were editing")
                    render(view: "edit", model: [projectTypeInstance: projectTypeInstance])
                    return
                }
            }
            projectTypeInstance.properties = params
            if (!projectTypeInstance.hasErrors() && projectTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'projectType.label', default: 'ProjectType'), projectTypeInstance.id])}"
                redirect(action: "show", id: projectTypeInstance.id)
            }
            else {
                render(view: "edit", model: [projectTypeInstance: projectTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectType.label', default: 'ProjectType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def projectTypeInstance = ProjectType.get(params.id)
        if (projectTypeInstance) {
            try {
                projectTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'projectType.label', default: 'ProjectType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'projectType.label', default: 'ProjectType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectType.label', default: 'ProjectType'), params.id])}"
            redirect(action: "list")
        }
    }
}
