package com.incra



/** 
 * The <i>UserMetricValueRow</i> entity records one (label,value) pair for a specific UserMetricValue
 * object.  For instance, if the UserMetricValue calculated a histogram of activities, the rows 
 * might be (Bike, 30), (Treadmill, 20), (Elliptical, 16), (Dance, 3).
 * 
 * The other fields in this entity are used to manage sequencing, and to provide a facility to click
 * on a label and perform an action on the related object.  In this case, the display rendering code
 * will create a link using the entityType of the metric to locate the controller, and the id from 
 * this record.
 * 
 * @author Jeff Risberg
 * @since 02/10/11
 */
class UserMetricValueRow {
  
  int seqNum 
  String label
  double value
  long relatedId 
  
  static belongsTo = [userMetricValue: UserMetricValue]
  
  def constraints = {
    userMetricValue()
    seqNum()
    label()
    value()
    relatedId()
  }
  
  /** Default Constructor */
  public UserMetricValueRow() {
  }
  
  /** Constructor */
  public UserMetricValueRow(UserMetricValue userMetricValue, int seqNum, String label, double value, long relatedId) {
    this.userMetricValue = userMetricValue
    this.seqNum = seqNum
    this.label = label
    this.value = value
    this.relatedId = relatedId
  }
}