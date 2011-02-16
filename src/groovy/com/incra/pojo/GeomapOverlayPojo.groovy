package com.incra.pojo


import com.incra.EntityType 
import java.util.List;

/**
 * The <i>GeomapOverlayPojo</i> class is much like the DashboardPanePojo.  However, it works in 
 * one of two possible ways:  If kmlFileUrl is non-null, then the file is to be drawn.  If 
 * kmlFileUrl is null, then the records are plotted (these must be objects that have a field 
 * called "address").
 * 
 * @author Jeffrey Risberg
 * @since 11/22/10
 */
class GeomapOverlayPojo {
  static int maxRecordCount = 40
  
  String label
  String description
  String kmlFileUrl
  String colorName
  EntityType entityType
  List<Object> records
  boolean showMoreLink
  boolean shown
  
  /** Constructor */
  GeomapOverlayPojo(String label, String description, String kmlFileUrl, String colorName, EntityType entityType) {
    this.label = label
    this.description = description
    this.kmlFileUrl = kmlFileUrl
    this.colorName = colorName
    this.entityType = entityType
    this.records = new ArrayList<Object>()
    this.showMoreLink = false
    this.shown = false
  }
  
  /**
   * Add one more record to the overlay, cutting off at the maxRecordCount and instead setting the more flag.
   */
  void addRecord(Object record) {
    if (records.size() >= maxRecordCount) {
      showMoreLink = true;
    }
    else {
      records.add(record);
    }
  }
  
  /**
   * Add a list of records to the overlay
   */
  void addRecords(List<Object> records) {
    for (Object record : records) {
      addRecord(record);
    }
  }
}
