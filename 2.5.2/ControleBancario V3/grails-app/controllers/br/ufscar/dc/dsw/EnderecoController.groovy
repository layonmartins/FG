package br.ufscar.dc.dsw



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured('ROLE_GERENTE')
@Transactional(readOnly = true)
class EnderecoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Endereco.list(params), model:[enderecoInstanceCount: Endereco.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_GERENTE'])
    def show(Endereco enderecoInstance) {
        respond enderecoInstance
    }

    def create() {
        respond new Endereco(params)
    }

    @Transactional
    def save(Endereco enderecoInstance) {
        if (enderecoInstance == null) {
            notFound()
            return
        }

        if (enderecoInstance.hasErrors()) {
            respond enderecoInstance.errors, view:'create'
            return
        }

        enderecoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'enderecoInstance.label', default: 'Endereco'), enderecoInstance.id])
                redirect enderecoInstance
            }
            '*' { respond enderecoInstance, [status: CREATED] }
        }
    }

    def edit(Endereco enderecoInstance) {
        respond enderecoInstance
    }

    @Transactional
    def update(Endereco enderecoInstance) {
        if (enderecoInstance == null) {
            notFound()
            return
        }

        if (enderecoInstance.hasErrors()) {
            respond enderecoInstance.errors, view:'edit'
            return
        }

        enderecoInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Endereco.label', default: 'Endereco'), enderecoInstance.id])
                redirect enderecoInstance
            }
            '*'{ respond enderecoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Endereco enderecoInstance) {

        if (enderecoInstance == null) {
            notFound()
            return
        }

        enderecoInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Endereco.label', default: 'Endereco'), enderecoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'enderecoInstance.label', default: 'Endereco'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    def addressByCEP() {
        def html
        
        try {
            withHttp(uri: "http://cep.republicavirtual.com.br/") {
                html = get(path : 'web_cep.php', 
                    query : [cep:params.CEP, formato:'json'])
            
                params.estado = Estado.findBySigla(html.uf)
                params.cidade = Cidade.findByNomeAndEstado(html.cidade, params.estado)
                params.bairro = html.bairro
                params.logradouro = html.tipo_logradouro + " " + html.logradouro
            }
        
            render template: 'address', model: [enderecoInstance: new Endereco(params)]
        } catch (Exception e) {
            println e
        }
    }
}