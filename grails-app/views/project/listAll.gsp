
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
	</head>
	<body>
		<div class="body">
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
			<div style="margin-top: 6px; margin-bottom: 3px">
			  <span style="font-weight: bold; font-size: 13px; color: gray;">View, Modify, Find and Create Projects at your Facilities</span>
			  <div class="menuButton" style="float: right; font-weight: bold">
						<g:link class="create" action="create">Create New Project</g:link>
			  </div>
			  <div style="clear:both"></div>
			</div>
			<g:form action="listAll" method="post">
				<i:filterGrid numColumns="2" content="${filters}" />
 			</g:form>
			<div class="list">
				<table>
					<thead>
						<tr>	
						  <g:sortableColumn property="user"
								title="${message(code: 'project.user.label', default: 'User')}" />					 
							<g:sortableColumn property="name"
								title="${message(code: 'project.name.label', default: 'Name')}" />
							<g:sortableColumn property="status"
								title="${message(code: 'project.status.label', default: 'Status')}" />						 
							<th>
								<g:message code="project.priority.label" default="Priority" />
							</th>
							<th>	
								<g:message code="project.type.label" default="Type" />
							</th>
							<th>
								<g:message code="project.account.label" default="Facility" />
							</th>		
							<g:sortableColumn property="description"
								title="${message(code: 'project.description.label', default: 'Description')}" />												
							<g:sortableColumn property="lastUpdated" title="Last Updated"/>
							<g:sortableColumn property="dateUpdated" title="Date Created"/>						
						</tr>
					</thead>
					<tbody>
						<g:each in="${projectInstanceList}" status="i"
							var="projectInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">					
								<td>${fieldValue(bean: projectInstance, field: "user")}</td>
								<td>
									<g:link action="show" id="${projectInstance.id}">${fieldValue(bean:
										projectInstance, field: "name")}</g:link>
								</td>
								<td>${fieldValue(bean: projectInstance, field: "status")}</td>
								<td><i:priorityLabel value="${projectInstance.priority}"/></td>
								<td>${fieldValue(bean: projectInstance, field: "type")}</td>
								<td>${fieldValue(bean: projectInstance, field: "account")}</td>
								<td>${fieldValue(bean: projectInstance, field: "description")}</td>								
								<td><g:formatDate value="${projectInstance.lastUpdated}" format="MM/dd/yyyy" /></td>
								<td><g:formatDate value="${projectInstance.dateCreated}" format="MM/dd/yyyy" /></td>								
						  </tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${projectInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
