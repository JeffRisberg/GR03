package com.incra

import grails.plugins.selenium.*
import grails.plugins.selenium.pageobjects.*

/**
 * @author Jeff Risberg
 * @since 12/12/10
 */
@Mixin(SeleniumAware)
abstract class AbstractPage extends Page {
	
	AbstractPage() {
		super()
	}
	
	private AbstractPage(String uri) {
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
	
	public DashboardPage goHome() {
		selenium.open(selenium.contextPath + '/')
		return new DashboardPage()
	}
}
