<%@ page import="br.ufscar.dc.dsw.ClienteJuridico" %>



<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="clienteJuridico.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${clienteJuridicoInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="clienteJuridico.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" required="" value="${clienteJuridicoInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="clienteJuridico.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" maxlength="30" required="" value="${clienteJuridicoInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'endereco', 'error')} required">
	<label for="endereco">
		<g:message code="clienteJuridico.endereco.label" default="Endereco" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="endereco" name="endereco.id" from="${br.ufscar.dc.dsw.Endereco.list()}" optionKey="id" required="" value="${clienteJuridicoInstance?.endereco?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'dtMoradia', 'error')} required">
	<label for="dtMoradia">
		<g:message code="clienteJuridico.dtMoradia.label" default="Dt Moradia" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dtMoradia" precision="day"  value="${clienteJuridicoInstance?.dtMoradia}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="clienteJuridico.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${clienteJuridicoInstance.constraints.status.inList}" required="" value="${clienteJuridicoInstance?.status}" valueMessagePrefix="clienteJuridico.status"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'CNPJ', 'error')} required">
	<label for="CNPJ">
		<g:message code="clienteJuridico.CNPJ.label" default="CNPJ" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="CNPJ" maxlength="18" required="" value="${clienteJuridicoInstance?.CNPJ}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="clienteJuridico.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${clienteJuridicoInstance?.accountExpired}" />

</div>

<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="clienteJuridico.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${clienteJuridicoInstance?.accountLocked}" />

</div>

<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'contasCliente', 'error')} ">
	<label for="contasCliente">
		<g:message code="clienteJuridico.contasCliente.label" default="Contas Cliente" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${clienteJuridicoInstance?.contasCliente?}" var="c">
    <li><g:link controller="contaCliente" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="contaCliente" action="create" params="['clienteJuridico.id': clienteJuridicoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'contaCliente.label', default: 'ContaCliente')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="clienteJuridico.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${clienteJuridicoInstance?.enabled}" />

</div>

<div class="fieldcontain ${hasErrors(bean: clienteJuridicoInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="clienteJuridico.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${clienteJuridicoInstance?.passwordExpired}" />

</div>

