package com.incra

/**
 * The <i>ProductAttributeValue</i> class is at the intersection of Product and ProductAttribute.
 * 
 * @author Spoorthy, Jeff
 * @since 02/06/11
 */
class ProductAttributeValue {
	
	ProductAttribute productAttribute
	Integer value
	
	static constraints = {
		productAttribute()
		value()
	}
	
	static belongsTo = [ product : Product ]
}
