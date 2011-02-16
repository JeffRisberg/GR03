
<%@ page import="com.incra.Transaction" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"
			value="${message(code: 'transaction.label', default: 'Transaction')}" />
		<title>
			<g:message code="default.list.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<div class="body">
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
			<g:form action="list" method="post">
				<i:filterGrid numColumns="2" content="${filters}" />

				<div class="buttons" style="margin-top: 0px; margin-bottom: 5px">
					<span class="button">
						<input class="save" type="submit" value="Search" />
					</span>
					<span class="menuButton">
						<g:link class="create" action="create">
							Create New Transaction
				    </g:link>
					</span>
				</div>
			</g:form>
			<div class="list">
				<table>
					<thead>
						<tr>
							<g:sortableColumn property="id"
								title="${message(code: 'transaction.id.label', default: 'Id')}" />
							<g:sortableColumn property="startDate"
								title="${message(code: 'transaction.startDate.label', default: 'Start Date')}" />
							<g:sortableColumn property="endDate"
								title="${message(code: 'transaction.endDate.label', default: 'End Date')}" />
							<th>
								<g:message code="transaction.account.label" default="Account" />
							</th>
							<th>
								<g:message code="transaction.type.label" default="Type" />
							</th>
							<th>
								<g:message code="transaction.resource.label" default="Resource" />
							</th>
							<th>
								<g:message code="transaction.amount.label" default="Amount" />
							</th>
							<th>
								<g:message code="transaction.uom.label" default="UOM" />
							</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${transactionInstanceList}" status="i"
							var="transactionInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>
									<g:link action="show" id="${transactionInstance.id}">${fieldValue(bean:
										transactionInstance, field: "id")}</g:link>
								</td>
								<td>
									<g:formatDate format="MM/dd/yyyy" date="${transactionInstance.startDate}" />
								</td>
								<td>
									<g:formatDate format="MM/dd/yyyy" date="${transactionInstance.endDate}" />
								</td>
								<td>${fieldValue(bean: transactionInstance, field: "account")}
								</td>
								<td>${fieldValue(bean: transactionInstance, field: "type")}</td>
								<td>${fieldValue(bean: transactionInstance, field: "resource")}								
								</td>
								<td>${fieldValue(bean: transactionInstance, field: "amount")}
								</td>
								<td>${fieldValue(bean: transactionInstance, field: "uom")}
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${transactionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
