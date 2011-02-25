package com.incra

import com.google.code.facebookapi.Metric 
import com.incra.domain.AbstractDomain 
import com.incra.domain.ChartType 

/** 
 * The <i>UserDashboardPanel</i> entity defines the settings for one panel of a dashboard
 * for one user.  The user can be null, in which case the entity applies for all users.
 * 
 * This entity was created for the Sustainable Silicon Valley dashboard, not all fields are
 * applicable as yet.
 * 
 * @author Jeff Risberg
 * @since 11/29/10
 */
class UserDashboardPanel extends AbstractDomain {
  
  Metric metric  
  int panelIndex
  String label
  ChartType chartType
  String bgColor
  String fgColor
  String rangeType
  Date lastRecalcDate
  
  static transients = getTransients_UserDashboardPanel()
  
  static protected def getTransients_UserDashboardPanel() {
    def result = []
    result.addAll(getTransients_AbstractDomain())
    return result
  }
  
  static belongsTo = [user: User]
  
  static def constraints = {
    user(nullable: true)
    metric()
    panelIndex()
    label()
    chartType()
    fgColor(nullable: true)
    bgColor(nullable: true)
    rangeType()
    lastRecalcDate(nullable: true)
  }
}
