package com.incra


import grails.plugins.selenium.*
import org.junit.*
import static org.junit.Assert.*

/**
 * @author Jeffrey Risberg
 * @since 12/08/10
 */
@Mixin(SeleniumAware)
class DashboardTests extends GroovyTestCase {
	
	@Test void openDashboard() {
		DashboardPage dashboardPage = DashboardPage.open()
	}
}
