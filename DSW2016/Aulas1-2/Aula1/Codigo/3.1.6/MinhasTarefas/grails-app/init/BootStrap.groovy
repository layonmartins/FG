import br.ufscar.minhasTarefas.ListaTarefa
import br.ufscar.minhasTarefas.Tarefa

class BootStrap {

    def init = { servletContext ->
        criarListasComTarefas()

    }

    private criarListasComTarefas() {
        15.times { i ->
            ListaTarefa listaTarefa = new ListaTarefa(nome: "Lista ${i + 1}").save(failOnError: true)
            3.times { j ->
                new Tarefa(nome: "Tarefa ${j + 1}", lista: listaTarefa).save(failOnError: true)
            }
        }
    }

    def destroy = {
    }
}


