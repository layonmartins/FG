package br.ufscar.dc.dsw

class Transacao {
    
    public static final String CRÉDITO = "CRÉDITO"
    public static final String DÉBITO = "DÉBITO"
    
    static constraints = {
        contaCliente (nullable: false)
        caixaEletronico (nullable: false)
        valor (nullable: false, min: 0.01d)
        data (nullable: false)
        quem (nullable: false)
        motivo (nullable: false)
        tipo (nullable: false, inList: [CRÉDITO,DÉBITO])
    }
        
    Date data
    
    double valor
    
    String quem
    
    String motivo
    
    String tipo
    
    ContaCliente contaCliente
    
    CaixaEletronico caixaEletronico
    
    String toString() {
        return "[" + tipo + "] " + motivo + " R\$ " + valor 
    }
}
