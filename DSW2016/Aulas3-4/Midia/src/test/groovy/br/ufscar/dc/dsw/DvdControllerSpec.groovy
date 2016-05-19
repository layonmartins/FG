package br.ufscar.dc.dsw

import grails.test.mixin.*
import spock.lang.*

@TestFor(DvdController)
@Mock(Dvd)
class DvdControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.dvdList
            model.dvdCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.dvd!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def dvd = new Dvd()
            dvd.validate()
            controller.save(dvd)

        then:"The create view is rendered again with the correct model"
            model.dvd!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            dvd = new Dvd(params)

            controller.save(dvd)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/dvd/show/1'
            controller.flash.message != null
            Dvd.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def dvd = new Dvd(params)
            controller.show(dvd)

        then:"A model is populated containing the domain instance"
            model.dvd == dvd
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def dvd = new Dvd(params)
            controller.edit(dvd)

        then:"A model is populated containing the domain instance"
            model.dvd == dvd
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/dvd/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def dvd = new Dvd()
            dvd.validate()
            controller.update(dvd)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.dvd == dvd

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            dvd = new Dvd(params).save(flush: true)
            controller.update(dvd)

        then:"A redirect is issued to the show action"
            dvd != null
            response.redirectedUrl == "/dvd/show/$dvd.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/dvd/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def dvd = new Dvd(params).save(flush: true)

        then:"It exists"
            Dvd.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(dvd)

        then:"The instance is deleted"
            Dvd.count() == 0
            response.redirectedUrl == '/dvd/index'
            flash.message != null
    }
}
