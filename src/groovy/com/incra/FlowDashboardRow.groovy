package com.incra

/** This is a DTO for content on the dashboard */
class FlowDashboardRow {
	Account fromAccount
	Account toAccount
	double amount
	UnitOfMeasure uom
	Resource resource
	
	FlowDashboardRow(Account fromAccount, Account toAccount, double amount,
	UnitOfMeasure uom, Resource resource) {
		this.fromAccount = fromAccount
		this.toAccount = toAccount
		this.amount = amount
		this.uom = uom
		this.resource = resource
	}
}
