package br.ufscar.dc.dsw

class Estado {

    static constraints = {
        nome (nullable: false, size: 1..20)
        sigla (nullable: false, size: 2..2)
    }
    
    String nome
    
    String sigla
    
    String toString() {
        return sigla
    }
}
