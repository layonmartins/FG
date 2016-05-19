package br.ufscar.dc.dsw

class Ator {

    static belongsTo = [dvd: Dvd]

    static constraints = {
        nome size:1..30
        papel size:1..30
    }

    String nome

    String papel

    String toString() {
        return "[" + nome + ", " + papel + "]"
    }
}
