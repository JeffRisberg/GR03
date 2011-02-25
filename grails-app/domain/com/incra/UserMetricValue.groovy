package com.incra

import com.incra.domain.AbstractDomain 

/** 
 * The <i>UserMetricValue</i> entity is populated by the AnalyticService.  It generates a set of 
 * values, which are stored in the child records.
 * 
 * @author Jeff Risberg
 * @since 02/17/11
 */
class UserMetricValue extends AbstractDomain {
  
  static final long MINUTE_MILLIS = 60L * 1000L
  static final long HOUR_MILLIS = 60L * MINUTE_MILLIS
  static final long DAY_MILLIS = 24L * HOUR_MILLIS
  
  UserDashboardPanel userDashboardPanel
  String label
  double average // if a numeric type, this will be filled in
  Date keepUntil // cache control parameter
  
  static hasMany = [values: UserMetricValueRow]
  
  static transients = getTransients_UserMetricValue()
  
  static protected def getTransients_UserMetricValue() {
    def result = []
    result.addAll(getTransients_AbstractDomain())
    return result
  }
  
  static constraints = {
    userDashboardPanel()
    label()
    average(nullable: true)
    keepUntil(nullable: true)
  }
  
  /** Default Constructor */
  public UserMetricValue() {
  }
  
  /** Constructor */
  public UserMetricValue(UserDashboardPanel userDashboardPanel) {
    this.userDashboardPanel = userDashboardPanel
    this.label = userDashboardPanel.metric.name
    this.average = 0.0
    
    long time = System.currentTimeMillis()
    this.keepUntil = new Date(time)
  }
}