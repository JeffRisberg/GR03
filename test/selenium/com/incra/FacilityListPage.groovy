package com.incra

import grails.plugins.selenium.*
import grails.plugins.selenium.pageobjects.*

/**
 * @author Jeff Risberg
 * @since 12/10/10
 */
@Mixin(SeleniumAware)
class FacilityListPage extends AbstractListPage {
	private static final URL = "/account" 
	
	// Factory method that opens the page
	static FacilityListPage open() {
		new FacilityListPage(URL)
	}
	
	// Constructor called by navigation methods in other page objects
	FacilityListPage() {
		super()
	}
	
	// Constructor called by the factory method
	private FacilityListPage(String uri) {
		super(uri)
	}
	
	protected void verifyPage() throws UnexpectedPageException {
		if (!selenium.isTextPresent("Name") || !selenium.isTextPresent("Type")) {
			throw new UnexpectedPageException("Invalid Facility List page")
		}
	}
	
	/** index: 0-based */
	FacilityShowPage openRow(int index) {
		String fieldText = getFieldText(index, 0)
		
		selenium.clickAndWait("link=" + fieldText)
		return new FacilityShowPage()
	}
	
	void setName(String name) {
		selenium.type("name=name", name)
	}
	
	void clickSearch() {
		selenium.submitAndWait("name=filterForm")
	}
}
