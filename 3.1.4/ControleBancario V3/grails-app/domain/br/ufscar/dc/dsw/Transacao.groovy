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

    static transients = ['anterior', 'valorReal']

    Date data
    
    double valor
    
    String quem
    
    String motivo
    
    String tipo
    
    ContaCliente contaCliente
    
    CaixaEletronico caixaEletronico

    double getValorReal() {
        return (tipo == CRÉDITO) ? valor : -valor;
    }

    double getValorAnterior() {
        def ant = this.getPersistentValue('valor')
        def tipo = this.getPersistentValue('tipo')
        if (tipo == Transacao.DÉBITO) {
            ant = -ant
        }
        return ant
    }

    String toString() {
        return "[" + tipo + "] " + motivo + " R\$ " + valor
    }
}