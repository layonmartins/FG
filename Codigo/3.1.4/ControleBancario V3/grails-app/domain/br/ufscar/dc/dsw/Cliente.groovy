package br.ufscar.dc.dsw

abstract class Cliente extends Usuario{

    static hasMany = [contasCliente: ContaCliente]
    
    public static final String ATIVO = "Ativo"
    public static final String INATIVO = "Inativo"
    
    static constraints = {
        username (blank: false, unique: true)
        password (password: true , blank: false)
        nome (blank: false, size: 1..30)
        endereco (nullable: false)
        dtMoradia (blank: false)
        status (blank: false, inList: [ATIVO, INATIVO])
    }
    
    static mapping = { 
        tablePerHierarchy false
    }

    static fetchMode = [contasCliente: 'eager']
    
    String nome
    
    Date dtMoradia
    
    String status
    
    Endereco endereco
}
