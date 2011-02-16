package com.incra

/**
 * The <i>DashboardPanel</i> holds the contents of one panel that is 
 * shown on the dashboard, where each panel has a title string, a controller, and 
 * information to be shown in the left and right sides.
 * 
 * @author Jeff Risberg
 * @since 11/30/10
 */
class DashboardPanel {
  String title
  String controller
  boolean chart
  String color
  List<DashboardPanelRow> leftRows
  List<DashboardPanelRow> rightRows
  
  /** Constructor */
  DashboardPanel() {
    this.chart = false
    this.color = '#006600'
    this.leftRows = new ArrayList<DashboardPanelRow>()
    this.rightRows = new ArrayList<DashboardPanelRow>()
  }
  
  boolean isTable() { 
    table
  }
  
  void addLeftRow(String key, String value, Long id) {
    leftRows.add(new DashboardPanelRow(key: key, value: value, id: id))
  }
  
  void addRightRow(String key, String value, Long id) {
    rightRows.add(new DashboardPanelRow(key: key, value: value, id: id))
  }
}

class DashboardPanelRow {
  String key
  String value
  Long id
}