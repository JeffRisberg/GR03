package com.incra

import java.util.Date;

/**
 * The <i>Transaction</i> domain class records one transaction for an account.  Each has a
 * date range, a transactionType, a resource, an amount, a uom, and a description.
 *
 * @author Jeff Risberg
 * @since 09/29/10
 */
class Transaction {
	
	Date startDate
	Date endDate
	int amount
	UnitOfMeasure uom
	String description
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		user(nullable : true)
		startDate(nullable: false)
		endDate(nullable: true)
		account()
		type()
		resource()
		amount()
		uom()
		description(nullable: true)
		dateCreated(display: false)
	}
	
	static belongsTo = [ user : User, account : Account, type : TransactionType, resource : Resource ]
	
	String toString() {
		"${account.name} : ${type.name} on ${startDate}"
	}
}
