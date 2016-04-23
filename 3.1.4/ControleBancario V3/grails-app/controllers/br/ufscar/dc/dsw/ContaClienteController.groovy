package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured('ROLE_GERENTE')
class ContaClienteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def results = ContaCliente.findAll("from ContaCliente as cc where cc.conta.agencia = :agencia",
                [agencia: session.agencia])

        respond results, model: [contaClienteCount: ContaCliente.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_GERENTE'])
    def show(ContaCliente contaCliente) {
        respond contaCliente
    }

    def create() {
        respond new ContaCliente(params)
    }

    @Transactional
    def save(ContaCliente contaCliente) {
        if (contaCliente == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contaCliente.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contaCliente.errors, view: 'create'
            return
        }

        contaCliente.save flush: true

        def cliente = contaCliente.cliente

        if (!cliente.enabled) {
            cliente.enabled = true
            cliente.save flush: true
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contaCliente.label', default: 'ContaCliente'), contaCliente.id])
                redirect contaCliente
            }
            '*' { respond contaCliente, [status: CREATED] }
        }
    }

    def edit(ContaCliente contaCliente) {
        if (contaCliente != null && contaCliente.conta.agencia.id != session.agencia.id) {
            flash.message = message(code: 'springSecurity.denied.message', args: [message(code:
                    'contaCliente.label', default: 'ContaCliente'), contaCliente.id])
            redirect action: "index"
        }

        respond contaCliente
    }

    @Transactional
    def update(ContaCliente contaCliente) {
        if (contaCliente == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contaCliente.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contaCliente.errors, view: 'edit'
            return
        }

        contaCliente.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'contaCliente.label', default: 'ContaCliente'), contaCliente.id])
                redirect contaCliente
            }
            '*' { respond contaCliente, [status: OK] }
        }
    }

    @Transactional
    def delete(ContaCliente contaCliente) {

        if (contaCliente != null && contaCliente.conta.agencia.id != session.agencia.id) {
            flash.message = message(code: 'springSecurity.denied.message', args: [message(code:
                    'contaCliente.label', default: 'ContaCliente'), contaCliente.id])
            redirect action: "index"
        }

        if (contaCliente == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        contaCliente.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'contaCliente.label', default: 'ContaCliente'), contaCliente.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contaCliente.label', default: 'ContaCliente'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
