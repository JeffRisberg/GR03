package com.incra

import com.incra.domain.AbstractDomain 
import com.incra.domain.MetricType 

/**
 * The <i>Metric</i> entity is the definition of one metric to be calculated in the 
 * Analytic Service.  Since metrics are persistent domain objects, they can be configured.
 * 
 * @author Jeffrey Risberg
 * @since 01/02/11
 */
class Metric extends AbstractDomain {
  
  String name
  String description
  MetricType metricType // where type indicates if the metric is a range of values, or a range of (name,value) pairs
  String entityType // indicates the root entity that this applies to, such as UserActivity
  int retentionInDays
  
  static constraints = {
    name()
    description()
    metricType()
    entityType()
    retentionInDays()
  }
  
  static transients = getTransients_Metric()
  
  static protected def getTransients_Metric() {
    def result = []
    result.addAll(getTransients_DomainObjectAbsC())
    return result
  }
  
  String toString() {
    name
  }
}
