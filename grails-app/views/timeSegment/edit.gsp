

<%@ page import="com.incra.TimeSegment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'timeSegment.label', default: 'TimeSegment')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${timeSegmentInstance}">
            <div class="errors">
                <g:renderErrors bean="${timeSegmentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${timeSegmentInstance?.id}" />
                <g:hiddenField name="version" value="${timeSegmentInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="timeScale"><g:message code="timeSegment.timeScale.label" default="Time Scale" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeSegmentInstance, field: 'timeScale', 'errors')}">
                                    <g:select name="timeScale.id" from="${com.incra.TimeScale.list()}" optionKey="id" value="${timeSegmentInstance?.timeScale?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="label"><g:message code="timeSegment.label.label" default="Label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeSegmentInstance, field: 'label', 'errors')}">
                                    <g:textField name="label" value="${timeSegmentInstance?.label}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fromDate"><g:message code="timeSegment.fromDate.label" default="From Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeSegmentInstance, field: 'fromDate', 'errors')}">
                                    <g:datePicker name="fromDate" precision="day" value="${timeSegmentInstance?.fromDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="toDate"><g:message code="timeSegment.toDate.label" default="To Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: timeSegmentInstance, field: 'toDate', 'errors')}">
                                    <g:datePicker name="toDate" precision="day" value="${timeSegmentInstance?.toDate}"  />
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
