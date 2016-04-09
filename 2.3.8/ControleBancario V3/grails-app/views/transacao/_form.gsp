<%@ page import="br.ufscar.dc.dsw.CaixaEletronico" %>
<%@ page import="br.ufscar.dc.dsw.Transacao" %>

<div class="fieldcontain ${hasErrors(bean: transacaoInstance, field: 'contaCliente', 'error')} required">
	<label for="contaCliente">
		<g:message code="transacao.contaCliente.label" default="Conta Cliente" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="contaCliente" name="contaCliente.id" from="${session.contaCliente}" optionKey="id" value="${transacaoInstance?.contaCliente?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transacaoInstance, field: 'caixaEletronico', 'error')} ">
	<label for="caixaEletronico">
		<g:message code="transacao.caixaEletronico.label" default="Caixa Eletronico" />
		<span class="required-indicator">*</span>
	</label>
  <g:set var="caixas" value="${CaixaEletronico.findAll("from CaixaEletronico as caixa where caixa.banco = ?", [session.contaCliente.conta.agencia.banco])}" />
	<g:select id="caixaEletronico" name="caixaEletronico.id" from="${caixas}" optionKey="id" value="${transacaoInstance?.caixaEletronico?.id}" class="many-to-one" />
</div>

<div class="fieldcontain ${hasErrors(bean: transacaoInstance, field: 'valor', 'error')} required">
	<label for="valor">
		<g:message code="transacao.valor.label" default="Valor" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="valor" value="${fieldValue(bean: transacaoInstance, field: 'valor')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: transacaoInstance, field: 'data', 'error')} required">
	<label for="data">
		<g:message code="transacao.data.label" default="Data" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="data" precision="day"  value="${transacaoInstance?.data}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: transacaoInstance, field: 'quem', 'error')} ">
	<label for="quem">
		<g:message code="transacao.quem.label" default="Quem" />
		
	</label>
	<g:textField name="quem" value="${transacaoInstance?.quem}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transacaoInstance, field: 'motivo', 'error')} ">
	<label for="motivo">
		<g:message code="transacao.motivo.label" default="Motivo" />
		
	</label>
	<g:textField name="motivo" value="${transacaoInstance?.motivo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transacaoInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="transacao.tipo.label" default="Tipo" />
		
	</label>
	<g:select name="tipo" from="${transacaoInstance.constraints.tipo.inList}" value="${transacaoInstance?.tipo}" valueMessagePrefix="transacao.tipo" noSelection="['': '']"/>
</div>

