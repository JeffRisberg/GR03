package com.incra

/**
 * The <i>TransactionCategory</i> domain class defines a collection of TransactionTypes.
 * 
 * @author Jeff Risberg
 * @since 09/29/10
 */
class TransactionCategory {
	static final int AC_Scope1 = 1;
	static final int AC_Scope2 = 2;
	static final int AC_Scope3 = 3;
	
	String name
	String description
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(blank: false, unique:true)
		description(nullable: true)
		transactionTypes()
		dateCreated(display: false)
	}
	
	static hasMany = [ transactionTypes : TransactionType ]
	
	String toString() {
		"${name}"
	}
}
