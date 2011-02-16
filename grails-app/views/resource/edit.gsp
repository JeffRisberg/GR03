

<%@ page import="com.incra.Resource" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'resource.label', default: 'Resource')}" />
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
            <g:hasErrors bean="${resourceInstance}">
            <div class="errors">
                <g:renderErrors bean="${resourceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${resourceInstance?.id}" />
                <g:hiddenField name="version" value="${resourceInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="resource.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourceInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${resourceInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="type"><g:message code="resource.type.label" default="Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourceInstance, field: 'type', 'errors')}">
                                    <g:select name="type.id" from="${com.incra.ResourceType.list()}" optionKey="id" value="${resourceInstance?.type?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="cost"><g:message code="resource.cost.label" default="Cost" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourceInstance, field: 'cost', 'errors')}">
                                    <g:textField name="cost" value="${fieldValue(bean: resourceInstance, field: 'cost')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="uom"><g:message code="resource.uom.label" default="Uom" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourceInstance, field: 'uom', 'errors')}">
                                    <g:select name="uom.id" from="${com.incra.UnitOfMeasure.list()}" optionKey="id" value="${resourceInstance?.uom?.id}"  />
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
