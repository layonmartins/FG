<div class="fieldcontain ${hasErrors(bean: jogo, field: 'usuario', 'error')} required">
    <label for="usuario">
        <g:message code="jogo.usuario.label" default="Usuario"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="usuario" name="usuario.id" from="${session.usuario}" optionKey="id" required=""
              value="${jogo?.usuario?.id}" class="many-to-one"/>
</div>