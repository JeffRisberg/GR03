
<%@ page import="com.incra.ResourcePrice" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'resourcePrice.label', default: 'ResourcePrice')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h2><g:message code="default.show.label" args="[entityName]" /></h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="resourcePrice.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: resourcePriceInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="resourcePrice.resource.label" default="Resource" /></td>
                            
                            <td valign="top" class="value"><g:link controller="resource" action="show" id="${resourcePriceInstance?.resource?.id}">${resourcePriceInstance?.resource?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="resourcePrice.provider.label" default="Provider" /></td>
                            
                            <td valign="top" class="value"><g:link controller="provider" action="show" id="${resourcePriceInstance?.provider?.id}">${resourcePriceInstance?.provider?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="resourcePrice.price.label" default="Price (\$/HCF)" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: resourcePriceInstance, field: "price")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="resourcePrice.validFromDate.label" default="Valid From Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate format="MM/dd/yyyy" date="${resourcePriceInstance?.validFromDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="resourcePrice.validToDate.label" default="Valid To Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate format="MM/dd/yyyy" date="${resourcePriceInstance?.validToDate}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${resourcePriceInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
