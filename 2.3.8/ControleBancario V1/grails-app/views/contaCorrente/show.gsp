
<%@ page import="br.ufscar.dc.dsw.ContaCorrente" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contaCorrente.label', default: 'ContaCorrente')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-contaCorrente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-contaCorrente" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list contaCorrente">
			
				<g:if test="${contaCorrenteInstance?.agencia}">
				<li class="fieldcontain">
					<span id="agencia-label" class="property-label"><g:message code="contaCorrente.agencia.label" default="Agencia" /></span>
					
						<span class="property-value" aria-labelledby="agencia-label"><g:link controller="agencia" action="show" id="${contaCorrenteInstance?.agencia?.id}">${contaCorrenteInstance?.agencia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contaCorrenteInstance?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label"><g:message code="contaCorrente.numero.label" default="Numero" /></span>
					
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${contaCorrenteInstance}" field="numero"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contaCorrenteInstance?.saldo}">
				<li class="fieldcontain">
					<span id="saldo-label" class="property-label"><g:message code="contaCorrente.saldo.label" default="Saldo" /></span>
					
						<span class="property-value" aria-labelledby="saldo-label"><g:fieldValue bean="${contaCorrenteInstance}" field="saldo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contaCorrenteInstance?.abertura}">
				<li class="fieldcontain">
					<span id="abertura-label" class="property-label"><g:message code="contaCorrente.abertura.label" default="Abertura" /></span>
					
						<span class="property-value" aria-labelledby="abertura-label"><g:formatDate date="${contaCorrenteInstance?.abertura}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${contaCorrenteInstance?.limite}">
				<li class="fieldcontain">
					<span id="limite-label" class="property-label"><g:message code="contaCorrente.limite.label" default="Limite" /></span>
					
						<span class="property-value" aria-labelledby="limite-label"><g:fieldValue bean="${contaCorrenteInstance}" field="limite"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contaCorrenteInstance?.contasCliente}">
				<li class="fieldcontain">
					<span id="contasCliente-label" class="property-label"><g:message code="contaCorrente.contasCliente.label" default="Contas Cliente" /></span>
					
						<g:each in="${contaCorrenteInstance.contasCliente}" var="c">
						<span class="property-value" aria-labelledby="contasCliente-label"><g:link controller="contaCliente" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:contaCorrenteInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${contaCorrenteInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
