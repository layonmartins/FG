<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'contaPoupanca.label', default: 'ContaPoupanca')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
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
        <resource:dateChooser />
        <r:require module="pure-all" />
    </head>
    <body>
        <a href="#create-contaPoupanca" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="pure-menu pure-menu-open pure-menu-horizontal">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                       <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                       <li><g:link controller="logout">Logout</g:link></li>
                           </ul>
                           </div>
                           <div id="create-contaPoupanca" class="content scaffold-create" role="main">
                       <h1><g:message code="default.create.label" args="[entityName]" /></h1>
                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>
                        <g:hasErrors bean="${contaPoupancaInstance}">
                            <ul class="errors" role="alert">
                                <g:eachError bean="${contaPoupancaInstance}" var="error">
                                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                    </g:eachError>
                                    </ul>
                                </g:hasErrors>
                                <g:form class="pure-form pure-form-aligned" url="[resource:contaPoupancaInstance, action:'save']" >
                                    <fieldset class="form">
                                    <g:render template="form"/>
                                    </fieldset>
                                    <fieldset class="buttons">
                                        <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                                    </fieldset>
                                </g:form>
                                </div>
                                </body>
                                </html>
