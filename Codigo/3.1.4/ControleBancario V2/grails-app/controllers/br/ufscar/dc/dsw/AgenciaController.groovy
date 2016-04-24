package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured ('ROLE_ADMIN')
class AgenciaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Agencia.list(params), model:[agenciaCount: Agencia.count()]
    }

    @Secured (['ROLE_ADMIN', 'ROLE_CLIENTE' , 'ROLE_GERENTE'])
    def show(Agencia agencia) {
        respond agencia
    }

    def create() {
        respond new Agencia(params)
    }

    @Transactional
    def save(Agencia agencia) {
        if (agencia == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (agencia.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond agencia.errors, view:'create'
            return
        }

        agencia.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'agencia.label', default: 'Agencia'), agencia.id])
                redirect agencia
            }
            '*' { respond agencia, [status: CREATED] }
        }
    }

    def edit(Agencia agencia) {
        respond agencia
    }

    @Transactional
    def update(Agencia agencia) {
        if (agencia == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (agencia.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond agencia.errors, view:'edit'
            return
        }

        agencia.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'agencia.label', default: 'Agencia'), agencia.id])
                redirect agencia
            }
            '*'{ respond agencia, [status: OK] }
        }
    }

    @Transactional
    def delete(Agencia agencia) {

        if (agencia == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        agencia.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'agencia.label', default: 'Agencia'), agencia.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'agencia.label', default: 'Agencia'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
