package com.incra

import java.util.Date;

/**
 * The <i>ProjectService</i> supports the Project module.  The main function is the ROI calculation.
 * 
 * @author Jeffrey Risberg
 * @since 11/20/10
 */
class ProjectService {
  
  def resourceService
  
  /**
   * Get the ProjectSession, creating it if needed.
   */
  ProjectSession getProjectSession(def session) {
    ProjectSession pSession = (ProjectSession) session.pSession;
    
    if (pSession == null) {
      pSession = new ProjectSession();
      session.pSession = pSession;
    }
    return pSession
  }
  
  /**
   * Creates a project for a specified account
   * @param account
   * @param type
   * @param resource
   * @param uom
   * @param name
   * @param startDate
   * @param desc
   * @return
   */
  Project saveProject(Account account, ProjectType type, ProjectStatus status,  
  String name, Date startDate,  String desc, int priority, double budget){
    
    Project project
    if(!account){
      throw new RuntimeException("Project cannot be created as the Account($account)does not exist" );
    }else{
      project = new Project(account: account, name: name, type: type, budget:budget,
          status: status, priority: priority, description: desc, startDate:startDate)
      project.save()
    }
    project
  }
}
