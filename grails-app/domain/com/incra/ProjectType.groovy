package com.incra

/**
 * The <i>ProjectType</i> entity is a simple classification of projects, and in this version contains
 * on effectiveness fraction.  For instance, if a ProjectType typically reduces resource usage by 35%,
 * then the value of effectFrac would be 0.35.
 * 
 * @author Jeffrey Risberg
 * @since 10/02/10
 */
class ProjectType {
	
	String name
	String description
	String ningUrl
	double effectFrac
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(unique: true)
		resourceType()
		description(nullable: true)
		ningUrl(nullable: true)
		effectFrac()
		dateCreated(display: false)
	}
	
	String toString() {
		"${name}"
	}
	
	static belongsTo = [ resourceType : ResourceType ]
}
