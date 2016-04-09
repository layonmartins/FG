package br.ufscar.dc.dsw

abstract class Conta {

    static hasMany = [contasCliente: ContaCliente]
    
    static constraints = {
        agencia (nullable: false)
        numero (blank: false)
        saldo (nullable: false, min: 0.0d)
        abertura (nullable: false)
    }
	
    static mapping = {
        tablePerHierarchy false
        agencia lazy: false
    }
        
    Agencia agencia
    
    String numero
	
    double saldo
	
    Date abertura
	
}