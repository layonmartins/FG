package br.ufscar.dc.dsw

abstract class Midia {

    static belongsTo = [usuario: Usuario]

    static constraints = {
        titulo size: 1..30
        ano min: 1900
    }

    String titulo

    int ano

    String toString() {
        return titulo
    }
}
