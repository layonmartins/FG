
<%@ page import="br.ufscar.dc.dsw.Gerente" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gerente.label', default: 'Gerente')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gerente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gerente" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nome" title="${message(code: 'gerente.nome.label', default: 'Nome')}" />
					
						<g:sortableColumn property="rg" title="${message(code: 'gerente.rg.label', default: 'Rg')}" />
					
						<g:sortableColumn property="CPF" title="${message(code: 'gerente.CPF.label', default: 'CPF')}" />
					
						<th><g:message code="gerente.agencia.label" default="Agencia" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gerenteInstanceList}" status="i" var="gerenteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gerenteInstance.id}">${fieldValue(bean: gerenteInstance, field: "nome")}</g:link></td>
					
						<td>${fieldValue(bean: gerenteInstance, field: "rg")}</td>
					
						<td>${fieldValue(bean: gerenteInstance, field: "CPF")}</td>
					
						<td>${fieldValue(bean: gerenteInstance, field: "agencia")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gerenteInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
