package br.ufscar.minhasTarefas

import br.ufscar.minhasTarefas.seguranca.Usuario

class ListaTarefa {

    String nome
    Boolean preferida = false
    Boolean ativa = true
    Usuario usuario

    static hasMany = ['tarefas': Tarefa]

    static constraints = {
    }
}
