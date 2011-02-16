package com.incra

import java.util.Date;

/**
 * The <i>ResourceType</i> domain class defines a type of organization, such as "Building", "Factory", etc.
 *
 * @author Jeff Risberg
 * @since 09/30/10
 */
class ResourceType {	
	
	String name
	String description
	String ningUrl
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(unique: true)
		description(nullable: true)
		ningUrl(nullable: true)
		dateCreated(display: false)
	}
	
	static hasMany = [ resources : Resource ]
	
	String toString() {
		return name
	}
}
