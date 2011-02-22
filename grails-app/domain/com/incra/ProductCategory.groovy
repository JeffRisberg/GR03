package com.incra

import java.util.Date;

/**
 * The <i>ProductCategory</i> class...
 * 
 * @author Spoorthy, Jeff
 * @since 02/06/11
 */
class ProductCategory {
	
	String name
	String description
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(blank: false, unique: true, maxSize: 50)	
		description()
	}
	
	static hasMany = [ products : ProductCategory ]
}
