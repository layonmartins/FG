
<%@ page import="br.ufscar.dc.dsw.CaixaEletronico" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'caixaEletronico.label', default: 'CaixaEletronico')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-caixaEletronico" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				 <li>
                       <sec:access controller="caixaEletronico" action='create'>
                        <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
                            </sec:access>
                    </li>
                                      
			</ul>
		</div>
		<div id="list-caixaEletronico" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="caixaEletronico.banco.label" default="Banco" /></th>
					
						<th><g:message code="caixaEletronico.endereco.label" default="Endereco" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${caixaEletronicoInstanceList}" status="i" var="caixaEletronicoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${caixaEletronicoInstance.id}">${fieldValue(bean: caixaEletronicoInstance, field: "banco")}</g:link></td>
					
						<td>${fieldValue(bean: caixaEletronicoInstance, field: "endereco")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${caixaEletronicoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
