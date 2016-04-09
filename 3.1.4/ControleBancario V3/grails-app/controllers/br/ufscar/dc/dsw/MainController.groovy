package br.ufscar.dc.dsw

import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_GERENTE'])
class MainController {

    def springSecurityService

    def index() {

        def usuario = springSecurityService.getCurrentUser()
        def userName = springSecurityService.authentication.principal.getUsername()
        def authority = usuario.getAuthorities()[0].getAuthority()
        Usuario.findById(1)

        if (authority.equals('ROLE_GERENTE')) {
            def gerente = Gerente.findByUsername(userName)
            if (!session.agencia) {
                session.agencia = gerente.agencia
            }
        } else if (authority.equals('ROLE_CLIENTE')) {

            session.cliente = Cliente.findByUsername(userName)

            if (!session.conta) {
                if (session.cliente.contasCliente.size() == 1) {
                    def contaCliente = session.cliente.contasCliente[0]
                    session.contaCliente = contaCliente
                    session.conta = contaCliente.conta
                    session.agencia = session.conta.agencia
                    println session.agencia
                } else {
                    redirect(controller:'selecionaConta')
                }
            }
        }
        if (session.agencia) {
            println session.agencia
        }
    }
}