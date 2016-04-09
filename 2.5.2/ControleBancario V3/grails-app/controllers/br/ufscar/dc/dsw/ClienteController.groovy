package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured('ROLE_GERENTE')
class ClienteController {
    
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Cliente.list(params), model:[list: Cliente.list(params), clienteInstanceCount: Cliente.count()]
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_GERENTE'])
    def show() {
        Cliente instance = Cliente.get(params.id)
        if (instance.instanceOf(ClienteFisico)) {
            forward controller: 'clienteFisico', action: "show"
        } else {
            forward controller: 'clienteJuridico', action: "show"
        }
    }
}
