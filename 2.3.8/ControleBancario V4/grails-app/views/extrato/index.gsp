<%@ page import="org.grails.plugins.google.visualization.data.Cell; org.grails.plugins.google.visualization.util.DateUtil" %>
<%@ page import="br.ufscar.dc.dsw.Transacao" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="extrato.statement"/></title>
    <r:require module="pure-all" />
</head>
<body>
    <a href="#list-transacao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="pure-menu pure-menu-open pure-menu-horizontal">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link action="chart"><g:message code="extrato.chart" default="Chart" /></g:link></li>
            <li><g:link controller="logout">Logout</g:link></li>

        </ul>
    </div>
<div id="list-transacao" class="content scaffold-list" role="main">
    <h1><g:message code="extrato.statement"/></h1>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table class="pure-table pure-table-bordered">
        <thead>
            <tr>

                <th><g:message code="transacao.data"/></th>

                <th><g:message code="transacao.tipo"/></th>

                <th><g:message code="transacao.motivo"/></th>

                <th><g:message code="extrato.valor"/></th>

                <th><g:message code="extrato.saldo"/></th>

            </tr>
        </thead>
        <tbody>
            <g:each in="${lines}" status="i" var="linha">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td><g:formatDate date="${linha.data}" type="date" style="SHORT"/></td>

                    <td>${fieldValue(bean: linha, field: "tipo")}</td>

                    <td>${fieldValue(bean: linha, field: "motivo")}</td>

                    <td><g:formatNumber number="${linha.valor}" type="currency" /></td>

                    <td><g:formatNumber number="${linha.saldo}" type="currency" /></td>

                </tr>
            </g:each>
        </tbody>
    </table>
    <center>
        <h4>
            <g:message code="extrato.saveaspdf"/>
            <g:link action="index.pdf">
                <img src="${resource(dir: 'images', file: 'pdf.jpg')}" alt="PDF" width="18px"/>
            </g:link>
        </h4>
    </center>
</div>
</body>
</html>
