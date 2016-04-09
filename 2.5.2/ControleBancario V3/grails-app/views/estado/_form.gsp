<%@ page import="br.ufscar.dc.dsw.Estado" %>



<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="estado.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" maxlength="20" required="" value="${estadoInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'sigla', 'error')} required">
	<label for="sigla">
		<g:message code="estado.sigla.label" default="Sigla" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="sigla" maxlength="2" required="" value="${estadoInstance?.sigla}"/>

</div>

