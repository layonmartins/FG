package br.ufscar.dc.dsw

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(MainController)
class MainControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "testando a ação index"() {
        when:
        controller.index()
        then:
        response.text == "Olá Mundo !"
    }

    void "testando a ação renderGSP"() {
        when:
        controller.renderGSP()
        then: "o modelo deve ter a chave titulo"
        model.titulo == "Grails in Action"
        view == "/main/renderGSP"
    }

    void "testando a ação redirect - papel ROLE_ADMIN"() {
        when:
        controller.redirect("ROLE_ADMIN")
        then:
        response.redirectedUrl == "/admin"
    }

    void "testando a ação redirect - papel ROLE_CLIENTE"() {
        when:
        controller.redirect("ROLE_CLIENTE")
        then:
        response.redirectedUrl == "/cliente"
    }
}
