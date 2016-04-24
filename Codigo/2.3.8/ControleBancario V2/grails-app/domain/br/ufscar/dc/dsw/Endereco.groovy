package br.ufscar.dc.dsw

class Endereco {

    static constraints = {
        CEP (blank: false, cep: true, size: 9..9)
        logradouro (blank: false, size: 1..40)
        numero (min: 0)
        complemento (nullable: true, size: 1..40)
        bairro (blank: false, size: 1..40)
        cidade (nullable: false)
    }
    
    String logradouro
    
    int numero
    
    String complemento
    
    String bairro
    
    String CEP
    
    Cidade cidade
    
    String toString() {
        return logradouro + ", " + numero + 
               (complemento == null ? "" : " " + complemento) + ". " + 
               bairro + " " + CEP + " " + cidade 
    }
}
