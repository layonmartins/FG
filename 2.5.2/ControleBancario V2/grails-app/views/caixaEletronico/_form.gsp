<%@ page import="br.ufscar.dc.dsw.CaixaEletronico" %>



<div class="fieldcontain ${hasErrors(bean: caixaEletronicoInstance, field: 'banco', 'error')} required">
	<label for="banco">
		<g:message code="caixaEletronico.banco.label" default="Banco" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="banco" name="banco.id" from="${br.ufscar.dc.dsw.Banco.list()}" optionKey="id" required="" value="${caixaEletronicoInstance?.banco?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: caixaEletronicoInstance, field: 'endereco', 'error')} required">
	<label for="endereco">
		<g:message code="caixaEletronico.endereco.label" default="Endereco" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="endereco" name="endereco.id" from="${br.ufscar.dc.dsw.Endereco.list()}" optionKey="id" required="" value="${caixaEletronicoInstance?.endereco?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: caixaEletronicoInstance, field: 'transacoes', 'error')} ">
	<label for="transacoes">
		<g:message code="caixaEletronico.transacoes.label" default="Transacoes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${caixaEletronicoInstance?.transacoes?}" var="t">
    <li><g:link controller="transacao" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="transacao" action="create" params="['caixaEletronico.id': caixaEletronicoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'transacao.label', default: 'Transacao')])}</g:link>
</li>
</ul>


</div>

