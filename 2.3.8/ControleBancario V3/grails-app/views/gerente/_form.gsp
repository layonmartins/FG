<%@ page import="br.ufscar.dc.dsw.Gerente" %>



<div class="fieldcontain ${hasErrors(bean: gerenteInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="gerente.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${gerenteInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gerenteInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="gerente.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" required="" value="${gerenteInstance?.password}"/>
</div>

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

<div class="fieldcontain ${hasErrors(bean: gerenteInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="gerente.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${gerenteInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gerenteInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="gerente.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${gerenteInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gerenteInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="gerente.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${gerenteInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gerenteInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="gerente.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${gerenteInstance?.passwordExpired}" />
</div>

