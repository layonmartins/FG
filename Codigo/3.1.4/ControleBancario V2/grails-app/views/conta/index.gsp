
<%@ page import="br.ufscar.dc.dsw.Conta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'conta.label', default: 'Conta')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-conta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-conta" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>

						<g:sortableColumn property="numero" title="${message(code: 'conta.numero.label', default: 'Numero')}" />

						<th><g:message code="conta.agencia.label" default="Agencia" /></th>

						<g:sortableColumn property="saldo" title="${message(code: 'conta.saldo.label', default: 'Saldo')}" />
					
						<g:sortableColumn property="abertura" title="${message(code: 'conta.abertura.label', default: 'Abertura')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${list}" status="i" var="conta">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" controller="${conta.class}" id="${conta.id}">${fieldValue(bean: conta, field: "numero")}</g:link></td>
					
						<td>${fieldValue(bean: conta, field: "agencia")}</td>
					
						<td>${fieldValue(bean: conta, field: "saldo")}</td>
					
						<td><g:formatDate date="${conta.abertura}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${contaCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
