package com.incra

import grails.test.*

class AccountIntegrationTests extends GrailsUnitTestCase {
  protected void setUp() {
    super.setUp()
  }
  
  protected void tearDown() {
    super.tearDown()
  }
  
  void testFetchAccountTypes() {
    
    def accountType = AccountType.get(1)
    
    assertNotNull accountType
    assertEquals "Group", accountType.name
    
    def foundAccountTypes = AccountType.findAllByName(accountType.name)
    
    assertNotNull foundAccountTypes
    assertEquals 1, foundAccountTypes.size()
  }
  
  void testFetchAccounts() {
    
    def account = Account.get(1)
    
    if (false) {
      assertNotNull account
      assertEquals "California", account.name
      
      def foundAccounts = Account.findAllByName(account.name)
      
      assertNotNull foundAccounts
      assertEquals 1, foundAccounts.size()
    }
  }
  
  void testCreateAccountType() {
    
    def accountType = new AccountType(name: "Library", description: "Library desc")
    accountType.validate()
    
    assertNotNull accountType.save()
    assertNotNull accountType.id
    
    def foundAccountType = AccountType.get(accountType.id)
    assertEquals "Library", foundAccountType.name
    assertEquals "Library desc", foundAccountType.description
  }
  
  void testCreateAccount() {
    
    def accountType1 = AccountType.get(1L)
    assertNotNull accountType1
    def gs1 = GeoScale.get(1L)
    assertNotNull gs1
    
    if (false) {
      Address address1 = new Address(city: 'San Jose', statecode: 'CA', postalCode: '94505')
      address1.save()
      
      def account = new Account(type: accountType1, geoScale: gs1, address: address1,
          name: "Building 51", description: 'example')
      account.validate()
      
      assertNotNull account.save()
      assertNotNull account.id
      
      def foundAccount = Account.get(account.id)
      assertEquals "Building 51", foundAccount.name
      assertEquals accountType1, foundAccount.type
    }
  }
}
