<%@ page import="com.incra.LogEntry" %>

<html>
	<head>
		<title>Log Entry Viewer</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		<div class="panel" style="width: 960px">
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
			<g:form action="list" method="post">
				<i:filterGrid numColumns="3" content="${filters}" />
			</g:form>

			<div class="list">
				<table>
					<thead>
						<tr>
							<g:sortableColumn property="id"
								title="${message(code: 'logEntry.id.label', default: 'Id')}" />
							<g:sortableColumn property="startTimestamp"	title="When" />
							<th>Ago</th>
							<th>
								<g:message code="logEntry.user.label" default="User" />
						  </th>							
							<th>
								<g:message code="logEntry.key.label" default="Key" />
							</th>
							<th>
								<g:message code="logEntry.severity.label" default="Importance" />
							</th>						
							<g:sortableColumn property="parameter1"
								title="${message(code: 'logEntry.parameter1.label', default: 'Param&nbsp;1')}" />
							<g:sortableColumn property="parameter2"
								title="${message(code: 'logEntry.parameter2.label', default: 'Param&nbsp;2')}" />
							<g:sortableColumn property="parameter3"
								title="${message(code: 'logEntry.parameter3.label', default: 'Param&nbsp;3')}" />
								<g:sortableColumn property="parameter4"
								title="${message(code: 'logEntry.parameter4.label', default: 'Param&nbsp;4')}" />
						</tr>
					</thead>
					<tbody>
						<g:each in="${logEntryInstanceList}" status="i"
							var="logEntryInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>${fieldValue(bean:logEntryInstance, field: "id")}</td>
								<td><g:formatDate format="MM/dd/yyyy HH:mm:ss" date="${logEntryInstance.startTimestamp}" /></td>
								<td><i:dateFromNow date="${logEntryInstance.startTimestamp}" /></td>								
								<td><i:shortText value="${logEntryInstance?.user?.profile?.fullName?.encodeAsHTML()}" maxLength="22" /></td>								
								<td>${logEntryInstance.key?.label}</td>
								<td>${logEntryInstance.severity?.label}</td>								
								<td>${fieldValue(bean: logEntryInstance, field: "parameter1")}</td>
								<td>${fieldValue(bean: logEntryInstance, field: "parameter2")}</td>
								<td>${fieldValue(bean: logEntryInstance, field: "parameter3")}</td>
								<td>${fieldValue(bean: logEntryInstance, field: "parameter4")}</td>								
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${logEntryInstanceTotal}" params="${flash}" />
			</div>
		</div>
	</body>
</html>
