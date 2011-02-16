package com.incra

/**
 * The <i>ProjectStatus</i> entity...
 * 
 * @author Jeffrey Risberg
 * @since 11/19/10
 */
class ProjectStatus {
	String name
	int sequence
	
	static constraints = {
		name(blank: false, unique: true)
		sequence()
	}
	
	String toString() {
		return name
	}
}