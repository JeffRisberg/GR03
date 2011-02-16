package com.incra

import com.incra.controller.SecureController 
import groovy.sql.Sql 

/**
 * The <i>DashboardController</i> class collects information for the dashboard display.
 *
 * @author Jeffrey Risberg
 * @since 09/20/10
 */
class DashboardController extends SecureController {
  
  def pageFrameworkService
  def dataSource
  
  /**
   * Show the dashboard panes.  If an optional id parameter is passed, it specifies 0 for tableView
   * and 1 for chartView.  The default is chartView.
   */
  def index = {
    pageFrameworkService.showScenarioSelector(session)
    pageFrameworkService.showTimeSegmentSelector(session)
    pageFrameworkService.showMetricsSelector(session)
    pageFrameworkService.setControllerName(session, "dashboard")
    
    Sql db = new Sql(dataSource)
    
    boolean chartView = true
    int itemCount = 5
    
    if (params.id != null) {
      chartView = (params.id == "1")
    }
    if (!chartView) itemCount = 9
    
    List<DashboardPanel> panels = new ArrayList<DashboardPanel>()
    
    if (true) {
      DashboardPanel panel = new DashboardPanel(title: "Facilities", chart: chartView)
      panel.addLeftRow("Facilities Record Count", "82", null)
      panel.addLeftRow("Facilities Record Sources", "4", null)
      
      panel.addRightRow("MV Bldg-2",  "88", 0)
      panel.addRightRow("MV Bldg-3",  "56", 0)
      panel.addRightRow("SD Bldg-10", "42", 0)
      panel.addRightRow("OR Bldg-11", "38", 0)
      
      panels.add(panel)
    }
    
    if (true) {
      DashboardPanel panel = new DashboardPanel(title: "Emissions", color: '#FF0000', chart: chartView)
      panel.addLeftRow("Emission Record Count", "78", null)
      panel.addLeftRow("Emission Record Sources", "12", null)
      
      panel.addRightRow("key1", "20", 0)
      panel.addRightRow("key2", "40", 0)
      panel.addRightRow("key3", "60", 0)
      panel.addRightRow("key4", "80", 0)
      
      panels.add(panel)
    }
    
    if (true) {
      DashboardPanel panel = new DashboardPanel(title: "Water", chart: chartView)
      panel.addLeftRow("Water Record Count", "111", null)
      panel.addLeftRow("Water Record Sources", "32", null)
      
      panel.addRightRow("key1", "66", 0)
      panel.addRightRow("key2", "44", 0)
      panel.addRightRow("key3", "55", 0)
      panel.addRightRow("key4", "52", 0)
      
      panels.add(panel)
    }
    
    if (true) {
      DashboardPanel panel = new DashboardPanel(title: "Energy", color: '#00FF66', chart: chartView)
      panel.addLeftRow("Energy Record Count", "251", null)
      panel.addLeftRow("Energy Record Sources", "19", null)
      
      panel.addRightRow("key1", "56", 0)
      panel.addRightRow("key2", "78", 0)
      panel.addRightRow("key3", "122", 0)
      panel.addRightRow("key4", "167", 0)
      
      panels.add(panel)
    }
    
    [ panels: panels ]
  }
  
  def drillDown = {
    if (session.dashhboardLastDrillDown) {
      session.dashhboardLastDrillDown.call()
    }
    else {
      detailsDrillDown()
    }
  }
  
  /**
   * Display information about the Account, including comments attached to it
   */
  def detailsDrillDown = {
    session.dashhboardLastDrillDown = detailsDrillDown
    
    def accountId = params.id as Long
    Account account = Account.get(accountId)
    
    if (account) {
      EntityType etAccount = EntityType.findByName("Account")
      
      List<Comment> comments = Comment.createCriteria().list() {
        and {
          eq('entityType', etAccount)
          eq('entityId', accountId)
        }
        order("dateCreated", "desc")
      }
      
      render(template: "/accountDetails",
      model: [account: account, comments: comments]);
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
      redirect(action: "list")
    }
  }
  
  def flowsDrillDown = {
    session.dashhboardLastDrillDown = flowsDrillDown
    
    def accountId = params.id as Long
    Account account = Account.get(accountId)
    
    if (account) {
      def curScenario = session.scenario;
      boolean isCurScenarioBaseline = (curScenario == null || curScenario.id == 1)
      
      def flows = Flow.findAll()
      
      def inboundFlowDashboardRows = []
      def outboundFlowDashboardRows = []
      flows.each {
        def fromAccount = it.fromAccount
        def toAccount = it.toAccount
        
        if (fromAccount.id.equals(accountId)) {
          if (toAccount.inBaseline || !isCurScenarioBaseline) {
            // add to outbound
            outboundFlowDashboardRows.add(
            new FlowDashboardRow(it.fromAccount, it.toAccount, it.amount, it.uom, it.resource))
          }
        }
        
        if (toAccount.id.equals(accountId)) {
          if (fromAccount.inBaseline || !isCurScenarioBaseline) {
            // add to inbound
            inboundFlowDashboardRows.add(
            new FlowDashboardRow(it.fromAccount, it.toAccount, it.amount, it.uom, it.resource))
          }
        }
      }
      
      render(template: "/accountFlows",
      model: [account: account,
        inboundFlows : inboundFlowDashboardRows,
        outboundFlows : outboundFlowDashboardRows]);
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
      redirect(action: "list")
    }
  }
  
  def transactionsDrillDown = {
    session.dashhboardLastDrillDown = transactionsDrillDown
    
    def accountId = params.id as Long
    Account account = Account.get(accountId)
    
    if (account) {
      def transactions = Transaction.findAll()
      
      def transactionDashboardRows = []
      transactions.each {
        if (it.account.id.equals(accountId)) {
          // add to sum
          transactionDashboardRows.add(
          new TransactionDashboardRow(it.account, it.type, it.amount, it.startDate, it.endDate, it.resource, it.uom))
        }
      }
      
      render(template: "/accountTransactions",
      model: [account: account, transactions: transactionDashboardRows]);
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
      redirect(action: "list")
    }
  }
  
  def projectsDrillDown = {
    session.dashhboardLastDrillDown = projectsDrillDown
    
    def accountId = params.id as Long
    Account account = Account.get(accountId)
    
    User currentUser = pageFrameworkService.getCurrentUser(session);
    
    if (account) {
      def projects = Project.findAll()
      
      def projectDashboardRows = []
      projects.each {
        if (it.account.id.equals(accountId)) {
          if (it.user == null || it.user.id == currentUser?.id) {
            // add to displayed set
            projectDashboardRows.add(
            new ProjectDashboardRow(it.id, it.name, it.account, it.type, it.resource,
            it.investment, it.reduction, it.roi, it.description, it.startDate, it.endDate))
          }
        }
      }
      
      render(template: "/accountProjects",
      model: [account: account, projects: projectDashboardRows]);
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
      redirect(action: "list")
    }
  }
  
  def changeTimeSegment = {
    TimeSegment timeSegment = TimeSegment.get(params.timeSegmentId)
    
    session.timeSegment = timeSegment
    redirect(action: "index")
  }
}
