<div class="fieldcontain ${hasErrors(bean: cd, field: 'usuario', 'error')} required">
    <label for="usuario">
        <g:message code="cd.usuario.label" default="Usuario"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="usuario" name="usuario.id" from="${session.usuario}" optionKey="id" required=""
              value="${cd?.usuario?.id}" class="many-to-one"/>
</div>