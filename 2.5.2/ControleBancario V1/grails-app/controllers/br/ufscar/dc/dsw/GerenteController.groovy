package br.ufscar.dc.dsw



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GerenteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Gerente.list(params), model:[gerenteInstanceCount: Gerente.count()]
    }

    def show(Gerente gerenteInstance) {
        respond gerenteInstance
    }

    def create() {
        respond new Gerente(params)
    }

    @Transactional
    def save(Gerente gerenteInstance) {
        if (gerenteInstance == null) {
            notFound()
            return
        }

        if (gerenteInstance.hasErrors()) {
            respond gerenteInstance.errors, view:'create'
            return
        }

        gerenteInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gerente.label', default: 'Gerente'), gerenteInstance.id])
                redirect gerenteInstance
            }
            '*' { respond gerenteInstance, [status: CREATED] }
        }
    }

    def edit(Gerente gerenteInstance) {
        respond gerenteInstance
    }

    @Transactional
    def update(Gerente gerenteInstance) {
        if (gerenteInstance == null) {
            notFound()
            return
        }

        if (gerenteInstance.hasErrors()) {
            respond gerenteInstance.errors, view:'edit'
            return
        }

        gerenteInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Gerente.label', default: 'Gerente'), gerenteInstance.id])
                redirect gerenteInstance
            }
            '*'{ respond gerenteInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Gerente gerenteInstance) {

        if (gerenteInstance == null) {
            notFound()
            return
        }

        gerenteInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Gerente.label', default: 'Gerente'), gerenteInstance.id])
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
