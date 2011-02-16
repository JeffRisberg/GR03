

<%@ page import="com.incra.ResourcePrice" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'resourcePrice.label', default: 'ResourcePrice')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <calendar:resources lang="en" theme="green"/>
    </head>
    <body>
        <div class="body">
            <h2><g:message code="default.create.label" args="[entityName]" /></h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${resourcePriceInstance}">
            <div class="errors">
                <g:renderErrors bean="${resourcePriceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="resource"><g:message code="resourcePrice.resource.label" default="Resource" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourcePriceInstance, field: 'resource', 'errors')}">
                                    <g:select name="resource.id" from="${com.incra.Resource.list()}" optionKey="id" value="${resourcePriceInstance?.resource?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="provider"><g:message code="resourcePrice.provider.label" default="Provider" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourcePriceInstance, field: 'provider', 'errors')}">
                                    <g:select name="provider.id" from="${com.incra.Provider.list()}" optionKey="id" value="${resourcePriceInstance?.provider?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="price"><g:message code="resourcePrice.price.label" default="Price (\$/HCF)" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourcePriceInstance, field: 'price', 'errors')}">
                                    <g:textField name="price" value="${fieldValue(bean: resourcePriceInstance, field: 'price')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="validFromDate"><g:message code="resourcePrice.validFromDate.label" default="Valid From Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourcePriceInstance, field: 'validFromDate', 'errors')}">
                                    <calendar:datePicker name="validFromDate" value="${resourcePriceInstance?.validFromDate}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="validToDate"><g:message code="resourcePrice.validToDate.label" default="Valid To Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourcePriceInstance, field: 'validToDate', 'errors')}">
                                    <calendar:datePicker name="validToDate" value="${resourcePriceInstance?.validToDate}" />
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
