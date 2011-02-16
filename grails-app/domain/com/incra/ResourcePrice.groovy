package com.incra

import java.util.Date;

/** 
 * The <i>ResourcePrice</i> entity describes the offering of a resource from a vendor or partner
 * over a given time range.  While each of Resource and ResourcePrice have their own controllers,
 * they both use the ResourceService for business logic such as lookups and conversions.
 * 
 * @author Jeffrey Risberg
 * @since 11/12/10
 */
class ResourcePrice {
	
	Resource resource
	Provider provider
	double price
	Date validFromDate
	Date validToDate
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		resource()
		provider()
		price()
		validFromDate()
		validToDate(nullable: true)
		dateCreated(display: false)
	}
	
	static belongsTo = [ resource: Resource, provider: Provider ];
	
	String toString() {
		return "[Provider: " + provider.name + ", Resource: " + resource.name + " " + price + "]"
	}
}
