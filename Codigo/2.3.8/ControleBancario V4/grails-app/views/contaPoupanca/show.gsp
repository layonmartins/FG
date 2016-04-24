
<%@ page import="br.ufscar.dc.dsw.ContaPoupanca" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'contaPoupanca.label', default: 'ContaPoupanca')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <r:require module="pure-all" />
    </head>
    <body>
        <a href="#show-contaPoupanca" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="pure-menu pure-menu-open pure-menu-horizontal">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                       <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>

                       <li>
                       <sec:access controller="contaPoupanca" action='create'>
                        <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
                            </sec:access>
                    </li>
        <li><g:link controller="logout">Logout</g:link></li>
                </ul>
            </div>
            <div id="show-contaPoupanca" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list contaPoupanca">
                
                <g:if test="${contaPoupancaInstance?.agencia}">
                    <li class="fieldcontain">
                        <span id="agencia-label" class="property-label"><g:message code="contaPoupanca.agencia.label" default="Agencia" /></span>
                        
                        <span class="property-value" aria-labelledby="agencia-label"><g:link controller="agencia" action="show" id="${contaPoupancaInstance?.agencia?.id}">${contaPoupancaInstance?.agencia?.encodeAsHTML()}</g:link></span>
                        
                    </li>
                </g:if>
                
                <g:if test="${contaPoupancaInstance?.numero}">
                    <li class="fieldcontain">
                        <span id="numero-label" class="property-label"><g:message code="contaPoupanca.numero.label" default="Numero" /></span>
                        
                        <span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${contaPoupancaInstance}" field="numero"/></span>
                        
                    </li>
                </g:if>
                
                <g:if test="${contaPoupancaInstance?.saldo}">
                    <li class="fieldcontain">
                        <span id="saldo-label" class="property-label"><g:message code="contaPoupanca.saldo.label" default="Saldo" /></span>
                        
                        <span class="property-value" aria-labelledby="saldo-label"><g:fieldValue bean="${contaPoupancaInstance}" field="saldo"/></span>
                        
                    </li>
                </g:if>
                
                <g:if test="${contaPoupancaInstance?.abertura}">
                    <li class="fieldcontain">
                        <span id="abertura-label" class="property-label"><g:message code="contaPoupanca.abertura.label" default="Abertura" /></span>
                        
                        <span class="property-value" aria-labelledby="abertura-label"><g:formatDate date="${contaPoupancaInstance?.abertura}" /></span>
                        
                    </li>
                </g:if>
                
                <g:if test="${contaPoupancaInstance?.juros}">
                    <li class="fieldcontain">
                        <span id="juros-label" class="property-label"><g:message code="contaPoupanca.juros.label" default="Juros" /></span>
                        
                        <span class="property-value" aria-labelledby="juros-label"><g:fieldValue bean="${contaPoupancaInstance}" field="juros"/></span>
                        
                    </li>
                </g:if>
                
                <g:if test="${contaPoupancaInstance?.correcao}">
                    <li class="fieldcontain">
                        <span id="correcao-label" class="property-label"><g:message code="contaPoupanca.correcao.label" default="Correcao" /></span>
                        
                        <span class="property-value" aria-labelledby="correcao-label"><g:fieldValue bean="${contaPoupancaInstance}" field="correcao"/></span>
                        
                    </li>
                </g:if>
                
                <g:if test="${contaPoupancaInstance?.dia}">
                    <li class="fieldcontain">
                        <span id="dia-label" class="property-label"><g:message code="contaPoupanca.dia.label" default="Dia" /></span>
                        
                        <span class="property-value" aria-labelledby="dia-label"><g:fieldValue bean="${contaPoupancaInstance}" field="dia"/></span>
                        
                    </li>
                </g:if>
                
                <g:if test="${contaPoupancaInstance?.contasCliente}">
                    <li class="fieldcontain">
                        <span id="contasCliente-label" class="property-label"><g:message code="contaPoupanca.contasCliente.label" default="Contas Cliente" /></span>
                        
                        <g:each in="${contaPoupancaInstance.contasCliente}" var="c">
                            <span class="property-value" aria-labelledby="contasCliente-label"><g:link controller="contaCliente" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
                        </g:each>
                        
                    </li>
                </g:if>
                
            </ol>
            <g:form url="[resource:contaPoupancaInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <sec:access controller="contaPoupanca" action='edit'>
                        <g:link class="edit" action="edit" resource="${contaPoupancaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        </sec:access>
                        <sec:access controller="contaPoupanca" action='delete'>
                        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </sec:access>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
