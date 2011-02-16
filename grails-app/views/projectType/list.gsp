
<%@ page import="com.incra.ProjectType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectType.label', default: 'ProjectType')}" />
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
                            <g:sortableColumn property="name" title="${message(code: 'projectType.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="resourceType" title="${message(code: 'projectType.resourceType.label', default: 'Resource&nbsp;Type')}" />
                        
                            <g:sortableColumn property="ningUrl" title="${message(code: 'projectType.ningUrl.label', default: 'Ning Url')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'projectType.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="effectFrac" title="${message(code: 'projectType.effectFrac.label', default: 'Effect Frac')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'projectType.dateCreated.label', default: 'Date Created')}" />
                                               
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectTypeInstanceList}" status="i" var="projectTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${projectTypeInstance.id}">${fieldValue(bean: projectTypeInstance, field: "name")}</g:link></td>                        
                            <td>${fieldValue(bean: projectTypeInstance, field: "resourceType")}</td>                        
                         		<td>${fieldValue(bean: projectTypeInstance, field: "ningUrl")}</td>                         		
                            <td>${fieldValue(bean: projectTypeInstance, field: "description")}</td>                        
                            <td>${fieldValue(bean: projectTypeInstance, field: "effectFrac")}</td>                        
                            <td><g:formatDate format="MM/dd/yyyy" date="${projectTypeInstance.dateCreated}" /></td>                                   
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${projectTypeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
