import com.incra.AccountService 
import com.incra.AccountType 
import com.incra.EntityType 
import com.incra.FlowService 
import com.incra.Language 
import com.incra.GeoScale
import com.incra.MenuItem 
import com.incra.Module 
import com.incra.ProjectService;
import com.incra.ProjectType 
import com.incra.Resource 
import com.incra.ResourceService 
import com.incra.ResourceType 
import com.incra.TagUsageService;
import com.incra.TimeScale 
import com.incra.TimeSegment
import com.incra.TimeZone
import com.incra.TransactionService 
import com.incra.UnitOfMeasure
import com.incra.Preferences
import grails.util.Environment

/**
 * The <i>BootStrap</i> class has been extended to set up reference data (TimeZone, Language, UOM, etc.).
 *
 * @author Jeffrey Risberg
 * @since 09/29/10
 */
class BootStrap {
  
  def fixtureLoader
  ProjectService projectService
  AccountService accountService
  FlowService flowService
  TagUsageService tagUsageService
  ResourceService resourceService
  TransactionService transactionService
  
  def init = { servletContext ->
    switch (Environment.current) {
      case Environment.DEVELOPMENT:
      case Environment.TEST:
      case Environment.PRODUCTION:
      
      if (Language.count() == 0) fixtureLoader.load("languages")
      if (GeoScale.count() == 0) fixtureLoader.load("geoScales")
      if (TimeZone.count() == 0) fixtureLoader.load("timeZones")
      if (TimeSegment.count() == 0) fixtureLoader.load("timeSegments")
      if (UnitOfMeasure.count() == 0) fixtureLoader.load("uoms")
      
      // next candidates to become fixtures
      createEntityTypesIfRequired()
      createResourceTypesIfRequired()
      createResourcesIfRequired()
      
      if (Preferences.count() == 0) {
        println "Fresh Database. Creating preferences."
        
        GeoScale building = GeoScale.findByScale(1);
        GeoScale cluster = GeoScale.findByScale(2);
        GeoScale catchment = GeoScale.findByScale(3);
        TimeScale tsYear = TimeScale.findByScale(7);
        
        Preferences preferences
        
        preferences = new Preferences(geoScale: building, timeScale: tsYear)
        preferences.save()
        
        preferences = new Preferences(geoScale: cluster, timeScale: tsYear)
        preferences.save()
        
        preferences = new Preferences(geoScale: catchment, timeScale: tsYear)
        preferences.save()
      } else {
        println "Existing preferences, skipping creation"
      }
      
      createAccountTypesIfRequired()
      createProjectTypesIfRequired()
      
      createComponentsIfRequired()
    }
  }
  
  def destroy = {
  }  
  
  void createEntityTypesIfRequired() {
    if (EntityType.count() == 0) {
      println "Fresh Database. Creating entity types."
      
      EntityType entityType
      
      entityType = new EntityType(name: "Account", plural: "Accounts",
          hasOwnership: true, hasGeography: true,
          supportsComments: true, supportsTags: true)
      entityType.save()
      
      entityType = new EntityType(name: "Transaction", plural: "Transactions",
          hasOwnership: true, hasGeography: false,
          supportsComments: false, supportsTags: false);
      entityType.save()
      
      entityType = new EntityType(name: "Project", plural: "Projects",
          hasOwnership: true, hasGeography: false,
          supportsComments: true, supportsTags: true)
      entityType.save()
      
      entityType = new EntityType(name: "Flow", plural: "Flows",
          hasOwnership: true)
      entityType.save()
      
      entityType = new EntityType(name: "Provider", plural: "Providers",
          hasOwnership: false, hasGeography: true,
          supportsComments: true, supportsTags: true)
      entityType.save()
      
      entityType = new EntityType(name: "Vendor", plural: "Vendors",
          hasOwnership: false, hasGeography: true,
          supportsComments: true, supportsTags: true)
      entityType.save()
      
      entityType = new EntityType(name: "Partner", plural: "Partners",
          hasOwnership: false, hasGeography: true,
          supportsComments: true, supportsTags: true)
      entityType.save()
    }
    else {
      println "Existing entity types, skipping creation."
    }
  }
  
  void createResourceTypesIfRequired() {
    if (ResourceType.count() == 0) {
      println "Fresh Database. Creating resource types."
      
      ResourceType resourceType
      
      resourceType = new ResourceType(name: "Energy", ningUrl: "/page/energy-1");
      resourceType.save()
      resourceType = new ResourceType(name: "Water", ningUrl: "/page/water-1");
      resourceType.save()
      resourceType = new ResourceType(name: "Materials", ningUrl: "/page/materials-1");
      resourceType.save()
      resourceType = new ResourceType(name: "Sustainability", ningUrl: "/page/sustainability-1");
      resourceType.save()
      resourceType = new ResourceType(name: "Other");
      resourceType.save()
    }
    else {
      println "Existing resource types, skipping creation."
    }
  }
  
  void createResourcesIfRequired() {
    if (Resource.count() == 0) {
      println "Fresh Database. Creating resources."
      
      UnitOfMeasure uomKiloGallon = UnitOfMeasure.findByName("kgal")
      UnitOfMeasure uomKiloWattHour = UnitOfMeasure.findByName("kWh")
      UnitOfMeasure uomTherm =  UnitOfMeasure.findByName("therm")
      UnitOfMeasure uomMetricTon =  UnitOfMeasure.findByName("metric ton")
      
      ResourceType rtEnergy = ResourceType.get(1)
      ResourceType rtWater = ResourceType.get(2)
      ResourceType rtMaterial = ResourceType.get(3)
      ResourceType rtSustainability = ResourceType.get(4)
      ResourceType rtOther = ResourceType.get(5)
      Resource resource
      
      resource = new Resource(name: "Electricity", type: rtEnergy, uom: uomKiloWattHour);
      resource.save()
      resource = new Resource(name: "Natural Gas", type: rtEnergy, uom: uomTherm);
      resource.save()
      resource = new Resource(name: "Gasoline", type: rtEnergy, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Diesel", type: rtEnergy, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Freshwater", type: rtWater, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Greywater", type: rtWater, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Blackwater", type: rtWater, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Recycled Water", type: rtWater, uom: uomKiloGallon);
      resource.save()
      resource = new Resource(name: "Solid Waste", type: rtSustainability, uom: uomMetricTon);
      resource.save()
      resource = new Resource(name: "CO2e", type: rtSustainability, uom: uomMetricTon);
      resource.save()
      resource = new Resource(name: "Other", type: rtOther, uom: uomMetricTon);
      resource.save()
    }
    else {
      println "Existing resources, skipping creation."
    }
  }
  
  void createAccountTypesIfRequired() {
    if (AccountType.count() == 0) {
      println "Fresh Database. Creating account types."
      
      AccountType at
      
      at = new AccountType(name: "Group")
      at.save()
      at = new AccountType(name: "Building")
      at.save()
      at = new AccountType(name: "Component")
      at.save()
      at = new AccountType(name: "Administrative")
      at.save()
      at = new AccountType(name: "Manufacturing")
      at.save()
      at = new AccountType(name: "Data Center")
      at.save()
      at = new AccountType(name: "Recycling Center")
      at.save()
      at = new AccountType(name: "Recovery Device")
      at.save()
      at = new AccountType(name: "Customer")
      at.save()
    }
    else {
      println "Existing account type values, skipping creation."
    }
  }
  
  void createProjectTypesIfRequired() {
    if (ProjectType.count() == 0) {
      println "Fresh Database. Creating project types."
      
      ResourceType rtEnergy = ResourceType.get(1)
      ResourceType rtWater = ResourceType.get(2)
      ResourceType rtMaterials = ResourceType.get(3)
      ResourceType rtSustainability = ResourceType.get(4)
      ResourceType rtOther = ResourceType.get(5)
      ProjectType projectType
      
      projectType = new ProjectType(name: "Recycled Water", resourceType: rtWater,
          ningUrl: "/page/recycled-water", effectFrac: 0.5, description: "")
      projectType.save()
      
      projectType = new ProjectType(name: "Decentralized Water", resourceType: rtWater,
          ningUrl: "/page/decentralized-water", effectFrac: 0.5, description: "")
      projectType.save()
      
      projectType = new ProjectType(name: "Water Conservation", resourceType: rtWater,
          ningUrl: "/page/water-conservation", effectFrac: 0.5, description: "")
      projectType.save()
      
      projectType = new ProjectType(name: "Water Policy", resourceType: rtWater,
          ningUrl: "/page/water-policy-planning", effectFrac: 0.5, description: "")
      projectType.save()
      
      projectType = new ProjectType(name: "Materials Disposal", resourceType: rtMaterials,
          ningUrl: "/page/materials-disposal", effectFrac: 0.5, description: "")
      projectType.save()
      
      projectType = new ProjectType(name: "Materials Renew & Recycle", resourceType: rtMaterials,
          ningUrl: "/page/materials-renew-recycle", effectFrac: 0.5, description: "")
      projectType.save()
      
      projectType = new ProjectType(name: "Smart Grid", resourceType: rtEnergy,
          ningUrl: "/page/energy-smart-grid", effectFrac: 0.5, description: "")
      projectType.save()
      
      projectType = new ProjectType(name: "Energy Efficiency", resourceType: rtEnergy,
          ningUrl: "/page/energy-efficiency", effectFrac: 0.33, description: "")
      projectType.save()
      
      projectType = new ProjectType(name: "Energy Conservation", resourceType: rtEnergy,
          ningUrl: "/page/energy-conservation", effectFrac: 0.5, description: "")
      projectType.save()
      
      projectType = new ProjectType(name: "Energy Renewables", resourceType: rtEnergy,
          ningUrl: "/page/energy-renewables", effectFrac: 0.5, description: "")
      projectType.save()
    }
    else {
      println "Existing project types, skipping creation."
    }
  }
  
  void createComponentsIfRequired() {
    if (Module.count() == 0) {
      println "Fresh Database. Creating modules."
      
      Module module
      
      module = new Module(name: "Dashboard");
      module.save()
      module = new Module(name: "Recycled Water");
      module.save()
      module = new Module(name: "Urban Water Cycle");
      module.save()
      module = new Module(name: "Adminstration");
      module.save()
    }
    else {
      println "Existing modules, skipping creation."
    }
    
    if (MenuItem.count() == 0) {
      println "Fresh Database. Creating menu items."
      
      MenuItem menuItem
      
      menuItem = new MenuItem(seqNum:  10, name: "Dashboard", controller: 'dashboard');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  20, name: "Facilities", controller: 'account');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  30, name: "Map", controller: 'geomap');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  70, name: "Balances", controller: 'balance');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  40, name: "Projects", controller: 'project');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  50, name: "Partners", controller: 'partner');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  60, name: "Vendors", controller: 'vendor');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  80, name: "Preferences", controller: 'preferences');
      menuItem.save()
      menuItem = new MenuItem(seqNum:  90, name: "Administration", controller: 'administration', administrative: true);
      menuItem.save()
      menuItem = new MenuItem(seqNum: 100, name: "Help", controller: 'help');
      menuItem.save()
    }
    else {
      println "Existing menu items, skipping creation."
    }
  }
}		