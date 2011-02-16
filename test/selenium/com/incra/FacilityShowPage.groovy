package com.incra

import grails.plugins.selenium.*
import grails.plugins.selenium.pageobjects.*

/**
 * @author Jeff Risberg
 * @since 12/02/10
 */
@Mixin(SeleniumAware)
class FacilityShowPage extends AbstractShowPage {
	private static final URL = "/account/show" 
	
	// Factory method that opens the page
	static FacilityShowPage open(int id) {
		new FacilityShowPage(URL + "/" + id)
	}
	
	// Constructor called by navigation methods in other page objects
	FacilityShowPage() {
		super()
	}
	
	// Constructor called by the factory method
	private FacilityShowPage(String uri) {
		super(uri)
	}
	
	protected void verifyPage() throws UnexpectedPageException {
		if (!selenium.isTextPresent("Name") || !selenium.isTextPresent("Type")) {
			throw new UnexpectedPageException("Invalid Facility Show page")
		}
		if (!selenium.isTextPresent("Address") || !selenium.isTextPresent("Lat/Long")) {
			throw new UnexpectedPageException("Invalid Facility Show page")
		}
	}
	
	FacilityListPage goToList() {
		return FacilityListPage.open()
	}
}
