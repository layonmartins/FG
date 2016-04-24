package br.ufscar.dc.dsw

class TestController {

    def index() {
        render "Olá Mundo !"
    }

    def renderGSP() {
        render view: 'renderGSP', model: [titulo: "Grails in Action"]
    }

    def redirect(String papel){
        if (papel == "ROLE_ADMIN")
            redirect(controller:"admin")
        else
            redirect(controller: "cliente")
    }
}
