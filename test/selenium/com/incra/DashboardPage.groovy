package com.incra


import grails.plugins.selenium.*
import grails.plugins.selenium.pageobjects.*

/**
 * @author Jeff Risberg
 * @since 12/02/10
 */
@Mixin(SeleniumAware)
class DashboardPage extends Page {
	private static final URL = "/" 
	
	// Factory method that opens the page
	static DashboardPage open() {
		new DashboardPage(URL)
	}
	
	// Constructor called by navigation methods in other page objects
	DashboardPage() {
		super()
	}
	
	// Constructor called by the factory method
	private DashboardPage(String uri) {
		super(uri)
	}
	
	protected void verifyPage() throws UnexpectedPageException {
		if (!selenium.isTextPresent("Facilities") || !selenium.isTextPresent("Projects")) {
			throw new UnexpectedPageException("Invalid Dashboard page")
		}
	}
}
