
<%@ page import="br.ufscar.dc.dsw.ContaPoupanca" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contaPoupanca.label', default: 'ContaPoupanca')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
                <r:require module="pure-all" />
	</head>
	<body>
		<a href="#list-contaPoupanca" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="pure-menu pure-menu-open pure-menu-horizontal">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				 <li>
                       <sec:access controller="contaPoupanca" action='create'>
                        <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
                            </sec:access>
                    </li>
                                  
                    <li><g:link controller="logout">Logout</g:link></li>
                                      
			</ul>
		</div>
		<div id="list-contaPoupanca" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="pure-table pure-table-bordered">
			<thead>
					<tr>
					
						<th><g:message code="contaPoupanca.agencia.label" default="Agencia" /></th>
					
						<g:sortableColumn property="numero" title="${message(code: 'contaPoupanca.numero.label', default: 'Numero')}" />
					
						<g:sortableColumn property="saldo" title="${message(code: 'contaPoupanca.saldo.label', default: 'Saldo')}" />
					
						<g:sortableColumn property="abertura" title="${message(code: 'contaPoupanca.abertura.label', default: 'Abertura')}" />
					
						<g:sortableColumn property="juros" title="${message(code: 'contaPoupanca.juros.label', default: 'Juros')}" />
					
						<g:sortableColumn property="correcao" title="${message(code: 'contaPoupanca.correcao.label', default: 'Correcao')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${contaPoupancaInstanceList}" status="i" var="contaPoupancaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${contaPoupancaInstance.id}">${fieldValue(bean: contaPoupancaInstance, field: "agencia")}</g:link></td>
					
						<td>${fieldValue(bean: contaPoupancaInstance, field: "numero")}</td>
					
						<td>${fieldValue(bean: contaPoupancaInstance, field: "saldo")}</td>
					
						<td><g:formatDate date="${contaPoupancaInstance.abertura}" /></td>
					
						<td>${fieldValue(bean: contaPoupancaInstance, field: "juros")}</td>
					
						<td>${fieldValue(bean: contaPoupancaInstance, field: "correcao")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${contaPoupancaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
