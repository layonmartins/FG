package br.ufscar.minhasTarefas

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TarefaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tarefa.list(params), model:[tarefaCount: Tarefa.count()]
    }

    def show(Tarefa tarefa) {
        respond tarefa
    }

    def create() {
        respond new Tarefa(params)
    }

    @Transactional
    def save(Tarefa tarefa) {
        if (tarefa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tarefa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tarefa.errors, view:'create'
            return
        }

        tarefa.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), tarefa.id])
                redirect tarefa
            }
            '*' { respond tarefa, [status: CREATED] }
        }
    }

    def edit(Tarefa tarefa) {
        respond tarefa
    }

    @Transactional
    def update(Tarefa tarefa) {
        if (tarefa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tarefa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tarefa.errors, view:'edit'
            return
        }

        tarefa.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), tarefa.id])
                redirect tarefa
            }
            '*'{ respond tarefa, [status: OK] }
        }
    }

    @Transactional
    def delete(Tarefa tarefa) {

        if (tarefa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tarefa.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), tarefa.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
