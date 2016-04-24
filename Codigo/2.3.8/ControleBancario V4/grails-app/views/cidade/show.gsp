
<%@ page import="br.ufscar.dc.dsw.Cidade" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'cidade.label', default: 'Cidade')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <r:require module="pure-all" />
    </head>
    <body>
        <a href="#show-cidade" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="pure-menu pure-menu-open pure-menu-horizontal">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                       <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>

                       <li>
                       <sec:access controller="cidade" action='create'>
                        <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
                            </sec:access>
                    </li>
        <li><g:link controller="logout">Logout</g:link></li>
                </ul>
            </div>
            <div id="show-cidade" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list cidade">
                
                <g:if test="${cidadeInstance?.nome}">
                    <li class="fieldcontain">
                        <span id="nome-label" class="property-label"><g:message code="cidade.nome.label" default="Nome" /></span>
                        
                        <span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${cidadeInstance}" field="nome"/></span>
                        
                    </li>
                </g:if>
                
                <g:if test="${cidadeInstance?.estado}">
                    <li class="fieldcontain">
                        <span id="estado-label" class="property-label"><g:message code="cidade.estado.label" default="Estado" /></span>
                        
                        <span class="property-value" aria-labelledby="estado-label"><g:link controller="estado" action="show" id="${cidadeInstance?.estado?.id}">${cidadeInstance?.estado?.encodeAsHTML()}</g:link></span>
                        
                    </li>
                </g:if>
                
            </ol>
            <g:form url="[resource:cidadeInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <sec:access controller="cidade" action='edit'>
                        <g:link class="edit" action="edit" resource="${cidadeInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        </sec:access>
                        <sec:access controller="cidade" action='delete'>
                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </sec:access>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
