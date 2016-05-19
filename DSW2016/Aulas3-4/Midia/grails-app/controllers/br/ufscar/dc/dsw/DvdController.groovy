package br.ufscar.dc.dsw

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Secured ('ROLE_USER')
@Transactional(readOnly = true)
class DvdController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def criteria = Dvd.createCriteria()
        def dvdList = criteria.list (params) {
            eq('usuario', session.usuario)
        }

        respond dvdList, model:[dvdCount: dvdList.totalCount]
    }

    def show(Dvd dvd) {

        if (dvd != null && dvd.usuario != session.usuario) {
            flash.message = message(code: 'springSecurity.denied.message',
                    args: [message(code: 'dvd.label', default: 'DVD'), dvd.id])
            redirect action: "index"
        }

        respond dvd
    }

    def create() {
        respond new Dvd(params)
    }

    @Transactional
    def save(Dvd dvd) {
        if (dvd == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dvd.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dvd.errors, view:'create'
            return
        }

        dvd.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dvd.label', default: 'Dvd'), dvd.id])
                redirect dvd
            }
            '*' { respond dvd, [status: CREATED] }
        }
    }

    def edit(Dvd dvd) {
        respond dvd
    }

    @Transactional
    def update(Dvd dvd) {
        if (dvd == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dvd.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dvd.errors, view:'edit'
            return
        }

        dvd.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dvd.label', default: 'Dvd'), dvd.id])
                redirect dvd
            }
            '*'{ respond dvd, [status: OK] }
        }
    }

    @Transactional
    def delete(Dvd dvd) {

        if (dvd == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dvd.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dvd.label', default: 'Dvd'), dvd.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dvd.label', default: 'Dvd'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
