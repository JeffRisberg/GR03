

<%@ page import="com.incra.Flow" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'flow.label', default: 'Flow')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h2><g:message code="default.edit.label" args="[entityName]" /></h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${flowInstance}">
            <div class="errors">
                <g:renderErrors bean="${flowInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${flowInstance?.id}" />
                <g:hiddenField name="version" value="${flowInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fromAccount"><g:message code="flow.fromAccount.label" default="From Account" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: flowInstance, field: 'fromAccount', 'errors')}">
                                    <g:select name="fromAccount.id" from="${accounts}" optionKey="id" value="${flowInstance?.fromAccount?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="toAccount"><g:message code="flow.toAccount.label" default="To Account" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: flowInstance, field: 'toAccount', 'errors')}">
                                    <g:select name="toAccount.id" from="${accounts}" optionKey="id" value="${flowInstance?.toAccount?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="amount"><g:message code="flow.amount.label" default="Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: flowInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${fieldValue(bean: flowInstance, field: 'amount')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="resource"><g:message code="flow.resource.label" default="Resource" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: flowInstance, field: 'resource', 'errors')}">
                                    <g:select name="resource.id" from="${com.incra.Resource.list()}" optionKey="id" value="${flowInstance?.resource?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="uom"><g:message code="flow.uom.label" default="UOM" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: flowInstance, field: 'uom', 'errors')}">
                                    <g:select name="uom.id" from="${com.incra.UnitOfMeasure.list()}" optionKey="id" value="${flowInstance?.uom?.id}"  />
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
