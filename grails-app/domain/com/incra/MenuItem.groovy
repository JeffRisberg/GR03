package com.incra

/**
 * The <i>MenuItem</i> class...
 * 
 * @author Jeffrey Risberg
 * @since 10/02/10
 */
class MenuItem extends Component {
	
	String controller
	String action = 'index'
	boolean administrative = false;
	
	static constraints = {
	}
}
