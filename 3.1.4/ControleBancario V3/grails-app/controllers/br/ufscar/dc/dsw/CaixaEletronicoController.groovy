package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured ('ROLE_ADMIN')
class CaixaEletronicoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CaixaEletronico.list(params), model:[caixaEletronicoCount: CaixaEletronico.count()]
    }

    @Secured (['ROLE_ADMIN', 'ROLE_CLIENTE' , 'ROLE_GERENTE'])
    def show(CaixaEletronico caixaEletronico) {
        respond caixaEletronico
    }

    def create() {
        respond new CaixaEletronico(params)
    }

    @Transactional
    def save(CaixaEletronico caixaEletronico) {
        if (caixaEletronico == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (caixaEletronico.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond caixaEletronico.errors, view:'create'
            return
        }

        caixaEletronico.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'caixaEletronico.label', default: 'CaixaEletronico'), caixaEletronico.id])
                redirect caixaEletronico
            }
            '*' { respond caixaEletronico, [status: CREATED] }
        }
    }

    def edit(CaixaEletronico caixaEletronico) {
        respond caixaEletronico
    }

    @Transactional
    def update(CaixaEletronico caixaEletronico) {
        if (caixaEletronico == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (caixaEletronico.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond caixaEletronico.errors, view:'edit'
            return
        }

        caixaEletronico.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'caixaEletronico.label', default: 'CaixaEletronico'), caixaEletronico.id])
                redirect caixaEletronico
            }
            '*'{ respond caixaEletronico, [status: OK] }
        }
    }

    @Transactional
    def delete(CaixaEletronico caixaEletronico) {

        if (caixaEletronico == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        caixaEletronico.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'caixaEletronico.label', default: 'CaixaEletronico'), caixaEletronico.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'caixaEletronico.label', default: 'CaixaEletronico'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
