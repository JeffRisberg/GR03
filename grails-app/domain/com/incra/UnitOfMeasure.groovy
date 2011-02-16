package com.incra

import java.util.Date;

/**
 * The <i>UnitOfMeasure</i> entity defines a way to measure an amount for a Resource,
 * an Flow using that resource, a Transaction using a Resource, or a Project using a
 * resource.  It drives several input screens.
 * 
 * @author Jeff Risberg
 * @since 09/30/10
 */
class UnitOfMeasure {
	
	String name
	String plural
	Dimension dimension
	double factor
	boolean base = false
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(blank: false, unique: true)
		plural()
		dimension()
		factor()
		base()
		dateCreated(display: false)
	}
	
	String toString() {
		"${name}"
	}
}
