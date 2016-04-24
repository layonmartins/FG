
<%@ page import="br.ufscar.dc.dsw.ContaCorrente" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contaCorrente.label', default: 'ContaCorrente')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
                <r:require module="pure-all" />
	</head>
	<body>
		<a href="#list-contaCorrente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="pure-menu pure-menu-open pure-menu-horizontal">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				 <li>
                       <sec:access controller="contaCorrente" action='create'>
                        <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
                            </sec:access>
                    </li>
                                  
                    <li><g:link controller="logout">Logout</g:link></li>
                                      
			</ul>
		</div>
		<div id="list-contaCorrente" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="pure-table pure-table-bordered">
			<thead>
					<tr>
					
						<th><g:message code="contaCorrente.agencia.label" default="Agencia" /></th>
					
						<g:sortableColumn property="numero" title="${message(code: 'contaCorrente.numero.label', default: 'Numero')}" />
					
						<g:sortableColumn property="saldo" title="${message(code: 'contaCorrente.saldo.label', default: 'Saldo')}" />
					
						<g:sortableColumn property="abertura" title="${message(code: 'contaCorrente.abertura.label', default: 'Abertura')}" />
					
						<g:sortableColumn property="limite" title="${message(code: 'contaCorrente.limite.label', default: 'Limite')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${contaCorrenteInstanceList}" status="i" var="contaCorrenteInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${contaCorrenteInstance.id}">${fieldValue(bean: contaCorrenteInstance, field: "agencia")}</g:link></td>
					
						<td>${fieldValue(bean: contaCorrenteInstance, field: "numero")}</td>
					
						<td>${fieldValue(bean: contaCorrenteInstance, field: "saldo")}</td>
					
						<td><g:formatDate date="${contaCorrenteInstance.abertura}" /></td>
					
						<td>${fieldValue(bean: contaCorrenteInstance, field: "limite")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${contaCorrenteInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
