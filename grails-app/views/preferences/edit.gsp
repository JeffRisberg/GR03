<html>
	<head>
		<title>Water Preferences</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		<div class="content">
		  <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <g:hasErrors bean="${preferencesInstance}">
        <div class="errors">
          <g:renderErrors bean="${preferencesInstance}" as="list" />
        </div>
      </g:hasErrors>
			<g:form method="post">
				<g:hiddenField name="id" value="${preferencesInstance?.id}" />
				<g:hiddenField name="version" value="${preferencesInstance?.version}" />
					<table>
						<tbody>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="userId">
										Planning Horizon
								</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: preferencesInstance, field: 'preferencesId', 'errors')}">
									<g:textField name="planningHorizon" maxlength="20"
										value="${preferencesInstance?.planningHorizon}" />
								</td>
							</tr>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="discountRate">
										Discount Rate
								</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: preferencesInstance, field: 'preferencesId', 'errors')}">
									<g:textField name="discountRate" maxlength="20"
										value="${preferencesInstance?.discountRate}" />
								</td>
							</tr>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="geoScale">
										Geographic Scale
								</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: preferencesInstance, field: 'preferencesId', 'errors')}">
									<g:select name="geoScale" from="${geoScales}" optionKey="id" optionValue="name" value="${preferencesInstance?.geoScale?.id}"  />
								</td>
							</tr>
							<tr class="prop">
								<td valign="top" class="name">
									<label for="timeScale">
										Time Scale
								</label>
								</td>
								<td valign="top"
									class="value ${hasErrors(bean: preferencesInstance, field: 'preferencesId', 'errors')}">
									<g:select name="timeScale" from="${timeScales}" optionKey="id" optionValue="name" value="${preferencesInstance?.timeScale?.id}"  />
								</td>
							</tr>
						</tbody>
					</table>
					<div class="buttons">
						<span class="button">
							<g:actionSubmit class="update" action="update" value="Update" />
						</span>
						<span class="button">
							<g:actionSubmit class="cancel" action="cancel" value="Cancel" />
						</span>
					</div>
				</g:form>
		</div>
	</body>
</html>