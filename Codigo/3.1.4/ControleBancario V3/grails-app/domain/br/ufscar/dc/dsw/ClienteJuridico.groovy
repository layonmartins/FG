package br.ufscar.dc.dsw

class ClienteJuridico extends Cliente{
    
    static constraints = {
        nome (blank: false, size: 1..30)
        username (blank: false, unique: true)
        password (password: true , blank: false)
        CNPJ (blank: false, unique:true, cnpj: true, size: 18..18)
        endereco (nullable: false)
        dtMoradia (blank: false)
        status (blank: false, inList: [ATIVO, INATIVO])
    }
    
    String CNPJ
    
    @Override
    String toString() {
        return CNPJ
    }
}
