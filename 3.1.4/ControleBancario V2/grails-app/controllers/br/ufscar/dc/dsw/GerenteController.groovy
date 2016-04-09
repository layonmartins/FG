package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured ('ROLE_ADMIN')
class GerenteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Gerente.list(params), model:[gerenteCount: Gerente.count()]
    }

    @Secured (['ROLE_ADMIN', 'ROLE_CLIENTE' , 'ROLE_GERENTE'])
    def show(Gerente gerente) {
        respond gerente
    }

    def create() {
        respond new Gerente(params)
    }

    @Transactional
    def save(Gerente gerente) {
        if (gerente == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (gerente.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond gerente.errors, view:'create'
            return
        }

        gerente.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gerente.label', default: 'Gerente'), gerente.id])
                redirect gerente
            }
            '*' { respond gerente, [status: CREATED] }
        }
    }

    def edit(Gerente gerente) {
        respond gerente
    }

    @Transactional
    def update(Gerente gerente) {
        if (gerente == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (gerente.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond gerente.errors, view:'edit'
            return
        }

        gerente.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'gerente.label', default: 'Gerente'), gerente.id])
                redirect gerente
            }
            '*'{ respond gerente, [status: OK] }
        }
    }

    @Transactional
    def delete(Gerente gerente) {

        if (gerente == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        gerente.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'gerente.label', default: 'Gerente'), gerente.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gerente.label', default: 'Gerente'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
