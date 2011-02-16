
<%@ page import="com.incra.TransactionType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'transactionType.label', default: 'TransactionType')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h2><g:message code="default.list.label" args="[entityName]" /></h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'transactionType.id.label', default: 'Id')}" />
                        
                            <th><g:message code="transactionType.transactionCategory.label" default="Transaction Category" /></th>
                        
                            <g:sortableColumn property="name" title="${message(code: 'transactionType.name.label', default: 'Name')}" />
                        
                            <th><g:message code="transactionType.resourceType.label" default="Resource Type" /></th>
                        
                            <g:sortableColumn property="description" title="${message(code: 'transactionType.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'transactionType.dateCreated.label', default: 'Date Created')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${transactionTypeInstanceList}" status="i" var="transactionTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${transactionTypeInstance.id}">${fieldValue(bean: transactionTypeInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: transactionTypeInstance, field: "transactionCategory")}</td>
                        
                            <td>${fieldValue(bean: transactionTypeInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: transactionTypeInstance, field: "resourceType")}</td>
                        
                            <td>${fieldValue(bean: transactionTypeInstance, field: "description")}</td>
                        
                            <td><g:formatDate date="${transactionTypeInstance.dateCreated}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${transactionTypeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
