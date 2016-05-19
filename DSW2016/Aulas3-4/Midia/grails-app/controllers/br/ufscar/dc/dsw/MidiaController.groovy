package br.ufscar.dc.dsw

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured ('ROLE_USER')
@Transactional(readOnly = true)
class MidiaController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def lista = Midia.findAllByUsuario(session.usuario, params)
        render view: 'index', model:[midiaList: lista, midiaCount: Midia.count()]
    }

    def show() {
        Midia midia = Midia.get(params.id)

        if (midia.instanceOf(Cd)) {
            redirect (controller: 'cd', action: 'show', id: params.id)
        } else if (midia.instanceOf(Dvd)) {
            redirect (controller: 'dvd', action: 'show', id: params.id)
        } else {
            redirect (controller: 'jogo', action: 'show', id: params.id)
        }
    }
}
