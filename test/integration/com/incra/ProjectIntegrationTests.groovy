package com.incra

import grails.test.*

class ProjectIntegrationTests extends GrailsUnitTestCase {
  protected void setUp() {
    super.setUp()
  }
  
  protected void tearDown() {
    super.tearDown()
  }
  
  void testFetchProjectTypes() {
    
    def projectType = ProjectType.get(1)
    
    assertNotNull projectType
    assertEquals "Recycled Water", projectType.name
    
    def foundProjectTypes = ProjectType.findAllByName(projectType.name)
    
    assertNotNull foundProjectTypes
    assertEquals 1, foundProjectTypes.size()
  }
  
  void testCreateProjectType() {
    
    def resourceType1 = ResourceType.get(1)
    
    def projectType = new ProjectType(name: "Chiller Replacement", description: "CR desc", resourceType: resourceType1)
    
    assertNotNull projectType.save()
    assertNotNull projectType.id
    
    def foundProjectType = ProjectType.get(projectType.id)
    assertEquals "Chiller Replacement", foundProjectType.name
    assertEquals "CR desc", foundProjectType.description
  }
  
  void testCreateProject() {
    
    def projectType1 = ProjectType.get(1L)
    assertNotNull projectType1
    
    if (false) {
      def resource1 = Resource.get(1L)
      assertNotNull resource1
      
      def status1 = ProjectStatus.get(1L)
      assertNotNull status1
      
      def priority1 = new Integer(2)
      
      def account1 = Account.get(1L)
      assertNotNull account1
      
      def project = new Project(type: projectType1, resource: resource1, 
          account: account1, status: status1, priority: priority1,
          name: "Building 41 Remodel", description: 'example', startDate: new Date())
      
      assertNotNull project.save()
      assertNotNull project.id
      
      def foundProject = Project.get(project.id)
      assertEquals "Building 41 Remodel", foundProject.name
      assertEquals projectType1, foundProject.type
    }
  }
}
