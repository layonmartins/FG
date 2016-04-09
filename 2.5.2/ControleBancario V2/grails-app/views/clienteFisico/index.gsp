
<%@ page import="br.ufscar.dc.dsw.ClienteFisico" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'clienteFisico.label', default: 'ClienteFisico')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-clienteFisico" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				 <li>
                       <sec:access controller="clienteFisico" action='create'>
                        <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
                            </sec:access>
                    </li>
                                      
			</ul>
		</div>
		<div id="list-clienteFisico" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'clienteFisico.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'clienteFisico.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="nome" title="${message(code: 'clienteFisico.nome.label', default: 'Nome')}" />
					
						<th><g:message code="clienteFisico.endereco.label" default="Endereco" /></th>
					
						<g:sortableColumn property="dtMoradia" title="${message(code: 'clienteFisico.dtMoradia.label', default: 'Dt Moradia')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'clienteFisico.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${clienteFisicoInstanceList}" status="i" var="clienteFisicoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${clienteFisicoInstance.id}">${fieldValue(bean: clienteFisicoInstance, field: "username")}</g:link></td>
					
						<td>${fieldValue(bean: clienteFisicoInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: clienteFisicoInstance, field: "nome")}</td>
					
						<td>${fieldValue(bean: clienteFisicoInstance, field: "endereco")}</td>
					
						<td><g:formatDate date="${clienteFisicoInstance.dtMoradia}" /></td>
					
						<td>${fieldValue(bean: clienteFisicoInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${clienteFisicoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
