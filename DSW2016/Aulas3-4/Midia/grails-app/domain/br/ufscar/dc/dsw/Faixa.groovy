package br.ufscar.dc.dsw

class Faixa {

    static belongsTo = [cd: Cd]

    static constraints = {
        nome size:1..30
        duracao min:1
    }

    String nome

    int duracao

    String toString() {
        return "[" + nome + ", " + duracao + "]"
    }
}
