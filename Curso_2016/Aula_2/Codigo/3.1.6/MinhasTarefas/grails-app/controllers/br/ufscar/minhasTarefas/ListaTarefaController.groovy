package br.ufscar.minhasTarefas

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ListaTarefaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ListaTarefa.list(params), model:[listaTarefaCount: ListaTarefa.count()]
    }

    def show(ListaTarefa listaTarefa) {
        respond listaTarefa
    }

    def create() {
        respond new ListaTarefa(params)
    }

    @Transactional
    def save(ListaTarefa listaTarefa) {
        if (listaTarefa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (listaTarefa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond listaTarefa.errors, view:'create'
            return
        }

        listaTarefa.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'listaTarefa.label', default: 'ListaTarefa'), listaTarefa.id])
                redirect listaTarefa
            }
            '*' { respond listaTarefa, [status: CREATED] }
        }
    }

    def edit(ListaTarefa listaTarefa) {
        respond listaTarefa
    }

    @Transactional
    def update(ListaTarefa listaTarefa) {
        if (listaTarefa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (listaTarefa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond listaTarefa.errors, view:'edit'
            return
        }

        listaTarefa.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'listaTarefa.label', default: 'ListaTarefa'), listaTarefa.id])
                redirect listaTarefa
            }
            '*'{ respond listaTarefa, [status: OK] }
        }
    }

    @Transactional
    def delete(ListaTarefa listaTarefa) {

        if (listaTarefa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        listaTarefa.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'listaTarefa.label', default: 'ListaTarefa'), listaTarefa.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'listaTarefa.label', default: 'ListaTarefa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
