<%@ page import="br.ufscar.dc.dsw.ClienteFisico" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'clienteFisico.label', default: 'ClienteFisico')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <g:javascript src="jquery-1.8.3.min.js"/>
        <g:javascript src="jquery.maskedinput.min.js"/> 
        <g:javascript>
            var JQuery = jQuery.noConflict()
            JQuery(document).ready(function(){
                JQuery("#CPF").mask("999.999.999-99");
                JQuery("#CNPJ").mask("99.999.999/9999-99");
                JQuery("#CEP").mask("99999-999");
            });
        </g:javascript>
    </head>
    <body>
        <a href="#edit-clienteFisico" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                       <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                       <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                           </ul>
                           </div>
                           <div id="edit-clienteFisico" class="content scaffold-edit" role="main">
                       <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>
                        <g:hasErrors bean="${clienteFisicoInstance}">
                            <ul class="errors" role="alert">
                                <g:eachError bean="${clienteFisicoInstance}" var="error">
                                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                    </g:eachError>
                                    </ul>
                                </g:hasErrors>
                                <g:form url="[resource:clienteFisicoInstance, action:'update']" method="PUT" >
                                    <g:hiddenField name="version" value="${clienteFisicoInstance?.version}" />
                                    <fieldset class="form">
                                    <g:render template="form"/>
                                    </fieldset>
                                    <fieldset class="buttons">
                                        <g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                                    </fieldset>
                                </g:form>
                                </div>
                                </body>
                                </html>
