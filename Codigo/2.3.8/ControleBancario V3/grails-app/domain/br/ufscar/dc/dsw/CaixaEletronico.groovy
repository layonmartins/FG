package br.ufscar.dc.dsw

class CaixaEletronico {

    static hasMany = [transacoes: Transacao]
    
    static constraints = {
        banco (nullable: false)
        endereco (nullable: false)
    }
    
    Endereco endereco
	
    Banco banco
    
    @Override
    String toString() {
        return banco.toString() + " - " + endereco.toString();
    }
}
