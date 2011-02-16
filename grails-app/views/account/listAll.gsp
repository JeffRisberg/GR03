
<%@ page import="com.incra.Account" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"	value="Facility" />
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
			  <span style="font-weight: bold; font-size: 13px; color: gray;">View, Modify, Find and Create your Buildings and Locations</span>
			  <div class="menuButton" style="float: right; font-weight: bold">
						<g:link class="create" action="create">Create New Facility</g:link>
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
								title="${message(code: 'account.user.label', default: 'User')}" />									
							<g:sortableColumn property="name"
								title="${message(code: 'account.name.label', default: 'Name')}" />
							<th>
								<g:message code="account.type.label" default="Type" />
							</th>
							<g:sortableColumn property="description"
								title="${message(code: 'account.description.label', default: 'Description')}" />							
							<th>City</th>							
							<th>State Code</th>
							<g:sortableColumn property="lastUpdated" title="Last Updated"/>
							<g:sortableColumn property="dateUpdated" title="Date Created"/>							
						</tr>
					</thead>
					<tbody>
						<g:each in="${accountInstanceList}" status="i"
							var="accountInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">			
							  <td>${fieldValue(bean: accountInstance, field: "user")}</td>					
								<td>
									<g:link action="show" id="${accountInstance.id}">${fieldValue(bean:
										accountInstance, field: "name")}</g:link>
								</td>								
								<td>${fieldValue(bean: accountInstance, field: "type")}</td>
								<td>${fieldValue(bean: accountInstance, field: "description")}</td>							
								<td>${fieldValue(bean: accountInstance, field: "address.city")}</td>								
								<td>${fieldValue(bean: accountInstance, field: "address.stateCode")}</td>														
								<td><g:formatDate value="${accountInstance.lastUpdated}" format="MM/dd/yyyy" /></td>
								<td><g:formatDate value="${accountInstance.dateCreated}" format="MM/dd/yyyy" /></td>								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${accountInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
