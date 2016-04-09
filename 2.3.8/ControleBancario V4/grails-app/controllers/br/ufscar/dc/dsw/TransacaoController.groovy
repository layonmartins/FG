package br.ufscar.dc.dsw

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured('ROLE_CLIENTE')
@Transactional(readOnly = true)
class TransacaoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        
        def results = Transacao.findAllByContaCliente(session.contaCliente, params)
        
        respond results, model:[transacaoInstanceCount: Transacao.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_GERENTE'])
    def show(Transacao transacaoInstance) {
        respond transacaoInstance
    }

    def create() {
        respond new Transacao(params)
    }

    @Transactional
    def save(Transacao transacaoInstance) {
        if (transacaoInstance == null) {
            notFound()
            return
        }

        if (transacaoInstance.hasErrors()) {
            respond transacaoInstance.errors, view:'create'
            return
        }
        
        def conta = transacaoInstance.contaCliente.conta        
        conta.saldo += transacaoInstance.getValorReal()
        
        transacaoInstance.save flush:true
        conta.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'transacaoInstance.label', default: 'Transacao'), transacaoInstance.id])
                redirect transacaoInstance
            }
            '*' { respond transacaoInstance, [status: CREATED] }
        }
    }

    def edit(Transacao transacaoInstance) {
                
        if (transacaoInstance != null && transacaoInstance.contaCliente.id != session.contaCliente.id) {
            flash.message = message(code: 'springSecurity.denied.message', args: [message(code: 'Transacao.label', default: 'Transacao'), transacaoInstance.id])
            redirect action: "index"
        }
        
        respond transacaoInstance
    }

    @Transactional
    def update(Transacao transacaoInstance) {
        if (transacaoInstance == null) {
            notFound()
            return
        }

        if (transacaoInstance.hasErrors()) {
            respond transacaoInstance.errors, view:'edit'
            return
        }
        
        def conta = transacaoInstance.contaCliente.conta        
       
        def anterior = transacaoInstance.getValorAnterior();
        conta.saldo += (transacaoInstance.getValorReal() - anterior)
        
        transacaoInstance.save flush:true
        conta.save flush:true
        
        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Transacao.label', default: 'Transacao'), transacaoInstance.id])
                redirect transacaoInstance
            }
            '*'{ respond transacaoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Transacao transacaoInstance) {
        
        if (transacaoInstance.contaCliente.id != session.contaCliente.id) {
            flash.message = message(code: 'springSecurity.denied.message', args: [message(code: 'Transacao.label', default: 'Transacao'), transacaoInstance.id])
            redirect action: "index", method: "GET"
            return
        }

        if (transacaoInstance == null) {
            notFound()
            return
        }
        
        def conta = transacaoInstance.contaCliente.conta
        conta.saldo -= transacaoInstance.getValorReal()
        
        transacaoInstance.delete flush:true
        conta.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Transacao.label', default: 'Transacao'), transacaoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'transacaoInstance.label', default: 'Transacao'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}