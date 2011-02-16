

<%@ page import="com.incra.Transaction" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
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
            <g:hasErrors bean="${transactionInstance}">
            <div class="errors">
                <g:renderErrors bean="${transactionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startDate"><g:message code="transaction.startDate.label" default="Start Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'startDate', 'errors')}">
                                    <g:datePicker name="startDate" precision="day" value="${transactionInstance?.startDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endDate"><g:message code="transaction.endDate.label" default="End Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'endDate', 'errors')}">
                                    <g:datePicker name="endDate" precision="day" value="${transactionInstance?.endDate}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="account"><g:message code="transaction.account.label" default="Account" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'account', 'errors')}">
                                    <g:select name="account.id" from="${com.incra.Account.list()}" optionKey="id" value="${transactionInstance?.account?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type"><g:message code="transaction.type.label" default="Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'type', 'errors')}">
                                    <g:select name="type.id" from="${com.incra.TransactionType.list()}" optionKey="id" value="${transactionInstance?.type?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="resource"><g:message code="transaction.resource.label" default="Resource" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'resource', 'errors')}">
                                    <g:select name="resource.id" from="${com.incra.Resource.list()}" optionKey="id" value="${transactionInstance?.resource?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="transaction.amount.label" default="Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${fieldValue(bean: transactionInstance, field: 'amount')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="uom"><g:message code="transaction.uom.label" default="Uom" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'uom', 'errors')}">
                                    <g:select name="uom.id" from="${com.incra.UnitOfMeasure.list()}" optionKey="id" value="${transactionInstance?.uom?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="transaction.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${transactionInstance?.description}" />
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
