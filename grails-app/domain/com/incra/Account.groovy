package com.incra

import com.incra.domain.AbstractDomain 

/**
 * The <i>Account</i> domain class describes one organizational unit.  Each Account has a type
 * and a scale.  Each Account has a name, description, country, and naicsCode.  Each Account can 
 * have associated child AccountLinks.  Each Account can have associated Transactions.
 * 
 * @author Jeffrey Risberg
 * @since 09/29/10
 */
class Account extends AbstractDomain {
  String name
  String description
  Address address
  byte[] photo
  
  static constraints = {
    user(nullable: true)
    name(nullable: false)
    type()
    geoScale()
    description(maxSize: 500, nullable: true)
    address()
    photo(nullable: true, maxSize: 1000000)
    children(display: false)
    transactions(display: false)
    dateCreated(display: false)
  }
  
  static transients = getTransients_Account();
  
  static protected def getTransients_Account() {
    def result = [];
    result.addAll(getTransients_AbstractDomain());
    return result;
  }
  
  static mapping = { address lazy:false }
  
  static belongsTo = [ user : User, type : AccountType, geoScale : GeoScale ]
  
  static hasMany = [ children : AccountLink, transactions : Transaction, projects : Project ]
  
  static mappedBy = [ children: 'parent' ]
  
  String toString() {
    "${name}"
  }
}
