<div class="fieldcontain ${hasErrors(bean: contaPoupanca, field: 'agencia', 'error')} required">
    <label for="agencia">
        <g:message code="contaPoupanca.agencia.label" default="Agencia"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="agencia" name="agencia.id" from="${session.agencia}" optionKey="id" required=""
              value="${contaPoupanca?.agencia?.id}" class="many-to-one"/>
</div>
