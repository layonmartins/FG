<%@ page import="br.ufscar.dc.dsw.ContaCliente" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    <r:require module="pure-all" />
</head>
<body>
    <div class="pure-menu pure-menu-open pure-menu-horizontal">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link controller="logout">Logout</g:link></li>
            </ul>
        </div>


    <g:form class="pure-form pure-form-aligned" url="[action:'selected']" >
        <fieldset class="form">

            <g:set var="contas" value="${ContaCliente.findAll("from ContaCliente as contaCliente where contaCliente.cliente = ?", [session.cliente])}" />

            <g:select name="conta" from="${contas}" 
                optionKey="id" optionValue="conta">
            </g:select>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="OK" class="save" value="OK" />
        </fieldset>
    </g:form>
</body>
</html>