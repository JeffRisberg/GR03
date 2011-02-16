
<%@ page import="com.incra.AccountType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'accountType.label', default: 'AccountType')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'accountType.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'accountType.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'accountType.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'accountType.dateCreated.label', default: 'Date Created')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'accountType.lastUpdated.label', default: 'Last Updated')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${accountTypeInstanceList}" status="i" var="accountTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${accountTypeInstance.id}">${fieldValue(bean: accountTypeInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: accountTypeInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: accountTypeInstance, field: "description")}</td>
                        
                            <td><g:formatDate date="${accountTypeInstance.dateCreated}" /></td>
                        
                            <td><g:formatDate date="${accountTypeInstance.lastUpdated}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${accountTypeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
