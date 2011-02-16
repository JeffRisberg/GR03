package com.incra

import grails.plugins.selenium.*
import grails.plugins.selenium.pageobjects.*

/**
 * @author Jeff Risberg
 * @since 12/12/10
 */
@Mixin(SeleniumAware)
class ProjectShowPage extends AbstractShowPage {
	private static final URL = "/project/show"
	
	// Factory method that opens the page
	static ProjectShowPage open(int id) {
		new ProjectShowPage(URL + "/" + id)
	}
	
	// Constructor called by navigation methods in other page objects
	ProjectShowPage() {
		super()
	}
	
	// Constructor called by the factory method
	private ProjectShowPage(String uri) {
		super(uri)
	}
	
	protected void verifyPage() throws UnexpectedPageException {
		if (!selenium.isTextPresent("Name") || !selenium.isTextPresent("Type")) {
			throw new UnexpectedPageException("Invalid Project Show page")
		}
		if (!selenium.isTextPresent("Budget") || !selenium.isTextPresent("Priority")) {
			throw new UnexpectedPageException("Invalid Project Show page")
		}
	}
	
	ProjectListPage goToList() {
		return ProjectListPage.open()
	}
}
