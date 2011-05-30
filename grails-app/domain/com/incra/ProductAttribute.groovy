package com.incra

import com.incra.domain.AbstractDomain 
import java.util.Date;

/**
 * The <i>ProductAttribute</i> class...
 * 
 * @author Spoorthy, Jeff
 * @since 02/06/11
 */
class ProductAttribute extends AbstractDomain {
  
  String name
  String type // numeric, string, video, blank line, etc.
  
  static constraints = {
    name(blank: false, unique: true, maxSize: 50)	
    type()
  }
}
