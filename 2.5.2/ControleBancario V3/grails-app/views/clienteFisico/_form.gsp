<%@ page import="br.ufscar.dc.dsw.ClienteFisico" %>



<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="clienteFisico.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${clienteFisicoInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="clienteFisico.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" required="" value="${clienteFisicoInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="clienteFisico.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" maxlength="30" required="" value="${clienteFisicoInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'endereco', 'error')} required">
	<label for="endereco">
		<g:message code="clienteFisico.endereco.label" default="Endereco" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="endereco" name="endereco.id" from="${br.ufscar.dc.dsw.Endereco.list()}" optionKey="id" required="" value="${clienteFisicoInstance?.endereco?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'dtMoradia', 'error')} required">
	<label for="dtMoradia">
		<g:message code="clienteFisico.dtMoradia.label" default="Dt Moradia" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dtMoradia" precision="day"  value="${clienteFisicoInstance?.dtMoradia}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="clienteFisico.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${clienteFisicoInstance.constraints.status.inList}" required="" value="${clienteFisicoInstance?.status}" valueMessagePrefix="clienteFisico.status"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'rg', 'error')} required">
	<label for="rg">
		<g:message code="clienteFisico.rg.label" default="Rg" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="rg" maxlength="12" required="" value="${clienteFisicoInstance?.rg}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'CPF', 'error')} required">
	<label for="CPF">
		<g:message code="clienteFisico.CPF.label" default="CPF" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="CPF" maxlength="14" required="" value="${clienteFisicoInstance?.CPF}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="clienteFisico.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${clienteFisicoInstance?.accountExpired}" />

</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="clienteFisico.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${clienteFisicoInstance?.accountLocked}" />

</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'contasCliente', 'error')} ">
	<label for="contasCliente">
		<g:message code="clienteFisico.contasCliente.label" default="Contas Cliente" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${clienteFisicoInstance?.contasCliente?}" var="c">
    <li><g:link controller="contaCliente" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="contaCliente" action="create" params="['clienteFisico.id': clienteFisicoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'contaCliente.label', default: 'ContaCliente')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="clienteFisico.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${clienteFisicoInstance?.enabled}" />

</div>

<div class="fieldcontain ${hasErrors(bean: clienteFisicoInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="clienteFisico.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${clienteFisicoInstance?.passwordExpired}" />

</div>

