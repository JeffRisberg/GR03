package com.incra

/**
 * The <i>Rating<i> entity...
 * 
 * @author Jeffrey Risberg
 * @since 10/03/10
 */
class Rating {
	
	EntityType entityType
	long entityId
	User user
	int value
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		user(nullable : true) 
		entityType(nullable : false)
		dateCreated(display: false)
	}
	
	String toString() {
		"Rating="+value
	}
}
