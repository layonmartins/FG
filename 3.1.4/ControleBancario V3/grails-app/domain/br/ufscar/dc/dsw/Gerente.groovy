package br.ufscar.dc.dsw

class Gerente extends Usuario{

    static constraints = {
        nome (blank: false, size: 1..30)
        username (blank: false, unique: true)
        password (password: true , blank: false)
        rg (blank: false, size: 1..12)
        CPF (blank: false, unique: true, cpf: true, size: 14..14)
        agencia (nullable: false)
    }

    static fetchMode = [agencia: 'eager']

    String nome
    
    String rg
	
    String CPF
	
    Agencia agencia
    
    @Override
    String toString() {
        return nome + " " + CPF
    }
}
