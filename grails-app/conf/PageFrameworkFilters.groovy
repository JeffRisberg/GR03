

/**
 * The <i>PageFrameworkFilters</i> class contains code that runs before the rendering of a page, to set up
 * the page framework for navigation, security, etc.
 *  
 * @author Jeffrey Risberg
 * @since 10/16/10
 */
class PageFrameworkFilters {
	
	def pageFrameworkService
	def geomapService
	
	def filters = {
		all(controller: '*', action: '*') {
			before = {
			}
			after = {
			}
			afterView = {
			}
		}
	}
}
