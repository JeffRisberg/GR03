package com.incra

/**
 * The <i>UserRole</i> class...
 * 
 * @author Jeffrey Risberg
 * @since 10/22/10
 */
class UserRole {
	
	User user
	Role role
	Date effectiveStart
	Date effectiveEnd
	
	static constraints = {
		user()
		role()
		effectiveStart()
		effectiveEnd(nullable: true)
	}
}
