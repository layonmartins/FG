package br.ufscar.dc.dsw

abstract class Conta {

    static hasMany = [contasCliente: ContaCliente]
    
    static constraints = {
        numero (blank: false)
        agencia (nullable: false)
        saldo (nullable: false, min: 0.0d)
        abertura (nullable: false)
    }

    static fetchMode = [contasCliente: 'eager']

    static mapping = {
        tablePerHierarchy false
    }
        
    Agencia agencia
    
    String numero
	
    double saldo
	
    Date abertura
	
}