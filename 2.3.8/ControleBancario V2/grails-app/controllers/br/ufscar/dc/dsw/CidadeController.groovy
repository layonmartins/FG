package br.ufscar.dc.dsw



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class CidadeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Cidade.list(params), model:[cidadeInstanceCount: Cidade.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_GERENTE'])
    def show(Cidade cidadeInstance) {
        respond cidadeInstance
    }

    def create() {
        respond new Cidade(params)
    }

    @Transactional
    def save(Cidade cidadeInstance) {
        if (cidadeInstance == null) {
            notFound()
            return
        }

        if (cidadeInstance.hasErrors()) {
            respond cidadeInstance.errors, view:'create'
            return
        }

        cidadeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cidadeInstance.label', default: 'Cidade'), cidadeInstance.id])
                redirect cidadeInstance
            }
            '*' { respond cidadeInstance, [status: CREATED] }
        }
    }

    def edit(Cidade cidadeInstance) {
        respond cidadeInstance
    }

    @Transactional
    def update(Cidade cidadeInstance) {
        if (cidadeInstance == null) {
            notFound()
            return
        }

        if (cidadeInstance.hasErrors()) {
            respond cidadeInstance.errors, view:'edit'
            return
        }

        cidadeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Cidade.label', default: 'Cidade'), cidadeInstance.id])
                redirect cidadeInstance
            }
            '*'{ respond cidadeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Cidade cidadeInstance) {

        if (cidadeInstance == null) {
            notFound()
            return
        }

        cidadeInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Cidade.label', default: 'Cidade'), cidadeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cidadeInstance.label', default: 'Cidade'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}