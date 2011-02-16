package com.incra.session

import com.incra.GeoScale 
import com.incra.Preferences 

/**
 * The <i>GeomapSession</i> is a session state holder for the GeomapController.  It holds information 
 * such as the geoScale to control what records are shown on the map, the overlay flags, and the center 
 * and zoom information.
 * 
 * @author Jeff Risberg
 * @since 11/14/10
 */
class GeomapSession {
  GeoScale geoScale
  Double centerLat
  Double centerLng
  int zoom;
  String mapTypeId
  Map<String,Boolean> shownMap
  
  /** Constructor */
  GeomapSession(Preferences preferences) {
    this.geoScale = preferences.geoScale
    this.centerLat = 37.335278
    this.centerLng = -121.891944
    this.zoom = 11		
    this.mapTypeId = "terrain"
    this.shownMap = new HashMap<String,Boolean>();
    
    shownMap.put("SBWR Pipeline", Boolean.TRUE)
    shownMap.put("Facilities", Boolean.TRUE)
  }
  
  /**
   * Return true if an overlay should be shown
   */
  boolean isShown(String name) {
    Boolean x = shownMap.get(name)
    
    if (x == null) return false
    if (x) return true
  }
  
  /**
   * Called from the AJAX update by clicking on an overlay toggle
   */
  void setShown(String name, boolean value) {
    if (value) {
      shownMap.put(name, Boolean.TRUE)
    }
    else {
      shownMap.put(name, Boolean.FALSE)
    }
  }
}
