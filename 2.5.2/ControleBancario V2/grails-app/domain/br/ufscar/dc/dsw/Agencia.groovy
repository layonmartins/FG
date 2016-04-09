package br.ufscar.dc.dsw

class Agencia {

    static hasMany = [gerentes: Gerente]

    static constraints = {
        banco (nullable: false)
        numero (blank: false, min: 0)
        nome (blank: false, size: 1..20)
        endereco (nullable: false)
    }

    static mapping = {
        banco lazy: false
    }
    
    int numero

    String nome

    Endereco endereco

    Banco banco

    @Override
    String toString() {
        StringBuilder sb = new StringBuilder();
        if (nome != null) {
            sb.append(numero)
            sb.append(" - ")
            sb.append(banco)
        }
        return sb.toString();
    }
}
