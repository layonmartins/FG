package br.ufscar.minhasTarefas

import grails.plugin.springsecurity.annotation.Secured

@Secured("IS_AUTHENTICATED_REMEMBERED")
class MainController {
    def springSecurityService

    def index() {
        def usuario = springSecurityService.currentUser

        def userName = springSecurityService.authentication.principal.getUsername()
        def authority = usuario.getAuthorities()[0].getAuthority()

        if (authority.equals('ROLE_ADMIN')) {
            redirect(controller: 'user', action: 'search')

        } else if (authority.equals('ROLE_GERENCIAR_LISTAS')) {
            redirect(controller: 'listaTarefa')
        }

    }
}
