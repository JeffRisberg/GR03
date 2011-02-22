package com.incra

import com.incra.domain.AbstractDomain;

/**
 * The <i>Project</i> entity describes common fields for any Project, such as its
 * name, its type, its account, and the user who owns it.  For purposes of convenience
 * and to drive the dashboard, we also store startDate, endDate, and a description
 * 
 * @author Jeffrey Risberg
 * @since 10/21/10
 */
class Project extends AbstractDomain {
  
  String name
  Integer priority
  String description
  Date startDate
  Date endDate
  Double budget
  Double capitalInvest
  Double monthlySavings
  Double paybackMonths
  Double roi
  Double npv
  
  static constraints = {
    user(nullable: true)
    type()
    account()
    status()
    priority()
    
    name(blank: false)
    description(nullable: true)
    startDate()
    endDate(nullable: true)		
    budget(nullable: true)
    capitalInvest(nullable: true)
    monthlySavings(nullable: true)
    paybackMonths(nullable: true)
    roi(nullable: true)
    npv(nullable: true)
    
    dateCreated(display: false)
  }
  
  static transients = getTransients_Project();
  
  static protected def getTransients_Project() {
    def result = [];
    result.addAll(getTransients_AbstractDomain());
    return result;
  }
  
  static belongsTo = [ user : User, type : ProjectType, account : Account, status : ProjectStatus ]
  
  String toString() {
    "${name} (${type.name}) at ${account.name}"
  }
}
