
<%@ page import="com.incra.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h2>Tools Server User Administration</h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>                        
                            <g:sortableColumn property="userId" title="${message(code: 'user.userId.label', default: 'User Id')}" />                        
                            <th>Full Name</th>  
                            <th>Roles</th>                        
                            <g:sortableColumn property="lastLogin" title="${message(code: 'user.lastLogin.label', default: 'Last Login')}" />                        
                            <g:sortableColumn property="loginCount" title="${message(code: 'user.loginCount.label', default: 'Login Count')}" />                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'user.dateCreated.label', default: 'Date Created')}" />                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userInstanceList}" status="i" var="userInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">                        
                            <td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "userId")}</g:link></td>                                                                        
                            <td>${userInstance.profile.fullName}</td>                        
                            <td>${userInstance.roleNames()}</td> 
                            <td><g:formatDate date="${userInstance.lastLogin}" /></td>                        
                            <td>${fieldValue(bean: userInstance, field: "loginCount")}</td>                        
                            <td><g:formatDate date="${userInstance.dateCreated}" /></td>                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${userInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
