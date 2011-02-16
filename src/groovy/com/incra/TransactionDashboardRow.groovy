package com.incra

/** This is a DTO for content on the dashboard */
class TransactionDashboardRow {
	Account account
	TransactionType type
	Resource resource
	UnitOfMeasure uom
	double amount
	Date startDate
	Date endDate
	
	TransactionDashboardRow(Account account, TransactionType type, double amount,
	Date startDate, Date endDate, Resource resource, UnitOfMeasure uom) {
		this.account = account
		this.type = type
		this.amount = amount
		this.startDate = startDate
		this.endDate = endDate
		this.resource = resource
		this.uom = uom
	}
}
