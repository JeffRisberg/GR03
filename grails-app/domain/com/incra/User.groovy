package com.incra

import java.util.Date;

/**
 * The <i>User</i> domain class defines one user, including the link to the user's profile.  The User
 * class also maintains a lastLogin and a loginCount.
 * 
 * @author Jeff Risberg
 * @since 09/28/10
 */
class User {
	
	String userId
	String password
	Profile profile
	Date lastLogin
	int loginCount = 0
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		userId(size: 3..20, unique: true)
		password(minSize: 8)
		userRoles()
		lastLogin(editable: false, nullable: true)
		loginCount(editable: false)
		dateCreated(display: false)
		profile(nullable: true)
	}
	
	static mapping = { profile lazy:false }
	
	static hasMany = [ userRoles : UserRole ]
	
	String toString() {
		"${profile.fullName}"
	}
	
	String roleNames() {
		StringBuffer sb = new StringBuffer();
		
		boolean atFirst = true;
		for (UserRole userRole : userRoles) {
			if (atFirst == false) sb.append(", ");
			
			sb.append(userRole.role.name);
			atFirst = false;
		}	
		
		sb.toString();
	}
}
