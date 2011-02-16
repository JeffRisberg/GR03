package com.incra

import grails.plugins.selenium.*
import grails.plugins.selenium.pageobjects.*

/**
 * The <i>AbstractCreatePage</i> class...
 * 
 * @author Jeff Risberg
 * @since 12/07/10
 */
@Mixin(SeleniumAware)
abstract class AbstractCreatePage extends GrailsCreatePage {
	
	AbstractCreatePage() {
	}
	
	protected AbstractCreatePage(String url) {
		super(url)
	}
	
	public DashboardPage goHome() {
		selenium.open(selenium.contextPath + '/')
		return new DashboardPage()
	}
}