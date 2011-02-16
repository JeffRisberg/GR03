
<%@ page import="com.incra.ResourceType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'resourceType.label', default: 'ResourceType')}" />
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
                                                                    
                            <g:sortableColumn property="name" title="${message(code: 'resourceType.name.label', default: 'Name')}" />
                        
                        		<g:sortableColumn property="ningUrl" title="${message(code: 'resourceType.ningUrl.label', default: 'Ning Url')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'resourceType.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'resourceType.dateCreated.label', default: 'Date Created')}" />                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${resourceTypeInstanceList}" status="i" var="resourceTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${resourceTypeInstance.id}">${fieldValue(bean: resourceTypeInstance, field: "name")}</g:link></td>                           
                            <td>${fieldValue(bean: resourceTypeInstance, field: "ningUrl")}</td>                                                
                            <td>${fieldValue(bean: resourceTypeInstance, field: "description")}</td>                        
                            <td><g:formatDate format="MM/dd/yyyy" date="${resourceTypeInstance.dateCreated}" /></td>                                                 
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${resourceTypeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
