
<%@ page import="com.incra.Scenario" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'scenario.label', default: 'Scenario')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'scenario.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'scenario.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'scenario.dateCreated.label', default: 'Date Created')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'scenario.lastUpdated.label', default: 'Last Updated')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${scenarioInstanceList}" status="i" var="scenarioInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${scenarioInstance.id}">${fieldValue(bean: scenarioInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: scenarioInstance, field: "name")}</td>
                        
                            <td><g:formatDate date="${scenarioInstance.dateCreated}" /></td>
                        
                            <td><g:formatDate date="${scenarioInstance.lastUpdated}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${scenarioInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
