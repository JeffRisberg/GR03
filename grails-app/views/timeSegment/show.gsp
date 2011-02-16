
<%@ page import="com.incra.TimeSegment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'timeSegment.label', default: 'TimeSegment')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeSegment.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: timeSegmentInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeSegment.timeScale.label" default="Time Scale" /></td>
                            
                            <td valign="top" class="value"><g:link controller="timeScale" action="show" id="${timeSegmentInstance?.timeScale?.id}">${timeSegmentInstance?.timeScale?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeSegment.label.label" default="Label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: timeSegmentInstance, field: "label")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeSegment.fromDate.label" default="From Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${timeSegmentInstance?.fromDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="timeSegment.toDate.label" default="To Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${timeSegmentInstance?.toDate}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${timeSegmentInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
