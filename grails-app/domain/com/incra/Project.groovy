package com.incra

/**
 * The <i>Project</i> entity describes one improvement project, and the associated resource.  A project has a
 * reduction amount and a unit of measure.
 * 
 * @author Jeffrey Risberg
 * @since 10/01/10
 */
class Project {
	
	String name
	String description
	double investment
	Resource resource
	UnitOfMeasure uom
	double total
	double reduction
	double costSavings
	double roi
	Date startDate
	Date endDate
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
		user(nullable: true)
		name(blank: false, unique: true)
		description(nullable: true)
		type()
		account()
		investment(min:0.0d)
		resource()
		uom()
		total(nullable: true)
		reduction(nullable: true)
		costSavings(nullable: true)
		roi(nullable: true)
		startDate()
		endDate(nullable: true)
		dateCreated(display: false)
	}
	
	static belongsTo = [ user : User, type : ProjectType, account : Account ]
	
	String toString() {
		return name
	}
}
