package br.ufscar.minhasTarefas

class Tarefa {

    String nome
    Boolean concluida = false
    static belongsTo = ['lista': ListaTarefa]

    static constraints = {
    }
}