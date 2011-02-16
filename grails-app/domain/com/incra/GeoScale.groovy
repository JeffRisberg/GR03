package com.incra

/**
 * The <i>GeoScale</i> entity describes a geographic scale such as building, cluster, catchment, or region.
 * 
 * @author Jeffrey Risberg
 * @since 10/19/10
 */
class GeoScale {
	String name
	int scale
	
	static constraints = {
		name(blank: false, unique: true)
		scale()
	}
	
	String toString() {
		return name
	}
}
