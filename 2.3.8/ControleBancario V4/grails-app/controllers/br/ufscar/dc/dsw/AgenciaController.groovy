package br.ufscar.dc.dsw


import grails.converters.JSON
import grails.converters.XML
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class AgenciaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Agencia.list(params), model:[agenciaInstanceCount: Agencia.count()]
    }
    
    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
    def list() {
        
        def banco = Banco.findAllByNumero(params.numero)
        
        def agencias = Agencia.findAllByBanco(banco)
        
        def estado = Estado.findBySigla(params.estado)
        def cidade = Cidade.findByNomeAndEstado(params.cidade, estado)
        
        List<Info> lista = new ArrayList<Info>()
        
        agencias.each {
            
            if (!params.cidade || it.endereco.cidade == cidade) {
                Info info = new Info(it.banco.nome, it.numero, 
                    it.nome, it.endereco.toString())
            
                lista.add(info)
            }
        }
            
        withFormat {
            json { render lista as JSON }
            xml { render lista as XML }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_GERENTE'])
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
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'agenciaInstance.label', default: 'Agencia'), agenciaInstance.id])
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
            form {
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
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Agencia.label', default: 'Agencia'), agenciaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'agenciaInstance.label', default: 'Agencia'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}