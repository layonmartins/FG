package br.ufscar.dc.dsw

class Livro {

    static constraints = {
        titulo blank:false
        ano min: 1900
    }

    String titulo

    int ano
}