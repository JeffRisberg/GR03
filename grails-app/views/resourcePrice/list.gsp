
<%@ page import="com.incra.ResourcePrice" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'resourcePrice.label', default: 'ResourcePrice')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'resourcePrice.id.label', default: 'Id')}" />
                        
                            <th><g:message code="resourcePrice.resource.label" default="Resource" /></th>
                        
                            <th><g:message code="resourcePrice.provider.label" default="Provider" /></th>
                        
                            <g:sortableColumn property="price" title="${message(code: 'resourcePrice.price.label', default: 'Price ($/HCF)')}" />
                        
                            <g:sortableColumn property="validFromDate" title="${message(code: 'resourcePrice.validFromDate.label', default: 'Valid From Date')}" />
                        
                            <g:sortableColumn property="validToDate" title="${message(code: 'resourcePrice.validToDate.label', default: 'Valid To Date')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${resourcePriceInstanceList}" status="i" var="resourcePriceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${resourcePriceInstance.id}">${fieldValue(bean: resourcePriceInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: resourcePriceInstance, field: "resource")}</td>
                        
                            <td>${fieldValue(bean: resourcePriceInstance, field: "provider")}</td>
                        
                            <td style="text-align: right">${fieldValue(bean: resourcePriceInstance, field: "price")}&nbsp;&nbsp;</td>
                        
                            <td><g:formatDate format="MM/dd/yyyy" date="${resourcePriceInstance.validFromDate}" /></td>
                        
                            <td><g:formatDate format="MM/dd/yyyy" date="${resourcePriceInstance.validToDate}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${resourcePriceInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
