

<%@ page import="com.incra.Project" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="layout" content="main" />
		<g:set var="entityName"
			value="${message(code: 'project.label', default: 'Project')}" />
		<title>
			<g:message code="default.edit.label" args="[entityName]" />
		</title>
		<g:javascript library="scriptaculous" />
		<calendar:resources lang="en" theme="green"/>
		<p:css name="bundle2" />
	  <script type="text/javascript" src="<g:createLinkTo dir='js' file='accordion.js' />"></script>
	</head>
	<body>
		<div class="body">
			<h2>
				<g:message code="default.edit.label" args="[entityName]" />
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
			<g:form method="post">
				<g:hiddenField name="id" value="${projectInstance?.id}" />
				<g:hiddenField name="version" value="${projectInstance?.version}" />
				<div class="dialog">
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
						<g:actionSubmit class="save" action="update"
							value="${message(code: 'default.button.update.label', default: 'Update')}" />
					</span>
					<span class="button">
						<g:actionSubmit class="delete" action="delete"
							value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</span>
				</div>
			</g:form>
		</div>
	</body>
</html>
