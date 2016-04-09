<%@ page import="br.ufscar.dc.dsw.Banco" %>



<div class="fieldcontain ${hasErrors(bean: bancoInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="banco.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numero" type="number" min="0" value="${bancoInstance.numero}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: bancoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="banco.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" maxlength="20" required="" value="${bancoInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bancoInstance, field: 'CNPJ', 'error')} required">
	<label for="CNPJ">
		<g:message code="banco.CNPJ.label" default="CNPJ" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="CNPJ" maxlength="18" required="" value="${bancoInstance?.CNPJ}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bancoInstance, field: 'agencias', 'error')} ">
	<label for="agencias">
		<g:message code="banco.agencias.label" default="Agencias" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${bancoInstance?.agencias?}" var="a">
    <li><g:link controller="agencia" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="agencia" action="create" params="['banco.id': bancoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'agencia.label', default: 'Agencia')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: bancoInstance, field: 'caixas', 'error')} ">
	<label for="caixas">
		<g:message code="banco.caixas.label" default="Caixas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${bancoInstance?.caixas?}" var="c">
    <li><g:link controller="caixaEletronico" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="caixaEletronico" action="create" params="['banco.id': bancoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'caixaEletronico.label', default: 'CaixaEletronico')])}</g:link>
</li>
</ul>


</div>

