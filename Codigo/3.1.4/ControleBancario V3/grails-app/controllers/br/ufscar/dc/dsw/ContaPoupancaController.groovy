package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured ('ROLE_GERENTE')
class ContaPoupancaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def results = ContaPoupanca.findAllByAgencia(session.agencia, params)

        respond results, model:[contaPoupancaCount: results.size()]
    }

    @Secured (['ROLE_ADMIN', 'ROLE_CLIENTE' , 'ROLE_GERENTE'])
    def show(ContaPoupanca contaPoupanca) {
        respond contaPoupanca
    }

    def create() {
        respond new ContaPoupanca(params)
    }

    @Transactional
    def save(ContaPoupanca contaPoupanca) {
        if (contaPoupanca == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contaPoupanca.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contaPoupanca.errors, view:'create'
            return
        }

        contaPoupanca.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contaPoupanca.label', default: 'ContaPoupanca'), contaPoupanca.id])
                redirect contaPoupanca
            }
            '*' { respond contaPoupanca, [status: CREATED] }
        }
    }

    def edit(ContaPoupanca contaPoupanca) {

        if (contaPoupanca != null && contaPoupanca.agencia.id != session.agencia.id) {
            flash.message = message(code: 'springSecurity.denied.message', args: [message(code: 'transacaoInstance.label', default: 'Transacao'), contaPoupanca.id])
            redirect action: "index"
        }

        respond contaPoupanca
    }

    @Transactional
    def update(ContaPoupanca contaPoupanca) {
        if (contaPoupanca == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contaPoupanca.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contaPoupanca.errors, view:'edit'
            return
        }

        contaPoupanca.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'contaPoupanca.label', default: 'ContaPoupanca'), contaPoupanca.id])
                redirect contaPoupanca
            }
            '*'{ respond contaPoupanca, [status: OK] }
        }
    }

    @Transactional
    def delete(ContaPoupanca contaPoupanca) {

        if (contaPoupanca != null && contaPoupanca.agencia.id != session.agencia.id) {
            flash.message = message(code: 'springSecurity.denied.message', args: [message(code: 'transacaoInstance.label', default: 'Transacao'), contaPoupanca.id])
            redirect action: "index"
            return
        }

        if (contaPoupanca == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        contaPoupanca.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'contaPoupanca.label', default: 'ContaPoupanca'), contaPoupanca.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contaPoupanca.label', default: 'ContaPoupanca'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
