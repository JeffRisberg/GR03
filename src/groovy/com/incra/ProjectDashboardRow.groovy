package com.incra

/** This is a DTO for content on the dashboard */
class ProjectDashboardRow {
	Long id
	String name
	Account account
	ProjectType type
	Resource resource
	double investment
	double reduction
	double roi
	String description
	Date startDate
	Date endDate
	
	ProjectDashboardRow(Long id, String name, Account account, ProjectType type, Resource resource,
	double investment, double reduction, roi, String description,
	Date startDate, Date endDate) {
		this.id = id
		this.name = name
		this.account = account
		this.type = type
		this.resource = resource
		this.investment = investment
		this.reduction = reduction
		this.roi = roi
		this.description = description
		this.startDate = startDate
		this.endDate = endDate
	}
}
