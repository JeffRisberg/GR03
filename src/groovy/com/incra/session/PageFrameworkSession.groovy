package com.incra.session

import com.incra.Profile 
import com.incra.User 

/**
 * The <i>PageFrameworkSession</i> is a session state holder for the PageFrameworkService.
 * The state maintained includes the current User and Profile, and the flags for the
 * menus to display
 * 
 * @author Jeff Risberg
 * @since 11/04/10
 */
class PageFrameworkSession {
  User user
  Profile profile
  boolean showScenarioSelector
  boolean showTimeSegmentSelector
  boolean showMetricsSelector
  
  /** Constructor */
  PageFrameworkSession() {
    this.user = null
    this.profile = null
    this.showScenarioSelector = false
    this.showTimeSegmentSelector = false
    this.showMetricsSelector = false
  }
}
