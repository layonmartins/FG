<%@ page import="br.ufscar.dc.dsw.CaixaEletronico" %>
<%@ page import="br.ufscar.dc.dsw.Transacao" %>

<div class="fieldcontain ${hasErrors(bean: transacaoInstance, field: 'contaCliente', 'error')} required">
    <label for="contaCliente">
        <g:message code="transacao.contaCliente.label" default="Conta Cliente"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="contaCliente" name="contaCliente.id" from="${session.contaCliente}" optionKey="id"
              value="${transacaoInstance?.contaCliente?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transacaoInstance, field: 'caixaEletronico', 'error')} ">
    <label for="caixaEletronico">
        <g:message code="transacao.caixaEletronico.label" default="Caixa Eletronico"/>
        <span class="required-indicator">*</span>
    </label>
    <g:set var="caixas"
           value="${CaixaEletronico.findAll("from CaixaEletronico as caixa where caixa.banco = ?", [session.agencia.banco])}"/>
    <g:select id="caixaEletronico" name="caixaEletronico.id" from="${caixas}" optionKey="id"
              value="${transacaoInstance?.caixaEletronico?.id}" class="many-to-one"/>
</div>

