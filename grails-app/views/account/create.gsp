

<%@ page import="com.incra.Account" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName" value="Facility"/>
		<title>
			<g:message code="default.create.label" args="[entityName]" />
		</title>
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	</head>
	<body>
	  <script type="text/javascript">
		  var geocoder;
		  
			function mapInitialize() {		
			 geocoder = new google.maps.Geocoder(); 
			}
			
			function geoEncode() { 
       var addressLine1 = $('addressLine1').value;
       var addressLine2 = $('addressLine2').value;
       var addressLine3 = $('addressLine3').value;
       var city = $('city').value;
       var stateCode = $('stateCode').value;
       var geoString = addressLine1;
       if (addressLine2) geoString += " " + addressLine2;
       if (addressLine3) geoString += " " + addressLine3;
       if (city) geoString += " " + city;
       if (stateCode) geoString += " " + stateCode;
       
       geocoder.geocode( { 'address': geoString }, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
          var location = results[0].geometry.location;
          // put in geocoded values
          $('latitude').value = " " + location.lat();
          $('longitude').value = " " + location.lng();
          $('accountForm').submit();
        } else { 
          // put in default values
          $('latitude').value = "37.32";
          $('longitude').value = "-121.93";
          $('accountForm').submit();       
        }
       });     
      }
		</script>
		<div class="body">
			<h2>
				<g:message code="default.create.label" args="[entityName]" />
			</h2>
			<h3>Describe physical structure, next go to creation of Projects.</h3>
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
			<div id="error" style="display:none;"></div>
			<g:uploadForm name="accountForm" action="save" onSubmit="geoEncode(); return false;">
			  <g:hiddenField name="latitude" value="37.32"/>
			  <g:hiddenField name="longitude" value='-121.93" />
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
									<g:textField size="50" name="name" value="${accountInstance?.name}" />
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
									<g:textField name="tags" size="90" />
								</td>
							</tr>

						</tbody>
					</table>
				</div>
				<div class="buttons">
					<span class="button">
						<g:submitButton name="create" class="save"
							value="${message(code: 'default.button.create.label', default: 'Create')}" />
					</span>
				</div>
			</g:uploadForm>
		</div>
		<script type="text/javascript">	
			mapInitialize();
	  </script>
	</body>
</html>
