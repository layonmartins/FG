<%@ page import="org.grails.plugins.google.visualization.data.Cell; org.grails.plugins.google.visualization.util.DateUtil" %>
<html>
    <head>
        <title>Movimentação Financeira</title>
        <meta name="layout" content="main" />
    <gvisualization:apiImport/>
    <r:require module="pure-all" />
</head>
<body>
<div class="pure-menu pure-menu-open pure-menu-horizontal">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link controller="extrato"><g:message code="extrato.statement"/></g:link></li>
            <li><g:link controller="logout">Logout</g:link></li>
        </ul>
    </div>
    <div id="list-transacao" class="content scaffold-list" role="main">
        <div id="linechart"></div>
        <gvisualization:lineCoreChart elementId="linechart" width="${800}" height="${300}" title="Movimentação Financeira" columns="${columns}" data="${lines}" />
    </div>
</body>
</html>
