package br.ufscar.dc.dsw

class ContaCorrente extends Conta{

    static constraints = {
        numero (blank: false)
        agencia (nullable: false)
        saldo (nullable: false, min: 0.0d)
        limite (nullable: false, min: 0.0d)
        abertura (nullable: false)
    }

    static fetchMode = [agencia: 'eager']

    double limite
    
    @Override
    String toString() {
        return "(Conta Corrente) " + numero
    }
}