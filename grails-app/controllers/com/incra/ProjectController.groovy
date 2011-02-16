package com.incra

import java.util.List 
import org.hibernate.Query 
import org.hibernate.Session 

import com.incra.pojo.DisplayFilterPojo;

/**
 * The <i>ProjectController</i> is based on generated code.
 *
 * @author Jeffrey Risberg
 * @since 10/12/10
 */
class ProjectController {
	
	def pageFrameworkService
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index = {
		redirect(action: "list", params: params)
	}
	
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		User currentUser = pageFrameworkService.getCurrentUser(session);
		
		Preferences prefs = Preferences.get(1)
		List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
		DisplayFilterPojo dfp;
		
		dfp = new DisplayFilterPojo(name: 'projectType', label: 'Project Type:', type: 'Select',
		values: ProjectType.list())
		filters.add(dfp)
		
		// Keep these values so we can rerender while maintaining filter value settings
		flash.projectType = params.projectType
		
		def criteria = Project.createCriteria()
		def query = {
			and {
				if (params.projectType) {
					def selectedProjectType = ProjectType.get(Integer.parseInt(params.projectType))
					if (selectedProjectType) {
						eq('type', selectedProjectType)
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
		
		[filters: filters, projectInstanceList: results, projectInstanceTotal: results.totalCount]
	}
	
	def create = {
		def projectInstance = new Project()
		projectInstance.properties = params
		return [projectInstance: projectInstance]
	}
	
	def save = {
		def projectInstance = new Project(params)
		if (projectInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.id])}"
			redirect(action: "show", id: projectInstance.id)
		}
		else {
			render(view: "create", model: [projectInstance: projectInstance])
		}
	}
	
	def show = {
		def projectId = params.id as Long
		
		def projectInstance = Project.get(projectId)
		if (!projectInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
		else {
			EntityType etProject = EntityType.findBySingularName("Project")
			
			List<Comment> comments = Comment.createCriteria().list {
				and {
					eq('entityType', etProject)
					eq('entityId', projectId)
				}
				order("dateCreated", "desc")
			}
			
			[project: projectInstance, comments: comments]
		}
	}
	
	def edit = {
		def projectInstance = Project.get(params.id)
		if (!projectInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [projectInstance: projectInstance]
		}
	}
	
	def update = {
		def projectInstance = Project.get(params.id)
		if (projectInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (projectInstance.version > version) {
					
					projectInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'project.label', default: 'Project')] as Object[], "Another user has updated this Project while you were editing")
					render(view: "edit", model: [projectInstance: projectInstance])
					return
				}
			}
			projectInstance.properties = params
			if (!projectInstance.hasErrors() && projectInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.id])}"
				redirect(action: "show", id: projectInstance.id)
			}
			else {
				render(view: "edit", model: [projectInstance: projectInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def delete = {
		def projectInstance = Project.get(params.id)
		if (projectInstance) {
			try {
				projectInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def drillDown = {
		if (session.projectLastDrillDown) {
			session.projectLastDrillDown.call()
		}
		else {
			detailsDrillDown()
		}
	}
	
	/**
	 * Display information about the Project, including comments attached to it
	 */
	def detailsDrillDown = {
		session.projectLastDrillDown = detailsDrillDown
		
		def projectId = params.id as Long
		Project project = Project.get(projectId)
		
		if (project) {
			EntityType etProject = EntityType.findBySingularName("Project")
			
			List<Comment> comments = Comment.createCriteria().list {
				and {
					eq('entityType', etProject)
					eq('entityId', projectId)
				}
			}
			
			render(template: "/projectDetails",
			model: [project: project, comments: comments]);
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
	}
	
	/**
	 * Display information about the Project's Forecast
	 */
	def forecastDrillDown = {
		session.projectLastDrillDown = forecastDrillDown
		
		def projectId = params.id as Long
		Project project = Project.get(projectId)
		
		if (project) {
			EntityType etProject = EntityType.findByName("Project")
			
			// Get all the values
			List<TimeSeriesValue> tsvList = TimeSeriesValue.createCriteria().list {
				and {
					eq('resource', project.resource)
					eq('entityType', etProject)
					eq('entityId', project.id)
				}
			}
			
			def values = []
			for (TimeSeriesValue tsv : tsvList) {
				TimeSegment timeSegment = tsv.timeSegment
				int year = timeSegment.fromDate.year;
				double value = tsv.value
				
				if (year >= 109) {
					values.add(value)
				}
			}
			
			render(template: "/projectForecast",
			model: [project: project, values: values]);
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
	}
	
	/**
	 * Display a graph of the Project
	 */
	def graphDrillDown = {
		session.projectLastDrillDown = graphDrillDown
		
		def projectId = params.id as Long
		Project project = Project.get(projectId)
		
		if (project) {
			EntityType etProject = EntityType.findByName("Project")
			
			// Get all the values
			List<TimeSeriesValue> tsvList = TimeSeriesValue.createCriteria().list {
				and {
					eq('resource', project.resource)
					eq('entityType', etProject)
					eq('entityId', project.id)
				}
			}
			
			def totalOnYear = [:]
			for (TimeSeriesValue tsv : tsvList) {
				TimeSegment timeSegment = tsv.timeSegment
				int year = timeSegment.fromDate.year;
				double value = tsv.value
				
				if (year >= 109) {
					totalOnYear[timeSegment.label] = value;
				}
			}
			
			render(template: "/projectGraph",
			model: [project: project, totalOnYear: totalOnYear, uom : project.resource.uom]);
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def sessionFactory
	
	/**
	 * Store data, calculate and then jump to details pane to see results
	 */
	def calculate = {
		def projectInstance = Project.get(params.id)
		
		double v2009 = 0.0;
		if (params.v2009) v2009 = params.v2009 as Double
		double v2010 = 0.0
		if (params.v2010) v2010 = params.v2010 as Double
		double v2011 = 0.0
		if (params.v2011) v2011 = params.v2011 as Double
		double v2012 = 0.0
		if (params.v2012) v2012 = params.v2012 as Double
		double v2013 = 0.0
		if (params.v2013) v2013 = params.v2013 as Double
		
		TimeSegment ts2009 = TimeSegment.findByLabel("2009")
		TimeSegment ts2010 = TimeSegment.findByLabel("2010")
		TimeSegment ts2011 = TimeSegment.findByLabel("2011")
		TimeSegment ts2012 = TimeSegment.findByLabel("2012")
		TimeSegment ts2013 = TimeSegment.findByLabel("2013")
		
		EntityType etProject = EntityType.findByName("Project")
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from TimeSeriesValue tsv " +
		"where tsv.entityType = :entityType and tsv.entityId = :entityId");
		query.setEntity("entityType", etProject);
		query.setLong("entityId", projectInstance.id);
		query.executeUpdate();
		
		TimeSeriesValue tsv
		tsv = new TimeSeriesValue(entityType: etProject, entityId: projectInstance.id, 
		resource: projectInstance.resource, timeSegment: ts2009, value: v2009)
		tsv.save()
		tsv = new TimeSeriesValue(entityType: etProject, entityId: projectInstance.id,
		resource: projectInstance.resource, timeSegment: ts2010, value: v2010)
		tsv.save()
		tsv = new TimeSeriesValue(entityType: etProject, entityId: projectInstance.id,
		resource: projectInstance.resource, timeSegment: ts2011, value: v2011)
		tsv.save()
		tsv = new TimeSeriesValue(entityType: etProject, entityId: projectInstance.id,
		resource: projectInstance.resource, timeSegment: ts2012, value: v2012)
		tsv.save()
		tsv = new TimeSeriesValue(entityType: etProject, entityId: projectInstance.id,
		resource: projectInstance.resource, timeSegment: ts2013, value: v2013)
		tsv.save()
		
		double total = v2009 + v2010 + v2011 + v2012 + v2013;
		double resourceCost = projectInstance.resource.cost
		double reduction = total * projectInstance.type.effectFrac
		double costSavings = resourceCost * reduction
		double roi = 100.0 * (costSavings/projectInstance.investment)
		
		projectInstance.total = total;
		projectInstance.reduction = reduction;
		projectInstance.costSavings = costSavings
		projectInstance.roi = roi
		projectInstance.save()
		
		detailsDrillDown()
	}
}
