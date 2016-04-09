<%@ page import="br.ufscar.dc.dsw.Endereco" %>

<div class="fieldcontain ${hasErrors(bean: endereco, field: 'logradouro', 'error')}">
    <label for="logradouro">
        <g:message code="endereco.logradouro.label" default="Logradouro" />
    </label>
    <g:textField name="logradouro" maxlength="30" value="${endereco?.logradouro}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: endereco, field: 'numero', 'error')}">
    <label for="numero">
        <g:message code="endereco.numero.label" default="Numero" />
    </label>
    <g:field name="numero" type="number" min="0" value="${endereco.numero}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: endereco, field: 'complemento', 'error')} ">
    <label for="complemento">
        <g:message code="endereco.complemento.label" default="Complemento" />

    </label>
    <g:textField name="complemento" maxlength="20" value="${endereco?.complemento}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: endereco, field: 'bairro', 'error')} ">
    <label for="bairro">
        <g:message code="endereco.bairro.label" default="Bairro" />
    </label>
    <g:textField name="bairro" maxlength="20" value="${endereco?.bairro}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: endereco, field: 'cidade', 'error')} ">
    <label for="cidade">
        <g:message code="endereco.cidade.label" default="Cidade" />
    </label>
    <g:select id="cidade" name="cidade.id" from="${br.ufscar.dc.dsw.Cidade.list()}" optionKey="id" value="${endereco?.cidade?.id}" class="many-to-one"/>
</div>