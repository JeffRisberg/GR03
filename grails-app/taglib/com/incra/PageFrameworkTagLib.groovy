package com.incra


import com.incra.MenuItem
import com.incra.Preferences
import com.incra.Scenario
import com.incra.TimeScale
import com.incra.TimeSegment
import com.incra.pojo.DisplayFilterPojo;

/**
 * The <i>PageFrameworkTagLib</i> implements the tags used for page rendering and navigation, 
 * such as menus and filters.
 *
 * @author Jeffrey Risberg, Spoorthy Ananthaiah
 * @since 10/15/10
 */
class PageFrameworkTagLib {
  static namespace = "i"
  
  def pageFrameworkService
  
  def shortText = { attrs ->
    if (attrs["value"]) {
      String text = attrs["value"] as String
      int maxLength = 80
      
      if (attrs["maxLength"]) {
        maxLength = attrs["maxLength"] as Integer
      }
      
      int length = text.length()
      if (length > maxLength) {
        length = maxLength-5
        out << text.substring(0, length) + "..."
      }
      else {
        out << text
      }
    }
  }
  
  def priorityLabel = {attrs ->
    if (attrs["value"]) {
      int priority = attrs["value"] as Integer
      switch (attrs["value"] as Integer) {
        case 1: out << "High"; break;
        case 2: out << "Med-High"; break;
        case 3: out << "Medium"; break;
        case 4: out << "Med-Low"; break;
        case 5: out << "Low"; break;
        default: out << "Unknown"; break;
      }
    }
  }
  
  def prioritySelector = {attrs ->
    if (attrs['name']) {
      def value = attrs['value']
      
      out << """<select name="${attrs['name']}">"""
      out << "<option ${value == '1' ? 'selected ' : ''} value=1>High</option>"
      out << "<option ${value == '2' ? 'selected ' : ''} value=2>Med-High</option>"
      out << "<option ${value == '3' ? 'selected ' : ''} value=3>Medium</option>"
      out << "<option ${value == '4' ? 'selected ' : ''} value=4>Med-Low</option>"
      out << "<option ${value == '5' ? 'selected ' : ''} value=5>Low</option>"
      out << "</select>"
    }
  }
  
  def renderMenu = {
    def menuItems = MenuItem.findAll();
    
    out << '<ul class="navigation2">'
    
    for (MenuItem menuItem : menuItems) {
      if (menuItem.administrative == false || pageFrameworkService.userIsAdministrator(session)) {
        def title = menuItem.name?.toLowerCase()
        def cls = 'navigation2 '
        
        if (controllerName == menuItem.controller) {
          cls = 'navigation2_active '
        }
        
        out << '<li'
        if (cls) {
          out << " class=\"${cls.trim()}\""
        }
        out << '><a href="'
        out << createLink(controller: menuItem.controller, action: menuItem.action)
        out << '">'
        out << menuItem.name
        out << '</a></li>'
      }
    }
    
    out << '</ul>'
  }
  
  /**
   * This tag will generate the Scenario pulldown menu
   */
  def scenarioSelector = {
    if (pageFrameworkService.isScenarioSelectorVisible(session)) {
      List<Scenario> scenarios = Scenario.getAll()
      
      if (scenarios) {
        Scenario curScenario = session.scenario
        
        def controllerName = pageFrameworkService.getControllerName(session);
        out << "<div style='margin: 3px 5px 3px 0px; float: left'>"
        out << g.form(name: "scenarioForm", controller: controllerName, action: "changeScenario") {
          out << "<select name='scenarioId' onchange='scenarioForm.submit()'>"
          
          for (Scenario scenario : scenarios) {
            String name = scenario.name
            
            out << "<option value='${scenario.id}'"
            if (curScenario && scenario.id == curScenario.id) {
              out << " selected"
            }
            out << ">${name}</option>"
          }
          
          out << "</select>"
        }
        out << "</div>"
      }
    }
  }
  
  /**
   * This tag will generate the TimeSegment pulldown menu
   */
  def timeSegmentSelector = {
    if (pageFrameworkService.isTimeSegmentSelectorVisible(session)) {
      Preferences prefs = Preferences.get(1)
      TimeScale timeScale = prefs.timeScale
      def criteria = TimeSegment.createCriteria();
      def query = {
        order('fromDate', 'asc')
      }
      List<TimeSegment> timeSegments = criteria.list(params, query)
      
      TimeSegment curTimeSegment = session.timeSegment
      
      def controllerName = pageFrameworkService.getControllerName(session);
      out << "<div style='margin: 3px 5px 3px 0px; float: left'>"
      out << g.form(name: "timeSegmentForm", controller: controllerName, action: "changeTimeSegment") {
        out << "<select name='timeSegmentId' onchange='timeSegmentForm.submit()'>"
        
        for (TimeSegment timeSegment : timeSegments) {
          if (timeSegment.timeScale.id == timeScale.id) {
            out << "<option value='${timeSegment.id}'"
            if (curTimeSegment && timeSegment.id == curTimeSegment.id) {
              out << " selected"
            }
            out << ">${timeSegment.label}</option>"
          }
        }
        
        out << "</select>"
      }
      out << "</div>"
    }
  }
  
  /**
   * This tag will generate the Metrics pulldown menu
   */
  def metricsSelector = {
    if (pageFrameworkService.isMetricsSelectorVisible(session)) {
      
      int curMetric = session.metric
      
      def controllerName = pageFrameworkService.getControllerName(session);
      out << "<div style='margin: 3px 5px 3px 0px; float: left'>"
      out << g.form(name: "metricForm", controller: controllerName, action: "changeMetric") {
        out << "<select name='metric' onchange='metricForm.submit()'>"
        
        out << '<option value="1"'
        if (curMetric == 1) out << ' selected'
        out << '>Water</option>'
        
        out << '<option value="2"'
        if (curMetric == 2) out << ' selected'
        out << '>Energy</option>'
        
        out << '<option value="3"'
        if (curMetric == 3) out << ' selected'
        out << '>Cost</option>'
        
        out << "</select>"
      }
      out << "</div>"
    }
  }
  
  /**
   * Web 2.0 style dates, from the "Grails in Action" book
   */
  def dateFromNow = { attrs ->
    def date = attrs.date
    def niceDate = getNiceDate(date) // implement this somehow...
    out << niceDate
  }
  
  static final long second = 1000
  static final long minute = second * 60
  static final long hour = minute * 60
  static final long day = hour * 24
  
  static String getNiceDate(Date date) {
    def now = new Date()
    def diff = Math.abs(now.time - date.time)
    
    def niceTime = ""
    long calc = 0;
    calc = Math.floor(diff / day)
    if (calc) {
      niceTime += calc + "&nbsp;day" + (calc > 1 ? "s " : " ")
      diff %= day
    }
    calc = Math.floor(diff / hour)
    if (calc) {
      niceTime += calc + "&nbsp;hour" + (calc > 1 ? "s " : " ")
      diff %= hour
    }
    calc = Math.floor(diff / minute)
    if (calc) {
      niceTime += calc + "&nbsp;min" + (calc > 1 ? "s " : " ")
      diff %= minute
    }
    if (!niceTime) {
      niceTime = "Right now"
    } else {
      niceTime += (date.time > now.time) ? "from now" : ""
    }
    return niceTime
  }
  
  /**
   * This tag will generate the filter part of a list view.  Parameters are the
   * list of DisplayFilterPojo objects, and the number of columns (defaults to 3)
   */
  def filterGrid = { attrs, body ->
    List<DisplayFilterPojo> displayFilterPojos = attrs.content
    
    // Get the number of columns as a parameter
    int numColumns = attrs.numColumns ? attrs.numColumns as Integer : 0
    
    if (numColumns == 0) numColumns = 3
    
    out << '<div style="border: 1px solid #C0D9AF; font-size: 1.2em; margin-top: 0px; margin-bottom: 8px; padding-left: 5px; background: #C0D9AF;">'
    
    // Begin loop over filters
    int index = 0;
    for (DisplayFilterPojo dfp : displayFilterPojos) {
      
      def width = (int) Math.floor(100/numColumns)
      if (numColumns > 4) {
        width--
      }
      out << "<div style='float: left; width: ${width}%'>"
      
      String name = dfp.name
      String label = dfp.label
      Object value = flash[name]
      
      out << "<span class='name'>"
      out << "<label for='${name}'>${label}&nbsp;</label>"
      out << "</span>"
      
      out << "<span class='value'>"
      if (dfp.type == "String") {             
        if (numColumns > 4) {			
          out << g.textField(name: name, value: value, size: 13)
        }
        else {
          out << g.textField(name: name, value: value)
        }
      }
      else if (dfp.type == "Select") {
        def noSel = ['' : 'Select an item...']
        
        out << g.select(name: name, from: dfp.values, value: value, noSelection: noSel, optionKey: "id")
      } else if (dfp.type == 'Enumeration') {
        def noSel = ['' : 'Select an item...']
        
        out << g.select(name: name, from: dfp.values, value: value, noSelection: noSel, optionValue: "label")
      } else if (dfp.type == "CheckBox") {
        out << g.checkBox(name: name, value: value)
      } else {
        println "Unknown filter type " + dfp.type
      }
      out << "</span>"    
      
      // Determine if we should start a new row
      index++;
      out << "</div>"
      if ((index % numColumns) == 0) {
        out << '<div style="clear: both"></div>'
      }
    }
    // Always issue a clear after all filters are emitted 
    out << '<div style="clear: both"></div>'
    
    // Emit the "Search" button
    out << '<div class="d_buttons" style="margin: 0px 0px; background: #C0D9AF;">'
    out << '<input class="d_save" name="Search" style="font-size: 13px" type="submit" value="Search" />'
    out << '</div>'
    out << '</div>'
  }
}