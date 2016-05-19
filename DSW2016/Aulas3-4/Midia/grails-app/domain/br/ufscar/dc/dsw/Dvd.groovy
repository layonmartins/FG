package br.ufscar.dc.dsw

class Dvd extends Midia {

    static hasMany = [atores: Ator]

    static constraints = {
        diretor size:1..30
    }

    String diretor
}
