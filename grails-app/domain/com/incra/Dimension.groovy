package com.incra

import java.util.Date;

/**
 * The <i>Dimension</i> entity...
 * 
 * @author Jeff Risberg
 * @since 09/30/10
 */
class Dimension {
	String name
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(blank: false, unique: true)
		dateCreated(display: false)
	}
	
	String toString() {
		"${name}"
	}
}
