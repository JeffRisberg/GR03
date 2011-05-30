package com.incra

import com.incra.domain.AbstractDomain 
import java.util.Date;

/**
 * The <i>Product</i> class describes an entry in the Marketplace.
 * 
 * @author Jeff, Spoorthy
 * @since 02/09/11
 */
class Product extends AbstractDomain {
  
  String name
  String description	
  ProductCategory productCategory
  
  static constraints = {
    name(blank: false, unique: true, maxSize: 60)		
    description(maxSize: 500, nullable: true)	
    productCategory()	
    dateCreated(display: false)
  }
  
  static transients = getTransients_Product();
  
  static protected def getTransients_Product() {
    def result = [];
    result.addAll(getTransients_AbstractDomain());
    return result;
  }
  
  static hasMany = [ productItems : ProductAttribute ]
  
  String toString() {
    name
  }
}
