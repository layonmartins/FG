package br.ufscar.dc.dsw

class Cd extends Midia {

    static hasMany = [faixas: Faixa]

    static constraints = {
        artista size:1..30
    }

    String artista
}
