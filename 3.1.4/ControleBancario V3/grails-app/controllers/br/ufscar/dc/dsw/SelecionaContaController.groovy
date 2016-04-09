package br.ufscar.dc.dsw

import org.springframework.security.access.annotation.Secured

@Secured('ROLE_CLIENTE')
class SelecionaContaController {

    def index() { }
    
    def selected() {
        
        def contaCliente = ContaCliente.get(params.conta)

        session.contaCliente = contaCliente
        session.conta = contaCliente.conta
        session.agencia = session.conta.agencia

        redirect(controller:'main')
    }
}
