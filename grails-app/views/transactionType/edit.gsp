

<%@ page import="com.incra.TransactionType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'transactionType.label', default: 'TransactionType')}" />
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
            <g:hasErrors bean="${transactionTypeInstance}">
            <div class="errors">
                <g:renderErrors bean="${transactionTypeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${transactionTypeInstance?.id}" />
                <g:hiddenField name="version" value="${transactionTypeInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="transactionCategory"><g:message code="transactionType.transactionCategory.label" default="Transaction Category" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionTypeInstance, field: 'transactionCategory', 'errors')}">
                                    <g:select name="transactionCategory.id" from="${com.incra.TransactionCategory.list()}" optionKey="id" value="${transactionTypeInstance?.transactionCategory?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="transactionType.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionTypeInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${transactionTypeInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="resourceType"><g:message code="transactionType.resourceType.label" default="Resource Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionTypeInstance, field: 'resourceType', 'errors')}">
                                    <g:select name="resourceType.id" from="${com.incra.ResourceType.list()}" optionKey="id" value="${transactionTypeInstance?.resourceType?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="transactionType.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionTypeInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${transactionTypeInstance?.description}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="transactions"><g:message code="transactionType.transactions.label" default="Transactions" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionTypeInstance, field: 'transactions', 'errors')}">
                                    
<ul>
<g:each in="${transactionTypeInstance?.transactions?}" var="t">
    <li><g:link controller="transaction" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="transaction" action="create" params="['transactionType.id': transactionTypeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'transaction.label', default: 'Transaction')])}</g:link>

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
