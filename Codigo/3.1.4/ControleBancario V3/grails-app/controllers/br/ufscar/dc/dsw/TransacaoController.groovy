package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured ('ROLE_CLIENTE')
class TransacaoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Transacao.list(params), model:[transacaoCount: Transacao.count()]
    }

    @Secured (['ROLE_ADMIN', 'ROLE_CLIENTE' , 'ROLE_GERENTE'])
    def show(Transacao transacao) {
        respond transacao
    }

    def create() {
        respond new Transacao(params)
    }

    @Transactional
    def save(Transacao transacao) {
        if (transacao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (transacao.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond transacao.errors, view:'create'
            return
        }

        def conta = transacao.contaCliente.conta
        conta.saldo += transacao.getValorReal()
        transacao.save flush:true
        conta.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'transacao.label', default: 'Transacao'), transacao.id])
                redirect transacao
            }
            '*' { respond transacao, [status: CREATED] }
        }
    }

    def edit(Transacao transacao) {
        if (transacao != null && transacao.contaCliente.id != session.contaCliente.id) {
            flash.message = message(code: 'springSecurity.denied.message', args: [message(code: 'transacao.label', default: 'Transacao'), transacao.id])
            redirect action: "index"
        }
        respond transacaoInstance
    }

    double getValorReal(Transacao transacao) {
        return (transacao.tipo == Transacao.CRÉDITO) ? transacao.valor : -transacao.valor;
    }

    double getValorAnterior(Transacao transacao) {
        def ant = transacao.getPersistentValue('valor')
        if (ant == null) {
            ant = 0
        }
        def tipo = transacao.getPersistentValue('tipo')
        if (tipo == Transacao.DÉBITO) {
            ant = -ant
        }
        return ant
    }

    @Transactional
    def update(Transacao transacao) {
        if (transacao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (transacao.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond transacao.errors, view:'edit'
            return
        }

        def conta = transacao.contaCliente.conta

        println "saldo = " + conta.saldo

        conta.saldo += (transacao.getValorReal() - transacao.getValorAnterior())

        println "saldo = " + conta.saldo

        transacao.save flush:true
        conta.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'transacao.label', default: 'Transacao'), transacao.id])
                redirect transacao
            }
            '*'{ respond transacao, [status: OK] }
        }
    }

    @Transactional
    def delete(Transacao transacao) {

        if (transacao.contaCliente.id != session.contaCliente.id) {
            flash.message = message(code: 'springSecurity.denied.message', args: [message(code: 'transacao.label', default: 'Transacao'), transacao.id])
            redirect action: "index", method: "GET"
            return
        }

        if (transacao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def conta = transacao.contaCliente.conta;
        conta.saldo -= transacao.getValorReal()
        transacao.delete flush:true
        conta.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'transacao.label', default: 'Transacao'), transacao.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'transacao.label', default: 'Transacao'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
