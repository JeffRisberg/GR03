package com.incra

import java.util.Date;

/** 
 * The <i>PriorityCode</i> entity...
 * 
 * @author Jeffrey Risberg
 * @since 10/17/10
 */
class PriorityCode {
	
	String name
	Date dateCreated
	Date dateUpdated
	
	static constraints = {
		name()
		dateCreated(display: false)
	}
}
