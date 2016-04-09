<%@ page import="br.ufscar.dc.dsw.Conta" %>
<%@ page import="br.ufscar.dc.dsw.ContaCliente" %>

<div class="fieldcontain ${hasErrors(bean: contaClienteInstance, field: 'cliente', 'error')} required">
    <label for="cliente">
        <g:message code="contaCliente.cliente.label" default="Cliente"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="cliente" name="cliente.id" from="${br.ufscar.dc.dsw.Cliente.list()}" optionKey="id"
              required="" value="${contaClienteInstance?.cliente?.id}"
              disabled="${contaClienteInstance?.cliente?.id != null}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contaClienteInstance, field: 'conta', 'error')} required">
    <label for="conta">
        <g:message code="contaCliente.conta.label" default="Conta"/>
        <span class="required-indicator">*</span>
    </label>
    <g:set var="contas"
           value="${Conta.findAll("from Conta as conta where conta.agencia = ?", [session.agencia])}"/>
    <g:select id="conta" name="conta.id" from="${contas}" optionKey="id" required=""
              value="${contaClienteInstance?.conta?.id}"
              disabled="${contaClienteInstance?.conta?.id != null}" class="many-to-one"/>
</div>