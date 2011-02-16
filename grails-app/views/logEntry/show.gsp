
<%@ page import="com.incra.LogEntry" %>
<html>
	<head>
		<title>Log Entry Viewer</title>
		<meta name="layout" content="skinfour_admin" />
	</head>
	<body>
		<div class="panel">
			<g:link class="list" action="list">Return to list</g:link>
			<h1>
				Log Entry
			</h1>
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
			<div class="dialog">
				<table>
					<tbody>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="logEntry.id.label" default="Id" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: logEntryInstance,
								field: "id")}</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="logEntry.user.label" default="User" />
							</td>
							<td valign="top" class="value">
								<g:link controller="user" action="show"
									id="${logEntryInstance?.user?.id}">${logEntryInstance?.user?.encodeAsHTML()}</g:link>
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="logEntry.key.label" default="Key" />
							</td>
							<td valign="top" class="value">${logEntryInstance?.key.label}</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="logEntry.parameter1.label" default="Parameter 1" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: logEntryInstance,
								field: "parameter1")}</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="logEntry.parameter2.label" default="Parameter 2" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: logEntryInstance,
								field: "parameter2")}</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="logEntry.parameter3.label" default="Parameter 3" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: logEntryInstance,
								field: "parameter3")}</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="logEntry.parameter4.label" default="Parameter 4" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: logEntryInstance,
								field: "parameter4")}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>
