<div class="fieldcontain ${hasErrors(bean: dvd, field: 'usuario', 'error')} required">
    <label for="usuario">
        <g:message code="dvd.usuario.label" default="Usuario"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="usuario" name="usuario.id" from="${session.usuario}" optionKey="id" required=""
              value="${dvd?.usuario?.id}" class="many-to-one"/>
</div>