package com.incra

import java.util.Date;

import com.incra.domain.AbstractDomain;

/**
 * The <i>Transaction</i> domain class records one transaction for an account.  Each has a
 * date range, a transactionType, a resource, an amount, a uom, and a description.
 *
 * @author Jeff Risberg
 * @since 09/29/10
 */
class Transaction extends AbstractDomain {
  
  Date startDate
  Date endDate
  int amount
  UnitOfMeasure uom
  String description
  
  static constraints = {
    user(nullable : true)
    startDate(nullable: false)
    endDate(nullable: true)
    account()
    type()
    resource()
    amount()
    uom()
    description(nullable: true)
    dateCreated(display: false)
  }
  
  static transients = getTransients_Transaction();
  
  static protected def getTransients_Transaction() {
    def result = [];
    result.addAll(getTransients_AbstractDomain());
    return result;
  }
  
  static belongsTo = [ user : User, account : Account, type : TransactionType, resource : Resource ]
  
  String toString() {
    "${account.name} : ${type.name} on ${startDate}"
  }
}
