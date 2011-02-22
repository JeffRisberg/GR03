package com.incra

import java.util.Date;

/**
 * The <i>ProductAttribute</i> class...
 * 
 * @author Spoorthy, Jeff
 * @since 02/06/11
 */
class ProductAttribute {
	
	String name
	String type // numeric, string, video, blank line, etc.
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(blank: false, unique: true, maxSize: 50)	
		type()
	}
}
