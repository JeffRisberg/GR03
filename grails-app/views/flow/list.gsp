
<%@ page import="com.incra.Flow" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName" value="${message(code: 'flow.label', default: 'Flow')}" />
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
			  <span style="font-weight: bold; font-size: 13px; color: gray;">View, Modify, Find and Create your inter-Facility Flows</span>
			  <div class="menuButton" style="float: right; font-weight: bold">				
						<g:link class="create" action="create">Create New Flow</g:link>
				</div>
				<div style="clear:both"></div>
			</div>
			<g:form action="list" method="post">
				<i:filterGrid numColumns="2" content="${filters}" />
 			</g:form>
			<div class="list">
				<table>
					<thead>
						<tr>
							<g:sortableColumn property="id"
								title="${message(code: 'flow.id.label', default: 'Id')}" />
							<th>
								<g:message code="flow.fromAccount.label" default="From Facility" />
							</th>
							<th>
								<g:message code="flow.toAccount.label" default="To Facility" />
							</th>
							<g:sortableColumn property="amount"
								title="${message(code: 'flow.amount.label', default: 'Amount')}" />
							<g:sortableColumn property="resourceUOM"
								title="${message(code: 'flow.resourceUOM.label', default: 'Resource UOM')}" />
							<g:sortableColumn property="resourceName"
								title="${message(code: 'flow.resourceName.label', default: 'Resource Name')}" />
						</tr>
					</thead>
					<tbody>
						<g:each in="${flowInstanceList}" status="i" var="flowInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>
									<g:link action="show" id="${flowInstance.id}">${fieldValue(bean:
										flowInstance, field: "id")}</g:link>
								</td>
								<td>${fieldValue(bean: flowInstance, field: "fromAccount")}</td>
								<td>${fieldValue(bean: flowInstance, field: "toAccount")}</td>
								<td>${fieldValue(bean: flowInstance, field: "amount")}</td>
								<td>${fieldValue(bean: flowInstance, field: "uom")}</td>
								<td>${fieldValue(bean: flowInstance, field: "resource")}
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${flowInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
