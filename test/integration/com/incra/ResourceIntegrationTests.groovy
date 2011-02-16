package com.incra

import grails.test.*

class ResourceIntegrationTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testFetchResourceTypes() {
		
		def resourceType = ResourceType.get(1)
		
		assertNotNull resourceType
		assertEquals "Energy", resourceType.name
		
		def foundResourceTypes = ResourceType.findAllByName(resourceType.name)
		
		assertNotNull foundResourceTypes
		assertEquals 1, foundResourceTypes.size()
	}
	
	void testFetchResources() {
		
		def resource = Resource.get(1)
		
		assertNotNull resource
		assertEquals "Electricity", resource.name
		
		def foundResources = Resource.findAllByName(resource.name)
		
		assertNotNull foundResources
		assertEquals 1, foundResources.size()
	}
	
	void testCreateResourceType() {
		
		def resourceType = new ResourceType(name: "energy", description: "energy desc")
		
		assertNotNull resourceType.save()
		assertNotNull resourceType.id
		
		def foundResourceType = ResourceType.get(resourceType.id)
		assertEquals "energy", foundResourceType.name
		assertEquals "energy desc", foundResourceType.description
	}
	
	void testCreateResource() {
		
		def resourceType1 = ResourceType.get(1L)
		assertNotNull resourceType1
		def uom1 = UnitOfMeasure.get(1L)
		assertNotNull uom1
		
		def resource = new Resource(type: resourceType1, uom: uom1, name: "unobtainium", description: 'example')
		
		assertNotNull resource.save()
		assertNotNull resource.id
		
		def foundResource = Resource.get(resource.id)
		assertEquals "unobtainium", foundResource.name
		assertEquals resourceType1, foundResource.type
	}
}
