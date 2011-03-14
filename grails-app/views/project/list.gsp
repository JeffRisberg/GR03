<%@ page import="com.incra.Project" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName"
      value="${message(code: 'project.label', default: 'Project')}" />
    <title>
      <g:message code="default.list.label" args="[entityName]" />
    </title>
    <p:css name="jqModal" /> 
    <p:javascript src="jqModal" />
    <script type="text/javascript">
       $().ready(function() {
        $('#ex2').jqm({ajax:'@href', trigger: 'a.ex2trigger'});
      });
    </script>
  </head>
  <body>
    <div class="body">
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <div style="margin-top: 6px; margin-bottom: 3px">
        <span style="font-weight: bold; font-size: 13px; color: gray;">View, Modify, Find and Create Projects at your Facilities</span>
        <g:if test="${userIsLoggedIn}">
          <div class="menuButton" style="float: right; font-weight: bold">
            <g:link class="create" action="create">Create New Project</g:link>
          </div>
        </g:if>
        <div style="clear:both"></div>
      </div>
      <g:form name="filterForm" action="list" method="post">
        <i:filterGrid numColumns="3" content="${filters}" />
      </g:form>
      <div class="list">
        <table>
          <thead>
            <tr>             
              <g:sortableColumn property="name"
                title="${message(code: 'project.name.label', default: 'Name')}" />
              <g:sortableColumn property="status"
                title="${message(code: 'project.status.label', default: 'Status')}" />             
              <g:sortableColumn property="priority" title="Priority" />
              <th>  
                <g:message code="project.type.label" default="Type" />
              </th>
              <th>
                <g:message code="project.account.label" default="Facility" />
              </th>   
              <g:sortableColumn property="description"
                title="${message(code: 'project.description.label', default: 'Description')}" />                        
              <g:sortableColumn property="budget" title="Budget (\$)" />
              <g:sortableColumn property="capitalInvest" title="Capital Invest" />
              <g:sortableColumn property="monthlySavings" title="Monthly Savings" />
              <g:sortableColumn property="paybackMonths" title="Payback (months)" />  
              <g:sortableColumn property="roi" title="ROI %" /> 
              <th>  
                 <g:message code="project.account.label" default="Summary" />
            </th>
            </tr>
          </thead>
          <tbody>
            <g:each in="${projectInstanceList}" status="i" var="projectInstance">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">         
                <td>
                <g:link action="show" id="${projectInstance.id}">${fieldValue(bean:
                    projectInstance, field: "name")}
                </g:link>
                <g:link action="edit" id="${projectInstance.id}">
                  <p:image src="skin/database_edit.png" alt="Edit" />
                </g:link>   
                </td>
                <td>${fieldValue(bean: projectInstance, field: "status")}</td>
                <td><i:priorityLabel value="${projectInstance.priority}"/></td>
                <td>${fieldValue(bean: projectInstance, field: "type")}</td>
                <td>${fieldValue(bean: projectInstance, field: "account")}</td>
                <td>${fieldValue(bean: projectInstance, field: "description")}</td>               
                <td style="text-align: right;"><g:formatNumber number="${projectInstance.budget}" format="###,###,###" /></td>
                <td style="text-align: right;"><g:formatNumber number="${projectInstance.capitalInvest}" format="###,###,###" /></td>
                <td style="text-align: right;"><g:formatNumber number="${projectInstance.monthlySavings}" format="###,###,###.00" /></td> 
                <td style="text-align: right;"><g:formatNumber number="${projectInstance.paybackMonths}" format="#.00" /></td>  
                <td style="text-align: right;"><g:formatNumber number="${projectInstance.roi}" format="###" /></td>
                <td>
                 
                <g:link action="summary" id="${projectInstance.id}" class="ex2trigger">
                  <p:image src="summarize.png" alt="Summarize" />
                </g:link>   
                <div class="jqmWindow" id="ex2"></div>                      
                </td>               
              </tr>
            </g:each>
          </tbody>
        </table>
      </div>
      <div class="paginateButtons">
        <g:paginate total="${projectInstanceTotal}" params="[tag: params.tag]" />
      </div>
    </div>
  </body>
</html>
