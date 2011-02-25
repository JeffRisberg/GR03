package com.incra

import com.incra.domain.AbstractDomain 

/** 
 * The <i>UserMetric</i> entity ...
 * 
 * @author Jeff Risberg
 * @since 11/19/10
 */
class UserMetric extends AbstractDomain {
  
  Metric metric  
  int historicalInterval
  Date lastRecalcDate
  
  static transients = getTransients_UserMetric()
  
  static protected def getTransients_UserMetric() {
    def result = []
    result.addAll(getTransients_AbstractDomain())
    return result
  }
  
  static belongsTo = [user: User]
  
  static def constraints = {
    metric()  
    historicalInterval()
    lastRecalcDate()
  }
}