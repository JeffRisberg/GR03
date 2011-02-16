package com.incra

import java.util.Date;

/**
 * The <i>Flow</i> class describes one inter-Account transfer of a Resource.  It has 
 * fields for the from-Account and the to-Account.
 * 
 * @author Jeffrey Risberg
 * @since 10/15/10
 */
class Flow {
	
	Resource resource
	UnitOfMeasure uom
	double amount
	Date dateCreated
	Date lastUpdated
	
	static belongsTo = [ fromAccount : Account, toAccount : Account ]
	
	static constraints = {
		fromAccount()
		toAccount()
		amount()
		resource()
		uom()
		dateCreated(display: false)
	}
}
