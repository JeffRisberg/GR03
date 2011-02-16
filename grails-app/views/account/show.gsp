
<%@ page import="com.incra.Account" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName" value="Facility" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<div class="body">
			<h2>Facility Information</h2>
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
			<div class="dialog">
				<table>
					<tbody>

						<tr class="prop">
							<td colspan="99">
								<g:if test="${accountInstance.photo}">								
									<img src="
										<g:createLink action='renderImage'
											id='${accountInstance.id}' />" width="200" height="150" class="facilityImg"/>
								</g:if>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="account.name.label" default="Name" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: accountInstance,
								field: "name")}</td>
              <td valign="top" class="name">
                <g:message code="account.geoScale.label" default="GeoScale" />
              </td>
              <td valign="top" class="value">${accountInstance.geoScale.name}</td>
            </tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="account.type.label" default="Type" />
							</td>
							<td valign="top" class="value">
								${accountInstance?.type?.encodeAsHTML()}
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="account.address.label" default="Address" />
							</td>
							<td valign="top" class="value">${addressInstance}</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="account.latLong.label" default="Lat/Long" />
							</td>

							<td valign="top" class="value">
								${fieldValue(bean: accountInstance,
								field: "address.latitude")},
								${fieldValue(bean: accountInstance,
								field: "address.longitude")}
							</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="account.elevation.label" default="Elevation (m)" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: accountInstance,
								field: "address.elevation")}</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="account.tags.label" default="Tags" />
							</td>
							<td valign="top" class="value">${formattedTags}</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="account.description.label" default="Description" />
							</td>
							<td valign="top" class="value">${fieldValue(bean: accountInstance,
								field: "description")}</td>
						</tr>

						<tr class="prop">
							<td valign="top" class="name">
								<g:message code="account.projects.label" default="Projects" />
							</td>
							<td valign="top" style="text-align: left;" class="value">
								<ul>
									<g:each in="${accountInstance.projects}" var="p">
										<li>
											<g:link controller="project" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link>
										</li>
									</g:each>
								</ul>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="buttons">
				<g:form>
					<g:hiddenField name="id" value="${accountInstance?.id}" />
					<span class="button">
						<g:actionSubmit class="edit" action="edit"
							value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
					</span>
					<span class="button">
						<g:actionSubmit class="delete" action="delete"
							value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</span>
				</g:form>
			</div>
		</div>
	</body>
</html>
