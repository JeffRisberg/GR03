<%@ page import="com.incra.UserDashboardPanel" %>
<html>
  <head>
    <title>Configure Dashboard Panel</title>
    <meta name="layout" content="skinfour_admin" />
  </head>
  <body>
    <div class="panel">     
      <g:link action="index">Return to Dashboard</g:link>
      <h1>Configure Dashboard Panel</h1>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <g:hasErrors bean="${userDashboardPanelInstance}">
        <div class="errors">
          <g:renderErrors bean="${userDashboardPanelInstance}" as="list" />
        </div>
      </g:hasErrors>
      <div style="float:left; width: 120px; margin-right: 10px">  
        <g:each in="${0..5}" var="i">      
          <div style="float: left; width: 40px; height: 40px; background: ${i == userDashboardPanelInstance.panelIndex ? 'red' : 'white'}; border: 2px solid #eee">
          </div>
        </g:each>          
      </div>
      <g:form method="post">
        <input type="hidden" name="id" value="${userDashboardPanelInstance?.id}" />
        <input type="hidden" name="version" value="${userDashboardPanelInstance?.version}" />
        <div class="container" style="width: 600px">
          <table>
            <tbody>

              <tr class="prop">
                <td valign="top" class="name">
                  <label for="label">Label:</label>
                </td>
                <td valign="top"
                  class="value ${hasErrors(bean:userDashboardPanelInstance,field:'label','errors')}">
                  <g:textField size="50" name="label" value="${userDashboardPanelInstance.label}" />
                </td>
              </tr>

              <tr class="prop">
                <td valign="top" class="name">
                  <label for="metric">Metric:</label>
                </td>
                <td valign="top"
                  class="value ${hasErrors(bean:userDashboardPanelInstance,field:'viveMetric','errors')}">
                  <g:select style="width:340px;" name="metric" 
                    value="${userDashboardPanelInstance.viveMetric?.id}" 
                    optionValue="description" optionKey="id" from="${metrics}" />
                </td>
              </tr>

              <tr class="prop">
                <td valign="top" class="name">
                  <label for="rangeType">Historical Period:</label>
                </td>
                <td valign="top"
                  class="value ${hasErrors(bean:userDashboardPanelInstance,field:'rangeType','errors')}">                  
                  <g:select name="rangeType" id="rangeType" from='${rangeTypes}'
                   value="${userDashboardPanelInstance.rangeType}" />
                </td>
              </tr>  
             
              <tr class="prop">
                <td valign="top" class="name">
                  <label for="chartType">Display As:</label>
                </td>
                <td valign="top"
                  class="value ${hasErrors(bean:userDashboardPanelInstance,field:'chartType','errors')}">
                  <g:select name="chartType" optionValue="label"
                     value="${userDashboardPanelInstance.chartType}" from="${chartTypes}" />
                </td>
              </tr>

            </tbody>
          </table>
        </div>
        <div class="d_buttons" style="width: 600px">
          <g:actionSubmit class="d_save" action="configureUpdate" value="Update" />         
        </div>
      </g:form>
    </div>
    
    <div class="jqmWindow" id="ex2"></div>
    <script type="text/javascript">
      $().ready(function() {
        $('#ex2').jqm({ajax:'@href', trigger: 'a.ex2trigger'});
      });
    </script>
  </body>
</html>
