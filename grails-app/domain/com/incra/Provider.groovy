package com.incra

import java.util.Date;

/**
 * The <i>Provider</i> class is a generalization of Vendor and Partner information.  
 * In fact, the VendorController and ProviderController objects use this entity underneath.
 * This refactoring was done so that Resources can be provided and priced by either a 
 * Vendor or a Partner.  Providers re geographic objects.
 * 
 * @author Spoorthy Ananthaiah, Jeffrey Risberg
 * @since 11/07/10
 */
class Provider {
	
	String name
	boolean isPartner
	boolean isVendor
	Address address
	Contact contact
	String description	
	String website 
	boolean featured
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		name(blank: false, unique: true, maxSize: 50)
		isPartner()
		isVendor()
		address()
		contact()
		website(maxSize: 60)
		description(maxSize: 500, nullable: true)
		featured()
		dateCreated(display: false)
	}
	
	static mapping = {
		address lazy: false 
		contact lazy: false
	}
	
	String toString() {
		name
	}
}