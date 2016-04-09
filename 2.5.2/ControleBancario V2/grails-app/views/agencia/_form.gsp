<%@ page import="br.ufscar.dc.dsw.Agencia" %>



<div class="fieldcontain ${hasErrors(bean: agenciaInstance, field: 'banco', 'error')} required">
	<label for="banco">
		<g:message code="agencia.banco.label" default="Banco" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="banco" name="banco.id" from="${br.ufscar.dc.dsw.Banco.list()}" optionKey="id" required="" value="${agenciaInstance?.banco?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: agenciaInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="agencia.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numero" type="number" min="0" value="${agenciaInstance.numero}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: agenciaInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="agencia.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" maxlength="20" required="" value="${agenciaInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: agenciaInstance, field: 'endereco', 'error')} required">
	<label for="endereco">
		<g:message code="agencia.endereco.label" default="Endereco" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="endereco" name="endereco.id" from="${br.ufscar.dc.dsw.Endereco.list()}" optionKey="id" required="" value="${agenciaInstance?.endereco?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: agenciaInstance, field: 'gerentes', 'error')} ">
	<label for="gerentes">
		<g:message code="agencia.gerentes.label" default="Gerentes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${agenciaInstance?.gerentes?}" var="g">
    <li><g:link controller="gerente" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="gerente" action="create" params="['agencia.id': agenciaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'gerente.label', default: 'Gerente')])}</g:link>
</li>
</ul>


</div>

