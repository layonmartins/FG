<%@ page import="br.ufscar.dc.dsw.Estado" %>



<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="estado.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" maxlength="20" value="${estadoInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'sigla', 'error')} ">
	<label for="sigla">
		<g:message code="estado.sigla.label" default="Sigla" />
		
	</label>
	<g:textField name="sigla" maxlength="2" value="${estadoInstance?.sigla}"/>
</div>

