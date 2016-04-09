package br.ufscar.dc.dsw

class Cidade {

    static constraints = {
        nome (blank: false, size: 1..40)
        estado (nullable: false)
    }

    String nome
    Estado estado
    
    @Override
    String toString() {
        StringBuilder sb = new StringBuilder();
        if (nome != null) {
            sb.append(nome)
            sb.append(" - ")
            sb.append(estado.sigla)
        }
        return sb.toString();
    }
}
