

<%@ page import="com.incra.ResourceType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'resourceType.label', default: 'ResourceType')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${resourceTypeInstance}">
            <div class="errors">
                <g:renderErrors bean="${resourceTypeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${resourceTypeInstance?.id}" />
                <g:hiddenField name="version" value="${resourceTypeInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="resourceType.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourceTypeInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${resourceTypeInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="resourceType.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourceTypeInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${resourceTypeInstance?.description}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="resources"><g:message code="resourceType.resources.label" default="Resources" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourceTypeInstance, field: 'resources', 'errors')}">
                                    
<ul>
<g:each in="${resourceTypeInstance?.resources?}" var="r">
    <li><g:link controller="resource" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="resource" action="create" params="['resourceType.id': resourceTypeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'resource.label', default: 'Resource')])}</g:link>

                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
