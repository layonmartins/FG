package br.ufscar.dc.dsw



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured('ROLE_GERENTE')
@Transactional(readOnly = true)
class ContaPoupancaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ContaPoupanca.list(params), model:[contaPoupancaInstanceCount: ContaPoupanca.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_GERENTE'])
    def show(ContaPoupanca contaPoupancaInstance) {
        respond contaPoupancaInstance
    }

    def create() {
        respond new ContaPoupanca(params)
    }

    @Transactional
    def save(ContaPoupanca contaPoupancaInstance) {
        if (contaPoupancaInstance == null) {
            notFound()
            return
        }

        if (contaPoupancaInstance.hasErrors()) {
            respond contaPoupancaInstance.errors, view:'create'
            return
        }

        contaPoupancaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contaPoupancaInstance.label', default: 'ContaPoupanca'), contaPoupancaInstance.id])
                redirect contaPoupancaInstance
            }
            '*' { respond contaPoupancaInstance, [status: CREATED] }
        }
    }

    def edit(ContaPoupanca contaPoupancaInstance) {
        respond contaPoupancaInstance
    }

    @Transactional
    def update(ContaPoupanca contaPoupancaInstance) {
        if (contaPoupancaInstance == null) {
            notFound()
            return
        }

        if (contaPoupancaInstance.hasErrors()) {
            respond contaPoupancaInstance.errors, view:'edit'
            return
        }

        contaPoupancaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ContaPoupanca.label', default: 'ContaPoupanca'), contaPoupancaInstance.id])
                redirect contaPoupancaInstance
            }
            '*'{ respond contaPoupancaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ContaPoupanca contaPoupancaInstance) {

        if (contaPoupancaInstance == null) {
            notFound()
            return
        }

        contaPoupancaInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ContaPoupanca.label', default: 'ContaPoupanca'), contaPoupancaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contaPoupancaInstance.label', default: 'ContaPoupanca'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}