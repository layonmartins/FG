package br.ufscar.dc.dsw

abstract class Cliente {

    static hasMany = [contasCliente: ContaCliente]
    
    public static final String ATIVO = "Ativo"
    public static final String INATIVO = "Inativo"
    
    static constraints = {
        nome (blank: false, size: 1..30)
        endereco (nullable: false)
        dtMoradia (blank: false)
        status (blank: false, inList: [ATIVO, INATIVO])
    }
    
    static mapping = { 
        tablePerHierarchy false
    }
    
    String nome
    
    Date dtMoradia
    
    String status
    
    Endereco endereco
}
