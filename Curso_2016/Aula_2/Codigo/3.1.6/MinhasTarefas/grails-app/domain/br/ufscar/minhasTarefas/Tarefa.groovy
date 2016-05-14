package br.ufscar.minhasTarefas

import br.ufscar.minhasTarefas.seguranca.Usuario

class Tarefa {

    String nome
    Boolean concluida = false
    Usuario usuario
    static belongsTo = ['lista': ListaTarefa]

    static constraints = {
        nome()
    }
}