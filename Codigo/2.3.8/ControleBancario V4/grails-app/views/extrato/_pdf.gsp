<%@ page import="br.ufscar.dc.dsw.ContaCorrente" %>
<%@ page import="br.ufscar.dc.dsw.ClienteFisico" %>

<hr/>
<center>
    <h1>EXTRATO</h1>
</center>
<hr/>
<h4>Nome: ${contaCliente.cliente.nome}</h4>
<h4>
    ${contaCliente.cliente instanceof ClienteFisico ? "CPF: " + contaCliente.cliente.CPF : "CNPJ: " + contaCliente.cliente.CNPJ}
</h4>
<hr/>
<h4>Conta: ${contaCliente.conta.numero}
    (${contaCliente.conta instanceof ContaCorrente ? "Conta Corrente" : "Conta Poupança"})</h4>
<h4>Agência: ${contaCliente.conta.agencia.numero}</h4>
<h4>${contaCliente.conta.agencia.banco} - CNPJ: ${contaCliente.conta.agencia.banco.CNPJ}</h4>
<hr/>

<br/>
<br/>

<table style="text-align: left; margin-left: auto; margin-right: auto;" class="pure-table pure-table-bordered" border="2">
    <thead> <tr>
            <th style="width: 200px; background-color: rgb(204, 255, 255);">Data</th>
            <th style="width: 200px; background-color: rgb(204, 255, 255);">Tipo</th>
            <th style="width: 200px; background-color: rgb(204, 255, 255);">Motivo</th>
            <th style="width: 200px; background-color: rgb(204, 255, 255);">Valor</th>
            <th style="width: 200px; background-color: rgb(204, 255, 255);">Saldo</th>
        </tr>
    </thead> <tbody>
        <g:each in="${lines}" status="i" var="linha"> 
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><g:formatDate date="${linha.data}" type="date"/></td>
                <td>${fieldValue(bean: linha, field: "tipo")}</td>
                <td>${fieldValue(bean: linha, field: "motivo")}</td>
                <td><g:formatNumber number="${linha.valor}" type="currency"/></td>
                <td><g:formatNumber number="${linha.saldo}" type="currency"/></td>
            </tr>
        </g:each>
    </tbody>
</table>

<br/>

<br/>
<hr/>

<center>
    <h6>&copy; Departamento de Computação - Universidade Federal de São Carlos</h6>
    <h6><g:formatDate date="${new Date()}" type="datetime" style="LONG"/></h6>
</center>
