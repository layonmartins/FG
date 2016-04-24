<%@ page import="br.ufscar.dc.dsw.ContaPoupanca" %>



<div class="fieldcontain ${hasErrors(bean: contaPoupancaInstance, field: 'agencia', 'error')} required">
	<label for="agencia">
		<g:message code="contaPoupanca.agencia.label" default="Agencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="agencia" name="agencia.id" from="${session.agencia}" optionKey="id" required="" value="${contaPoupancaInstance?.agencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contaPoupancaInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="contaPoupanca.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numero" required="" value="${contaPoupancaInstance?.numero}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contaPoupancaInstance, field: 'saldo', 'error')} required">
	<label for="saldo">
		<g:message code="contaPoupanca.saldo.label" default="Saldo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="saldo" value="${fieldValue(bean: contaPoupancaInstance, field: 'saldo')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: contaPoupancaInstance, field: 'abertura', 'error')} required">
	<label for="abertura">
		<g:message code="contaPoupanca.abertura.label" default="Abertura" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="abertura" precision="day"  value="${contaPoupancaInstance?.abertura}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: contaPoupancaInstance, field: 'juros', 'error')} required">
	<label for="juros">
		<g:message code="contaPoupanca.juros.label" default="Juros" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="juros" value="${fieldValue(bean: contaPoupancaInstance, field: 'juros')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: contaPoupancaInstance, field: 'correcao', 'error')} required">
	<label for="correcao">
		<g:message code="contaPoupanca.correcao.label" default="Correcao" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="correcao" value="${fieldValue(bean: contaPoupancaInstance, field: 'correcao')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: contaPoupancaInstance, field: 'dia', 'error')} required">
	<label for="dia">
		<g:message code="contaPoupanca.dia.label" default="Dia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="dia" from="${1..28}" class="range" required="" value="${fieldValue(bean: contaPoupancaInstance, field: 'dia')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contaPoupancaInstance, field: 'contasCliente', 'error')} ">
	<label for="contasCliente">
		<g:message code="contaPoupanca.contasCliente.label" default="Contas Cliente" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${contaPoupancaInstance?.contasCliente?}" var="c">
    <li><g:link controller="contaCliente" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="contaCliente" action="create" params="['contaPoupanca.id': contaPoupancaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'contaCliente.label', default: 'ContaCliente')])}</g:link>
</li>
</ul>

</div>

