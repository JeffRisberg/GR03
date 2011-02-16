
<%@ page import="com.incra.Account" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="noheader" />
        <g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
         <style>
           body { font-family:"Lucida Grande","Lucida Sans Unicode",sans-serif; }
        </style>
    </head>
    <body>
        <div class="nav">
        </div>
        <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                        
                    <div>
                    <g:each in="${accountInstanceList}" status="i" var="accountInstance">
                         <div class="nowrapping">
								<g:if test="${accountInstance.photo}">								
									<g:link action="show" id="${accountInstance.id}"><img src="
										<g:createLink action='renderImage'
											id='${accountInstance.id}' />"
											width="90" height="14" class="textmiddle"/>
											  
									</g:link>
								</g:if>
								<g:link action="show" id="${accountInstance.id}">
									${fieldValue(bean:accountInstance, field: "name")}
								</g:link>
				        </div>
                    </g:each>
                    </div>
            
             
        </div>
    </body>
</html>
