package br.ufscar.dc.dsw

import grails.rest.*

@Resource(uri='/livros', formats=['json', 'xml'])
class Livro {

    String titulo

    static constraints = {
        titulo blank:false
    }
}