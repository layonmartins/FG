package br.ufscar.minhasTarefas

class ListaTarefa {

    String nome
    Boolean preferida = false
    Boolean ativa = true

    static hasMany = ['tarefas': Tarefa]

    static constraints = {
    }
}
