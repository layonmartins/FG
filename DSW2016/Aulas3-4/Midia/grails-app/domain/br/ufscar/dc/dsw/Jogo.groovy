package br.ufscar.dc.dsw

class Jogo extends Midia {

    static constraints = {
        genero size:1..30
    }

    String genero
}
