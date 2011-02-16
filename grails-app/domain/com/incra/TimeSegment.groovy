package com.incra

/**
 * The <i>TimeSegment</i> entity describes one time range, such as a month, a quarter, or a year.
 * 
 * @author Jeffrey Risberg
 * @since 10/01/10
 */
class TimeSegment {
	
	TimeScale timeScale
	String label
	Date fromDate
	Date toDate
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		timeScale()
		label(blank:false)
		fromDate()
		toDate()
		dateCreated(display: false)
	}
}
