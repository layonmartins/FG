package br.ufscar.dc.dsw

import grails.rest.*

@Resource(uri='/livros', formats=['json', 'xml'])
class Livro {

    static constraints = {
        titulo blank:false
        ano min: 1900
    }

    String titulo

    int ano
}