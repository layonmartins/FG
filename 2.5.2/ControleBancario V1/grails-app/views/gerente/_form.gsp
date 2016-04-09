<%@ page import="br.ufscar.dc.dsw.Gerente" %>



<div class="fieldcontain ${hasErrors(bean: gerenteInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="gerente.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" maxlength="30" required="" value="${gerenteInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gerenteInstance, field: 'rg', 'error')} required">
	<label for="rg">
		<g:message code="gerente.rg.label" default="Rg" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="rg" maxlength="12" required="" value="${gerenteInstance?.rg}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gerenteInstance, field: 'CPF', 'error')} required">
	<label for="CPF">
		<g:message code="gerente.CPF.label" default="CPF" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="CPF" maxlength="14" required="" value="${gerenteInstance?.CPF}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gerenteInstance, field: 'agencia', 'error')} required">
	<label for="agencia">
		<g:message code="gerente.agencia.label" default="Agencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="agencia" name="agencia.id" from="${br.ufscar.dc.dsw.Agencia.list()}" optionKey="id" required="" value="${gerenteInstance?.agencia?.id}" class="many-to-one"/>

</div>

