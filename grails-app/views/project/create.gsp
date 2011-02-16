

<%@ page import="com.incra.Project" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"
			value="${message(code: 'project.label', default: 'Project')}" />
		<title>
			<g:message code="default.create.label" args="[entityName]" />
		</title>
		<g:javascript library="scriptaculous" />
		<calendar:resources lang="en" theme="green"/>
		<script type="text/javascript" src="				
			<g:createLinkTo dir='js' file='accordion.js' />
			">
		</script>
		<link rel="stylesheet" href="					
			<g:createLinkTo dir='css' file='accordion.css' />
			" />
	</head>
	<body>
		<div class="body">
			<h2>
				<g:message code="default.create.label" args="[entityName]" />
			</h2>
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${projectInstance}">
				<div class="errors">
					<g:renderErrors bean="${projectInstance}" as="list" />
				</div>
			</g:hasErrors>
			<g:hasErrors bean="${roiProjectInstance}">
				<div class="errors">
					<g:renderErrors bean="${roiProjectInstance}" as="list" />
				</div>
			</g:hasErrors>
			<g:form action="save">
				<div class="dialog">
				<h4>Please create Facilities before creating Projects.</h4>
					<div id="test-accordion" class="accordion">
					  <g:each in="${panes}" var="pane">
						  <div class="accordion-toggle">${pane.label}</div>
						  <div class="accordion-content">
							  <g:render template="${pane.templateName}" model="[projectInstance: projectInstance, roiProjectInstance: roiProjectInstance]" />
						  </div>
						</g:each>
					</div>
				</div>
				<div class="buttons">
					<span class="button">
						<g:submitButton name="create" class="save"
							value="${message(code: 'default.button.create.label', default: 'Create')}" />
					</span>
				</div>
			</g:form>
		</div>
	</body>
</html>
