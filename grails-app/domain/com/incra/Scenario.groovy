package com.incra

import java.util.Date;

/**
 * The <i>Scenario</i> entity
 * 
 * @author Jeffrey Risberg
 * @since 10/16/10
 */
class Scenario {
	
	String name
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(unique: true)
		dateCreated(display: false)
	}
}
