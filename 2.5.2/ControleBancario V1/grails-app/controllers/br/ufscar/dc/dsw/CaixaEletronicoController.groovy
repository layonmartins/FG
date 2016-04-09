package br.ufscar.dc.dsw



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CaixaEletronicoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CaixaEletronico.list(params), model:[caixaEletronicoInstanceCount: CaixaEletronico.count()]
    }

    def show(CaixaEletronico caixaEletronicoInstance) {
        respond caixaEletronicoInstance
    }

    def create() {
        respond new CaixaEletronico(params)
    }

    @Transactional
    def save(CaixaEletronico caixaEletronicoInstance) {
        if (caixaEletronicoInstance == null) {
            notFound()
            return
        }

        if (caixaEletronicoInstance.hasErrors()) {
            respond caixaEletronicoInstance.errors, view:'create'
            return
        }

        caixaEletronicoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'caixaEletronico.label', default: 'CaixaEletronico'), caixaEletronicoInstance.id])
                redirect caixaEletronicoInstance
            }
            '*' { respond caixaEletronicoInstance, [status: CREATED] }
        }
    }

    def edit(CaixaEletronico caixaEletronicoInstance) {
        respond caixaEletronicoInstance
    }

    @Transactional
    def update(CaixaEletronico caixaEletronicoInstance) {
        if (caixaEletronicoInstance == null) {
            notFound()
            return
        }

        if (caixaEletronicoInstance.hasErrors()) {
            respond caixaEletronicoInstance.errors, view:'edit'
            return
        }

        caixaEletronicoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'CaixaEletronico.label', default: 'CaixaEletronico'), caixaEletronicoInstance.id])
                redirect caixaEletronicoInstance
            }
            '*'{ respond caixaEletronicoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CaixaEletronico caixaEletronicoInstance) {

        if (caixaEletronicoInstance == null) {
            notFound()
            return
        }

        caixaEletronicoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'CaixaEletronico.label', default: 'CaixaEletronico'), caixaEletronicoInstance.id])
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
