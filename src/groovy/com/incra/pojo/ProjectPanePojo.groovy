package com.incra.pojo

import com.incra.ProjectType 

/**
 * The <i>ProjectPanePojo</i> holds information used to drive one pane of the Project editor.
 * 
 * @author Jeffrey Risberg
 * @since 11/30/10
 */
class ProjectPanePojo {
  
  String label
  String templateName
  ProjectType projectType
  
  /** Constructor */
  ProjectPanePojo(String label, String templateName, ProjectType projectType) {
    this.label = label
    this.templateName = templateName
    this.projectType = projectType
  }
}
