
<%@ page import="br.ufscar.dc.dsw.Banco" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'banco.label', default: 'Banco')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-banco" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-banco" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list banco">
			
				<g:if test="${bancoInstance?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label"><g:message code="banco.numero.label" default="Numero" /></span>
					
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${bancoInstance}" field="numero"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bancoInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="banco.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${bancoInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bancoInstance?.CNPJ}">
				<li class="fieldcontain">
					<span id="CNPJ-label" class="property-label"><g:message code="banco.CNPJ.label" default="CNPJ" /></span>
					
						<span class="property-value" aria-labelledby="CNPJ-label"><g:fieldValue bean="${bancoInstance}" field="CNPJ"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bancoInstance?.agencias}">
				<li class="fieldcontain">
					<span id="agencias-label" class="property-label"><g:message code="banco.agencias.label" default="Agencias" /></span>
					
						<g:each in="${bancoInstance.agencias}" var="a">
						<span class="property-value" aria-labelledby="agencias-label"><g:link controller="agencia" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${bancoInstance?.caixas}">
				<li class="fieldcontain">
					<span id="caixas-label" class="property-label"><g:message code="banco.caixas.label" default="Caixas" /></span>
					
						<g:each in="${bancoInstance.caixas}" var="c">
						<span class="property-value" aria-labelledby="caixas-label"><g:link controller="caixaEletronico" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:bancoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${bancoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
