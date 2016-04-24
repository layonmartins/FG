package br.ufscar.dc.dsw

import org.springframework.security.access.annotation.Secured

@Secured('ROLE_CLIENTE')
class SelecionaContaController {

    def index() { }
    
    def selected() {
        
        session.contaCliente = ContaCliente.get(params.conta)
        
        redirect(controller:'main')
    }
}
