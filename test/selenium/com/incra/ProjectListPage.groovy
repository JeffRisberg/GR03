package com.incra

import grails.plugins.selenium.*
import grails.plugins.selenium.pageobjects.*

/**
 * @author Jeff Risberg
 * @since 12/10/10
 */
@Mixin(SeleniumAware)
class ProjectListPage extends AbstractListPage {
	private static final URL = "/project" 
	
	// Factory method that opens the page
	static ProjectListPage open() {
		new ProjectListPage(URL)
	}
	
	// Constructor called by navigation methods in other page objects
	ProjectListPage() {
		super()
	}
	
	// Constructor called by the factory method
	private ProjectListPage(String uri) {
		super(uri)
	}
	
	protected void verifyPage() throws UnexpectedPageException {
		if (!selenium.isTextPresent("Name") || !selenium.isTextPresent("Type")) {
			throw new UnexpectedPageException("Invalid Project List page")
		}
		if (!selenium.isTextPresent("Budget") || !selenium.isTextPresent("ROI")) {
			throw new UnexpectedPageException("Invalid Project List page")
		}
	}
	
	/** index: 0-based */
	ProjectShowPage openRow(int index) {
		String fieldText = getFieldText(index, 0)
		
		selenium.clickAndWait("link=" + fieldText)
		return new ProjectShowPage()
	}
	
	void setName(String name) {
		selenium.type("name=name", name)
	}
	
	void clickSearch() {
		selenium.submitAndWait("name=filterForm")
	}
}
