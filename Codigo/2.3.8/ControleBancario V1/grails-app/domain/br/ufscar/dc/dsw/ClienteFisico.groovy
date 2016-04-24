package br.ufscar.dc.dsw

class ClienteFisico extends Cliente{
    
    static constraints = {
        nome (blank: false, size: 1..30)
        rg (blank: false, size: 1..12)
        CPF (blank: false, unique:true, cpf: true, size: 14..14)
        endereco (nullable: false)
        dtMoradia (blank: false)
        status (blank: false, inList: [ATIVO, INATIVO])
    }
    
    String rg
    
    String CPF
    
    @Override
    String toString() {
        return CPF
    }
}
