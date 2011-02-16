package com.incra

/**
 * The <i>TimeScale</i> entity describes a scale of time such as Day, Month, Quarter, Year
 * 
 * @author Jeffrey Risberg
 * @since 10/19/10
 */
class TimeScale {
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
