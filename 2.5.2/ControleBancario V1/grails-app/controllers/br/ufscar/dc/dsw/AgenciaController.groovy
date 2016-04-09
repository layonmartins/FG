package br.ufscar.dc.dsw



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AgenciaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Agencia.list(params), model:[agenciaInstanceCount: Agencia.count()]
    }

    def show(Agencia agenciaInstance) {
        respond agenciaInstance
    }

    def create() {
        respond new Agencia(params)
    }

    @Transactional
    def save(Agencia agenciaInstance) {
        if (agenciaInstance == null) {
            notFound()
            return
        }

        if (agenciaInstance.hasErrors()) {
            respond agenciaInstance.errors, view:'create'
            return
        }

        agenciaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'agencia.label', default: 'Agencia'), agenciaInstance.id])
                redirect agenciaInstance
            }
            '*' { respond agenciaInstance, [status: CREATED] }
        }
    }

    def edit(Agencia agenciaInstance) {
        respond agenciaInstance
    }

    @Transactional
    def update(Agencia agenciaInstance) {
        if (agenciaInstance == null) {
            notFound()
            return
        }

        if (agenciaInstance.hasErrors()) {
            respond agenciaInstance.errors, view:'edit'
            return
        }

        agenciaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Agencia.label', default: 'Agencia'), agenciaInstance.id])
                redirect agenciaInstance
            }
            '*'{ respond agenciaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Agencia agenciaInstance) {

        if (agenciaInstance == null) {
            notFound()
            return
        }

        agenciaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Agencia.label', default: 'Agencia'), agenciaInstance.id])
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
