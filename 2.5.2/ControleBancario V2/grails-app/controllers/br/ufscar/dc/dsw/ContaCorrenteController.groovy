package br.ufscar.dc.dsw



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured('ROLE_GERENTE')
@Transactional(readOnly = true)
class ContaCorrenteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ContaCorrente.list(params), model:[contaCorrenteInstanceCount: ContaCorrente.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_GERENTE'])
    def show(ContaCorrente contaCorrenteInstance) {
        respond contaCorrenteInstance
    }

    def create() {
        respond new ContaCorrente(params)
    }

    @Transactional
    def save(ContaCorrente contaCorrenteInstance) {
        if (contaCorrenteInstance == null) {
            notFound()
            return
        }

        if (contaCorrenteInstance.hasErrors()) {
            respond contaCorrenteInstance.errors, view:'create'
            return
        }

        contaCorrenteInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contaCorrenteInstance.label', default: 'ContaCorrente'), contaCorrenteInstance.id])
                redirect contaCorrenteInstance
            }
            '*' { respond contaCorrenteInstance, [status: CREATED] }
        }
    }

    def edit(ContaCorrente contaCorrenteInstance) {
        respond contaCorrenteInstance
    }

    @Transactional
    def update(ContaCorrente contaCorrenteInstance) {
        if (contaCorrenteInstance == null) {
            notFound()
            return
        }

        if (contaCorrenteInstance.hasErrors()) {
            respond contaCorrenteInstance.errors, view:'edit'
            return
        }

        contaCorrenteInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ContaCorrente.label', default: 'ContaCorrente'), contaCorrenteInstance.id])
                redirect contaCorrenteInstance
            }
            '*'{ respond contaCorrenteInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ContaCorrente contaCorrenteInstance) {

        if (contaCorrenteInstance == null) {
            notFound()
            return
        }

        contaCorrenteInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ContaCorrente.label', default: 'ContaCorrente'), contaCorrenteInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contaCorrenteInstance.label', default: 'ContaCorrente'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}