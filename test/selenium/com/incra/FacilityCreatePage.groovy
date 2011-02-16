package com.incra

import grails.plugins.selenium.*
import grails.plugins.selenium.pageobjects.*

/**
 * @author Jeff Risberg
 * @since 12/02/10
 */
@Mixin(SeleniumAware)
class FacilityCreatePage extends AbstractCreatePage {
	private static final URL = "/account/create" 
	
	// Factory method that opens the page
	static FacilityCreatePage open() {
		new FacilityCreatePage(URL)
	}
	
	// Constructor called by navigation methods in other page objects
	FacilityCreatePage() {
		super()
	}
	
	// Constructor called by the factory method
	private FacilityCreatePage(String uri) {
		super(uri)
	}
	
	protected void verifyPage() throws UnexpectedPageException {
		if (!selenium.isTextPresent("Name") || !selenium.isTextPresent("Type")) {
			throw new UnexpectedPageException("Invalid Facility Create page")
		}
		if (!selenium.isTextPresent("Address Line 1") || !selenium.isTextPresent("Address Line 2")) {
			throw new UnexpectedPageException("Invalid Facility Create page")
		}
		if (!selenium.isTextPresent("City") || !selenium.isTextPresent("State Code")) {
			throw new UnexpectedPageException("Invalid Facility Create page")
		}
	}
	
	FacilityListPage goToList() {
		return FacilityListPage.open()
	}
	
	void setName(String name) {
		selenium.type("name=name", name)
	}
	
	void setAddressLine1(String addressLine1) {
		selenium.type("name=addressLine1", addressLine1)
	}
	
	void setCity(String city) {
		selenium.type("name=city", city)
	}
	
	void setStateCode(String stateCode) {
		selenium.type("name=stateCode", stateCode)
	}
	
	void setPostalCode(String postalCode) {
		selenium.type("name=postalCode", postalCode)
	}
	
	FacilityShowPage clickCreate() {
		selenium.clickAndWait("id=create")
		new FacilityShowPage()
	}
}
