

<%@ page import="com.incra.Account" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName" value="Facility" />
		<title>
			<g:message code="default.edit.label" args="[entityName]" />
		</title>
	</head>
	<body>
		<div class="body">
			<h2>
				<g:message code="default.edit.label" args="[entityName]" />
			</h2>
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${accountInstance}">
				<div class="errors">
					<g:renderErrors bean="${accountInstance}" as="list" />
				</div>
			</g:hasErrors>
			<g:hasErrors bean="${addressInstance}">
				<div class="errors">
					<g:renderErrors bean="${addressInstance}" as="list" />
				</div>
			</g:hasErrors>
			<g:uploadForm method="post">
				<g:hiddenField name="id" value="${accountInstance?.id}" />
				<g:hiddenField name="version" value="${accountInstance?.version}" />
				<div class="dialog">
					<table>
						<tbody>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="photo">
										<g:message code="account.photo.label" default="Photo" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: accountInstance, field: 'photo', 'errors')}">
									<input type="file" id="photo" name="photo" />
								</td>
							</tr>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="name">
										<g:message code="account.name.label" default="Name" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: accountInstance, field: 'name', 'errors')}">
									<g:textField size="50" name="name"
										value="${accountInstance?.name}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="type">
										<g:message code="account.type.label" default="Type" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: accountInstance, field: 'type', 'errors')}">
									<g:select name="type.id" from="${accountTypes}"
										optionKey="id" value="${accountInstance?.type?.id}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="addressLine1">
										<g:message code="account.addressline1.label"
											default="Address Line 1" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'addressLine1', 'errors')}">
									<g:textField size="40" name="addressLine1"
										value="${addressInstance?.addressLine1}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="addressLine2">
										<g:message code="account.addressLine2.label"
											default="Address Line 2" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'addressLine2', 'errors')}">
									<g:textField size="40" name="addressLine2"
										value="${addressInstance?.addressLine2}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="addressLine3">
										<g:message code="account.addressLine3.label"
											default="Address Line 3" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'addressLine3', 'errors')}">
									<g:textField size="40" name="addressLine3"
										value="${addressInstance?.addressLine3}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="city">
										<g:message code="account.city.label" default="City" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'city', 'errors')}">
									<g:textField name="city" value="${addressInstance?.city}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="stateCode">
										<g:message code="account.stateCode.label" default="State Code" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'stateCode', 'errors')}">
									<g:textField name="stateCode" value="${addressInstance?.stateCode}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="postalCode">
										<g:message code="account.postalCode.label" default="Postal Code" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'postalCode', 'errors')}">
									<g:textField name="postalCode" value="${addressInstance?.postalCode}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="latitude">
										<g:message code="account.latitude.label" default="Latitude" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'latitude', 'errors')}">
									<g:textField name="latitude" value="${addressInstance?.latitude}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="latitude">
										<g:message code="account.longitude.label" default="Longitude" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'longitude', 'errors')}">
									<g:textField name="longitude" value="${addressInstance?.longitude}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="elevation">
										<g:message code="account.elevation.label" default="Elevation (m)" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: addressInstance, field: 'elevation', 'errors')}">
									<g:textField name="elevation" value="${addressInstance?.elevation}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="tags">
										<g:message code="account.tags.label" default="Tags" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: accountInstance, field: 'tags', 'errors')}">
									<g:textField name="tags" value="${formattedTags}"
										size="90" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="description">
										<g:message code="account.description.label" default="Description" />
									</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: accountInstance, field: 'description', 'errors')}">
									<g:textArea style="height: 50px !important; width: 550px !important;"
										name="description" cols="60" rows="3"
										value="${accountInstance?.description}" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="buttons">
					<span class="button">
						<g:actionSubmit class="save" action="update"
							value="${message(code: 'default.button.update.label', default: 'Update')}" />
					</span>
					<span class="button">
						<g:actionSubmit class="delete" action="delete"
							value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</span>
				</div>
			</g:uploadForm>
		</div>
	</body>
</html>
