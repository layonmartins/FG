<div class="fieldcontain ${hasErrors(bean: contaCorrente, field: 'agencia', 'error')} required">
    <label for="agencia">
        <g:message code="contaCorrente.agencia.label" default="Agencia"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="agencia" name="agencia.id" from="${session.agencia}" optionKey="id" required=""
              value="${contaCorrente?.agencia?.id}" class="many-to-one"/>
</div>
