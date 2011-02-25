package com.incra

import java.util.List;

import grails.plugins.selenium.*
import grails.plugins.selenium.pageobjects.*
import groovy.lang.Lazy;

/**
 * @author Jeff Risberg
 * @since 12/12/10
 */
@Mixin(SeleniumAware)
class AbstractShowPage extends GrailsShowPage {
  
  AbstractShowPage() {
    super()
  }
  
  AbstractShowPage(String uri) {
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
  
  @Lazy List leftFieldNames = (0..<leftFieldCount).collect {i ->
    selenium.getTable("//table.$i.0").replaceAll(/[^\w\s]+/, "")
  }
  
  @Lazy List rightFieldNames = (0..<leftFieldCount).collect {i ->
    try {
      if (selenium.isElementPresent("//table//tr[${i+1}]/td[3]")) {
        selenium.getTable("//table.$i.2").replaceAll(/[^\w\s]+/, "")
      }
      else {
        ""
      }
    }
    catch (Exception e) {
      ""
    }
  }
  
  int getLeftFieldCount() {
    selenium.getXpathCount("//table/tbody/tr")
  }
  
  int getRightFieldCount() {
    selenium.getXpathCount("//table/tbody/tr/td[4]")
  }
  
  @Override
  int getFieldCount() {
    getLeftFieldCount() + getRightFieldCount()
  }
  
  /**
   * Intercepts property getters to return data from table based on the field name.
   */
  @Override
  def propertyMissing(String name) {
    def indexLeft = leftFieldNames.indexOf(name)
    def indexRight = rightFieldNames.indexOf(name)
    
    if (indexLeft >= 0) {
      selenium.getTable "//table.$indexLeft.1"
    } else if (indexRight >= 0) {
      selenium.getTable "//table.$indexRight.3"
    } else {
      throw new MissingPropertyException(name)
    }
  }
  
  public DashboardPage goHome() {
    selenium.open(selenium.contextPath + '/')
    return new DashboardPage()
  }
}
