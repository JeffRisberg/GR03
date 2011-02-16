

<%@ page import="com.incra.ProjectType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectType.label', default: 'ProjectType')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectTypeInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectTypeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="projectType.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectTypeInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${projectTypeInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="projectType.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectTypeInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${projectTypeInstance?.description}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="effectFrac"><g:message code="projectType.effectFrac.label" default="Effect Frac" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectTypeInstance, field: 'effectFrac', 'errors')}">
                                    <g:textField name="effectFrac" value="${fieldValue(bean: projectTypeInstance, field: 'effectFrac')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
