<%@ page import="br.ufscar.dc.dsw.ContaCliente" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
    </head>
    <body>
        <div id="status" role="complementary">
            <h1>Selecione Conta:</h1>
            <g:form url="[action:'selected']" >
                <g:set var="contas" value="${ContaCliente.findAll("from ContaCliente as contaCliente where contaCliente.cliente = ?", [session.cliente])}" />

                <g:select name="conta" from="${contas}" 
                    optionKey="id" optionValue="conta">
                </g:select>
                </br>
                </br>
                <fieldset class="buttons">
                    <g:submitButton name="OK" class="save" value="OK" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>