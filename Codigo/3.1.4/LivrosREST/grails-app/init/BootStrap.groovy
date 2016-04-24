import br.ufscar.dc.dsw.Livro

class BootStrap {

    def init = { servletContext ->
        new Livro(titulo:"The Definitive Guide to Grails").save()
        new Livro(titulo:"Grails in Action").save()
    }
    def destroy = {
    }
}
