
<%@ page import="br.ufscar.dc.dsw.Cliente" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cliente.label', default: 'Cliente')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cliente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-cliente" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nome" title="${message(code: 'cliente.nome.label', default: 'Nome')}" />
					
						<th><g:message code="cliente.endereco.label" default="Endereco" /></th>
					
						<g:sortableColumn property="dtMoradia" title="${message(code: 'cliente.dtMoradia.label', default: 'Dt Moradia')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'cliente.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${list}" status="i" var="cliente">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" controller="${cliente.class}" id="${cliente.id}">${fieldValue(bean: cliente, field: "nome")}</g:link></td>
					
						<td>${fieldValue(bean: cliente, field: "endereco")}</td>
					
						<td><g:formatDate date="${cliente.dtMoradia}" /></td>
					
						<td>${fieldValue(bean: cliente, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${clienteCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
