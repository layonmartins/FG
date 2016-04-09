package br.ufscar.dc.dsw;

public class Info {
    
    private final int numero;

    private final String nome;

    private final String endereco;

    private final String banco;

    public Info(String banco, int numero, String nome, String endereco) {
        this.banco = banco;
        this.numero = numero;
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getBanco() {
        return banco;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }
    
    
}
