package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured ('ROLE_ADMIN')
class CidadeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Cidade.list(params), model:[cidadeCount: Cidade.count()]
    }

    @Secured (['ROLE_ADMIN', 'ROLE_CLIENTE' , 'ROLE_GERENTE'])
    def show(Cidade cidade) {
        respond cidade
    }

    def create() {
        respond new Cidade(params)
    }

    @Transactional
    def save(Cidade cidade) {
        if (cidade == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (cidade.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cidade.errors, view:'create'
            return
        }

        cidade.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cidade.label', default: 'Cidade'), cidade.id])
                redirect cidade
            }
            '*' { respond cidade, [status: CREATED] }
        }
    }

    def edit(Cidade cidade) {
        respond cidade
    }

    @Transactional
    def update(Cidade cidade) {
        if (cidade == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (cidade.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cidade.errors, view:'edit'
            return
        }

        cidade.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cidade.label', default: 'Cidade'), cidade.id])
                redirect cidade
            }
            '*'{ respond cidade, [status: OK] }
        }
    }

    @Transactional
    def delete(Cidade cidade) {

        if (cidade == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        cidade.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cidade.label', default: 'Cidade'), cidade.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cidade.label', default: 'Cidade'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
