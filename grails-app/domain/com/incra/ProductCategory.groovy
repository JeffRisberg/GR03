package com.incra

import com.incra.domain.AbstractDomain 
import java.util.Date;

/**
 * The <i>ProductCategory</i> class...
 * 
 * @author Spoorthy, Jeff
 * @since 02/06/11
 */
class ProductCategory extends AbstractDomain {
  
  String name
  String description
  
  static constraints = {
    name(blank: false, unique: true, maxSize: 50)	
    description()
  }
  
  static hasMany = [ products : ProductCategory ]
}
