<%@ page import="br.ufscar.dc.dsw.ContaCorrente" %>



<div class="fieldcontain ${hasErrors(bean: contaCorrenteInstance, field: 'agencia', 'error')} required">
	<label for="agencia">
		<g:message code="contaCorrente.agencia.label" default="Agencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="agencia" name="agencia.id" from="${br.ufscar.dc.dsw.Agencia.list()}" optionKey="id" required="" value="${contaCorrenteInstance?.agencia?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: contaCorrenteInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="contaCorrente.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numero" required="" value="${contaCorrenteInstance?.numero}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: contaCorrenteInstance, field: 'saldo', 'error')} required">
	<label for="saldo">
		<g:message code="contaCorrente.saldo.label" default="Saldo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="saldo" value="${fieldValue(bean: contaCorrenteInstance, field: 'saldo')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: contaCorrenteInstance, field: 'abertura', 'error')} required">
	<label for="abertura">
		<g:message code="contaCorrente.abertura.label" default="Abertura" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="abertura" precision="day"  value="${contaCorrenteInstance?.abertura}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: contaCorrenteInstance, field: 'limite', 'error')} required">
	<label for="limite">
		<g:message code="contaCorrente.limite.label" default="Limite" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="limite" value="${fieldValue(bean: contaCorrenteInstance, field: 'limite')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: contaCorrenteInstance, field: 'contasCliente', 'error')} ">
	<label for="contasCliente">
		<g:message code="contaCorrente.contasCliente.label" default="Contas Cliente" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${contaCorrenteInstance?.contasCliente?}" var="c">
    <li><g:link controller="contaCliente" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="contaCliente" action="create" params="['contaCorrente.id': contaCorrenteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'contaCliente.label', default: 'ContaCliente')])}</g:link>
</li>
</ul>


</div>

