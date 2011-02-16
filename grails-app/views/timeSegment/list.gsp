
<%@ page import="com.incra.TimeSegment" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"
			value="${message(code: 'timeSegment.label', default: 'TimeSegment')}" />
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
							Create New Time Segment
				    </g:link>
					</span>
				</div>
			</g:form>
			<div class="list">
				<table>
					<thead>
						<tr>
							<g:sortableColumn property="id"
								title="${message(code: 'timeSegment.id.label', default: 'Id')}" />
							<th>
								<g:message code="timeSegment.timeScale.label" default="Time Scale" />
							</th>
							<g:sortableColumn property="label"
								title="${message(code: 'timeSegment.label.label', default: 'Label')}" />
							<g:sortableColumn property="fromDate"
								title="${message(code: 'timeSegment.fromDate.label', default: 'From Date')}" />
							<g:sortableColumn property="toDate"
								title="${message(code: 'timeSegment.toDate.label', default: 'To Date')}" />
							<g:sortableColumn property="dateCreated"
								title="${message(code: 'timeSegment.lastUpdated.label', default: 'Last Updated')}" />
						</tr>
					</thead>
					<tbody>
						<g:each in="${timeSegmentInstanceList}" status="i"
							var="timeSegmentInstance">
							<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
								<td>
									<g:link action="show" id="${timeSegmentInstance.id}">${fieldValue(bean:
										timeSegmentInstance, field: "id")}</g:link>
								</td>
								<td>${fieldValue(bean: timeSegmentInstance, field: "timeScale")}
								</td>
								<td>${fieldValue(bean: timeSegmentInstance, field: "label")}
								</td>
								<td>
									<g:formatDate format="MM/dd/yyyy" date="${timeSegmentInstance.fromDate}" />
								</td>
								<td>
									<g:formatDate format="MM/dd/yyyy" date="${timeSegmentInstance.toDate}" />
								</td>
								<td>
									<g:formatDate date="${timeSegmentInstance.lastUpdated}" />
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
			<div class="paginateButtons">
				<g:paginate total="${timeSegmentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
