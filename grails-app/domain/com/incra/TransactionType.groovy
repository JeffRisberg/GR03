package com.incra

/**
 * The <i>TransactionType</i> domain class defines a type of transaction, such as "Produce", 
 * or "Consume".  This is used in combination with the ResourceType entity to provide a template
 * for a transaction.
 * 
 * @author Jeff Risberg
 * @since 09/29/10
 */
class TransactionType {	
	
	String name
	String description
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		transactionCategory()
		name(blank: false)		
		resourceType(unique: 'name')
		description(nullable: true)
		dateCreated(display: false)
	}
	
	static belongsTo = [ transactionCategory : TransactionCategory, resourceType : ResourceType ]
	
	static hasMany = [ transactions : Transaction ]
	
	String toString() {
		"${name}"
	}
}
