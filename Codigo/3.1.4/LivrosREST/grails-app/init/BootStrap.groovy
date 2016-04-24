import br.ufscar.dc.dsw.Livro

class BootStrap {

    def init = { servletContext ->
        new Livro(titulo:"The Definitive Guide to Grails", ano:2013).save()
        new Livro(titulo:"Grails in Action", ano:2009).save()
    }
    def destroy = {
    }
}
