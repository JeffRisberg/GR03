package com.incra

/**
 * The <i>Role</i> entity...
 * 
 * @author Jeffrey Risberg
 * @since 09/30/10
 */
class Role {
	
	String name
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(unique: true) 
		dateCreated(display: false)
	}
}
