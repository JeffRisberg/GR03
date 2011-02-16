
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
			  <g:if test="${userIsLoggedIn}">
			    <div class="menuButton" style="float: right; font-weight: bold">
						<g:link class="create" action="create">Create New Facility</g:link>
			    </div>
			  </g:if>
			  <div style="clear:both"></div>
			</div>
			<g:form name="filterForm" action="list" method="post">
				<i:filterGrid numColumns="3" content="${filters}" />
 			</g:form>
 			<div>Current GeoScale: ${geoScale.name}</div>
			<div class="list">
				<table>
					<thead>
						<tr>								 									
							<g:sortableColumn property="name"
								title="${message(code: 'account.name.label', default: 'Facility')}" style="width:25%"/>
							<th>
								<g:message code="account.type.label" default="Type" />
							</th>
							<th>Children</th>
							<g:sortableColumn property="description"
								title="${message(code: 'account.description.label', default: 'Description')}" />							
							<th>City</th>							
							<th>State&nbsp;Code</th>
							<th>Lat/Long</th>
							<th>Elevation</th>							
						</tr>
					</thead>
					<tbody>
						<g:each in="${accountInstanceList}" status="i"
							var="accountInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td class="nowrapping">
								<g:if test="${accountInstance.photo}">								
									<g:link action="show" id="${accountInstance.id}"><img src="
										<g:createLink action='renderImage'
											id='${accountInstance.id}' />"
											width="40" height="30" class="textmiddle"/>											  
									</g:link>
								</g:if>
								<g:link action="show" id="${accountInstance.id}">
									${fieldValue(bean:accountInstance, field: "name")}
								</g:link>						
								<g:link action="edit" id="${accountInstance.id}">
									<p:image src="skin/database_edit.png" alt="Edit" />
								</g:link>							
								</td>								
								<td>${fieldValue(bean: accountInstance, field: "type")}</td>
								<td><g:link action="showChildren" id="${accountInstance.id}">Show</g:link></td>
								<td>${fieldValue(bean: accountInstance, field: "description")}</td>	
								<td>${fieldValue(bean: accountInstance, field: "address.city")}</td>								
								<td>${fieldValue(bean: accountInstance, field: "address.stateCode")}</td>														
								<td>${fieldValue(bean: accountInstance, field: "address.latitude")}, 
								    ${fieldValue(bean: accountInstance, field: "address.longitude")}</td>
								<td>${fieldValue(bean: accountInstance, field: "address.elevation")}</td>								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${accountInstanceTotal}" params="[tag: params.tag]" />
			</div>
		</div>
		
	</body>
</html>
