package com.incra

import java.util.Date;

/**
 * The <i>Language</i> entity is reference data to indicate a deployment language.
 * 
 * @author Jeffrey Risberg
 * @since 10/02/10
 */
class Language {
	
	String name
	String code
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(unique : true)
		code()
		dateCreated(display: false)
	}
}
