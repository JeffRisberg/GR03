package com.incra

/** This is a DTO for content on the dashboard */
class AccountDashboardRow {
	Account account
	Account parent
	
	AccountDashboardRow(Account account, Account parent) {
		this.account = account
		this.parent = parent
	}
	
	AccountDashboardRow(Account account) {
		this.account = account
		this.parent = null
	}
}
