<%@ page import="br.ufscar.dc.dsw.Endereco" %>

<div class="fieldcontain ${hasErrors(bean: enderecoInstance, field: 'CEP', 'error')} required">
    <label for="CEP">
        <g:message code="endereco.CEP.label" default="CEP" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="CEP" maxlength="9" required="" value="${enderecoInstance?.CEP}" 
    onblur="${remoteFunction(action: 'addressByCEP', update: [success: 'addressContainer'], 
                             params: '\'CEP=\' + this.value', asynchronous: false)}"/>
</div>

<div id="addressContainer">
    <g:render template="address"/>
</div>
