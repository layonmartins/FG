package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured ('ROLE_GERENTE')
class ContaCorrenteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def results = ContaCorrente.findAllByAgencia(session.agencia, params)

        respond results, model:[contaCorrenteCount: results.size()]
    }

    @Secured (['ROLE_ADMIN', 'ROLE_CLIENTE' , 'ROLE_GERENTE'])
    def show(ContaCorrente contaCorrente) {
        respond contaCorrente
    }

    def create() {
        respond new ContaCorrente(params)
    }

    @Transactional
    def save(ContaCorrente contaCorrente) {
        if (contaCorrente == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contaCorrente.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contaCorrente.errors, view:'create'
            return
        }

        contaCorrente.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contaCorrente.label', default: 'ContaCorrente'), contaCorrente.id])
                redirect contaCorrente
            }
            '*' { respond contaCorrente, [status: CREATED] }
        }
    }

    def edit(ContaCorrente contaCorrente) {
        if (contaCorrente != null && contaCorrente.agencia.id != session.agencia.id) {
            flash.message = message(code: 'springSecurity.denied.message', args: [message(code: 'transacaoInstance.label', default: 'Transacao'), contaCorrente.id])
            redirect action: "index"
        }

        respond contaCorrente
    }

    @Transactional
    def update(ContaCorrente contaCorrente) {
        if (contaCorrente == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contaCorrente.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contaCorrente.errors, view:'edit'
            return
        }

        contaCorrente.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'contaCorrente.label', default: 'ContaCorrente'), contaCorrente.id])
                redirect contaCorrente
            }
            '*'{ respond contaCorrente, [status: OK] }
        }
    }

    @Transactional
    def delete(ContaCorrente contaCorrente) {

        if (contaCorrente.agencia.id != session.agencia.id) {
            flash.message = message(code: 'springSecurity.denied.message', args: [message(code: 'transacaoInstance.label', default: 'Transacao'), contaCorrente.id])
            redirect action: "index"
            return
        }

        if (contaCorrente == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        contaCorrente.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'contaCorrente.label', default: 'ContaCorrente'), contaCorrente.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contaCorrente.label', default: 'ContaCorrente'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
