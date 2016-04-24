package br.ufscar.dc.dsw

import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_GERENTE'])
class MainController {

    def springSecurityService
    
    def index() { 
    
        def usuario = springSecurityService.getCurrentUser() 
        def authority = usuario.getAuthorities()[0].getAuthority()
        
        if (authority.equals('ROLE_GERENTE')) {
            if (!session.agencia) {
                session.agencia = usuario.agencia
                session.papel = "Gerente"
            }
        } else if (authority.equals('ROLE_CLIENTE')) {
            
            if (!session.papel) {
                session.papel = "Cliente"
            }
            
            if (!session.cliente) {
                session.cliente = usuario
            }
                        
            if (!session.contaCliente) {
                if (session.cliente.contasCliente.size() == 1) {
                    session.contaCliente = session.cliente.contasCliente[0]
                } else {
                    redirect(controller:'selecionaConta')
                }
            }
        } else {
            if (!session.papel) {
                session.papel = "Administrador"
            }
        }
    }
}
