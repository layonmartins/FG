package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured ('ROLE_GERENTE')
class ClienteJuridicoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ClienteJuridico.list(params), model:[clienteJuridicoCount: ClienteJuridico.count()]
    }

    @Secured (['ROLE_ADMIN', 'ROLE_CLIENTE' , 'ROLE_GERENTE'])
    def show(ClienteJuridico clienteJuridico) {
        respond clienteJuridico
    }

    def create() {
        respond new ClienteJuridico(params)
    }

    @Transactional
    def save(ClienteJuridico clienteJuridico) {
        if (clienteJuridico == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (clienteJuridico.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clienteJuridico.errors, view:'create'
            return
        }

        clienteJuridico.enabled = false
        clienteJuridico.save flush:true

        def papel = Papel.findByAuthority("ROLE_CLIENTE")
        UsuarioPapel.create(clienteJuridico, papel)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'clienteJuridico.label', default: 'ClienteJuridico'), clienteJuridico.id])
                redirect clienteJuridico
            }
            '*' { respond clienteJuridico, [status: CREATED] }
        }
    }

    def edit(ClienteJuridico clienteJuridico) {
        respond clienteJuridico
    }

    @Transactional
    def update(ClienteJuridico clienteJuridico) {
        if (clienteJuridico == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (clienteJuridico.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clienteJuridico.errors, view:'edit'
            return
        }

        clienteJuridico.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'clienteJuridico.label', default: 'ClienteJuridico'), clienteJuridico.id])
                redirect clienteJuridico
            }
            '*'{ respond clienteJuridico, [status: OK] }
        }
    }

    @Transactional
    def delete(ClienteJuridico clienteJuridico) {

        if (clienteJuridico == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        clienteJuridico.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'clienteJuridico.label', default: 'ClienteJuridico'), clienteJuridico.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'clienteJuridico.label', default: 'ClienteJuridico'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
