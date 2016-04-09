
<%@ page import="br.ufscar.dc.dsw.Agencia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'agencia.label', default: 'Agencia')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-agencia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-agencia" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="agencia.banco.label" default="Banco" /></th>
					
						<g:sortableColumn property="numero" title="${message(code: 'agencia.numero.label', default: 'Numero')}" />
					
						<g:sortableColumn property="nome" title="${message(code: 'agencia.nome.label', default: 'Nome')}" />
					
						<th><g:message code="agencia.endereco.label" default="Endereco" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${agenciaInstanceList}" status="i" var="agenciaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${agenciaInstance.id}">${fieldValue(bean: agenciaInstance, field: "banco")}</g:link></td>
					
						<td>${fieldValue(bean: agenciaInstance, field: "numero")}</td>
					
						<td>${fieldValue(bean: agenciaInstance, field: "nome")}</td>
					
						<td>${fieldValue(bean: agenciaInstance, field: "endereco")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${agenciaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
