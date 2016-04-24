package br.ufscar.dc.dsw

class ContaCliente {

    static hasMany = [transacoes: Transacao]
    
    static constraints = {
        cliente (nullable: false)
        conta (nullable: false, unique: 'cliente')
        titular (nullable: false)
    }
    
    boolean titular
    Conta conta
    Cliente cliente
    
    @Override
    String toString() {
        return cliente.toString() + " X " + conta
    }
}
