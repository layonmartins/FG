<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'endereco.label', default: 'Endereco')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
        <asset:javascript src="jquery-2.2.0.min.js" />
        <asset:javascript src="jquery.maskedinput.min.js" />
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
        <a href="#edit-endereco" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-endereco" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.endereco}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.endereco}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.endereco}" method="PUT">
                <g:hiddenField name="version" value="${this.endereco?.version}" />
                <fieldset class="form">
                    <div class="fieldcontain ${hasErrors(bean: endereco, field: 'CEP', 'error')} required">
                        <label for="CEP">
                            <g:message code="endereco.CEP.label" default="CEP" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="CEP" maxlength="9" required="" value="${endereco?.CEP}"
                                     onblur="${remoteFunction(action: 'addressByCEP', update: [success: 'addressContainer'],
                                             params: '\'cep=\' + this.value', asynchronous: false)}"/>
                    </div>

                    <div id="addressContainer">
                        <g:render template="address" />
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
