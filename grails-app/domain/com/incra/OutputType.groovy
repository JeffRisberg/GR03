package com.incra

import java.util.Date

/**
 * The <i>OutputType</i> domain class defines a type of out "SF6", "Factory", etc.
 * 
 * @author Jeff Risberg
 * @since 09/30/10
 */
class OutputType {

	String name
	String description
	Date dateCreated
	Date lastUpdated

	static constraints = {
		name(unique: true)
		description(nullable: true)
		dateCreated(display: false)
	}

	static hasMany = [ accounts : Account ]

	String toString() {
		return name
	}
}
