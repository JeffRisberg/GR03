
<%@ page import="com.incra.TransactionCategory" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'transactionCategory.label', default: 'TransactionCategory')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'transactionCategory.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'transactionCategory.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'transactionCategory.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'transactionCategory.dateCreated.label', default: 'Date Created')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'transactionCategory.lastUpdated.label', default: 'Last Updated')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${transactionCategoryInstanceList}" status="i" var="transactionCategoryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${transactionCategoryInstance.id}">${fieldValue(bean: transactionCategoryInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: transactionCategoryInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: transactionCategoryInstance, field: "description")}</td>
                        
                            <td><g:formatDate date="${transactionCategoryInstance.dateCreated}" /></td>
                        
                            <td><g:formatDate date="${transactionCategoryInstance.lastUpdated}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${transactionCategoryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
