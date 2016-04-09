package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured ('ROLE_ADMIN')
class EstadoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Estado.list(params), model:[estadoCount: Estado.count()]
    }

    @Secured (['ROLE_ADMIN', 'ROLE_CLIENTE' , 'ROLE_GERENTE'])
    def show(Estado estado) {
        respond estado
    }

    def create() {
        respond new Estado(params)
    }

    @Transactional
    def save(Estado estado) {
        if (estado == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (estado.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond estado.errors, view:'create'
            return
        }

        estado.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'estado.label', default: 'Estado'), estado.id])
                redirect estado
            }
            '*' { respond estado, [status: CREATED] }
        }
    }

    def edit(Estado estado) {
        respond estado
    }

    @Transactional
    def update(Estado estado) {
        if (estado == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (estado.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond estado.errors, view:'edit'
            return
        }

        estado.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'estado.label', default: 'Estado'), estado.id])
                redirect estado
            }
            '*'{ respond estado, [status: OK] }
        }
    }

    @Transactional
    def delete(Estado estado) {

        if (estado == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        estado.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'estado.label', default: 'Estado'), estado.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'estado.label', default: 'Estado'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
