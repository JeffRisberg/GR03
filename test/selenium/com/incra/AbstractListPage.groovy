package com.incra

import grails.plugins.selenium.*
import grails.plugins.selenium.pageobjects.*

/**
 * @author Jeff Risberg
 * @since 12/12/10
 */
@Mixin(SeleniumAware)
abstract class AbstractListPage extends GrailsListPage {
	
	AbstractListPage() {
		super()
	}
	
	AbstractListPage(String uri) {
		super(uri)
	}
	
	String getFlashMessage() {
		hasFlashMessage() ? selenium.getText("css=.message") : null
	}
	
	boolean hasFlashMessage() {
		selenium.isElementPresent("css=.message")
	}
	
	boolean isTextPresent(String text) {
		selenium.isTextPresent(text)
	}
	
	/**
	 * rowIndex is 0-based, colIndex is 0-based
	 */
	String getFieldText(int rowIndex, int colIndex) {		
		selenium.getText("//table/tbody/tr[$rowIndex+1]/td[${colIndex+1}]")
	}
	
	public DashboardPage goHome() {
		selenium.open(selenium.contextPath + '/')
		return new DashboardPage()
	}
}
