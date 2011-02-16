<html>
	<head>
		<title>Water Preferences</title>
		<meta name="layout" content="main" />
	</head>
	<body>
		<div class="content">
		<div style="margin-top: 8px; margin-bottom: 5px">
		  <span style="font-weight: bold; font-size: 13px; color: gray;">
		    View and Set Preferences such as Scales and Time horizon.  Most of these fields are used only in Phase II
		  </span>
		</div>
		 <div class="nav">
				<span class="menuButton">
					<g:link class="edit" action="edit">Edit</g:link>
				</span>
			</div>
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
							${preferencesInstance?.planningHorizon} years
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
							${preferencesInstance?.discountRate} % </td>
					</tr>
					<tr class="prop">
						<td valign="top" class="name">
							<label for="geoScale">
								Geographic Scale
								</label>
						</td>
						<td valign="top">
							${preferencesInstance?.geoScale.name}
							</td>
					</tr>
					<tr class="prop">
						<td valign="top" class="name">
							<label for="timeScale">
								Time Scale
								</label>
						</td>
						<td valign="top">
							${preferencesInstance?.timeScale.name}
							</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>