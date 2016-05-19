package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured ('ROLE_USER')
@Transactional(readOnly = true)
class FaixaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Faixa.list(params), model:[faixaCount: Faixa.count()]
    }

    def show(Faixa faixa) {
        respond faixa
    }

    def create() {
        respond new Faixa(params)
    }

    @Transactional
    def save(Faixa faixa) {
        if (faixa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (faixa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond faixa.errors, view:'create'
            return
        }

        faixa.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'faixa.label', default: 'Faixa'), faixa.id])
                redirect faixa
            }
            '*' { respond faixa, [status: CREATED] }
        }
    }

    def edit(Faixa faixa) {
        respond faixa
    }

    @Transactional
    def update(Faixa faixa) {
        if (faixa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (faixa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond faixa.errors, view:'edit'
            return
        }

        faixa.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'faixa.label', default: 'Faixa'), faixa.id])
                redirect faixa
            }
            '*'{ respond faixa, [status: OK] }
        }
    }

    @Transactional
    def delete(Faixa faixa) {

        if (faixa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        faixa.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'faixa.label', default: 'Faixa'), faixa.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'faixa.label', default: 'Faixa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
