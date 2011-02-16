package com.incra

/**
 * The <i>Module</i> class is a subclass of a Component which has a list of menu items
 * 
 * @author Jeffrey Risberg
 * @since 10/03/10
 */
class Module extends Component {
	
	String controller
	String imgSrc
	
	static constraints = {
		controller(nullable: true)
		imgSrc(nullable: true)
	}
}
