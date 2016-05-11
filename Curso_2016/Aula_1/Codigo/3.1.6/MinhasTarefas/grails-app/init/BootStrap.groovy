import br.ufscar.minhasTarefas.ListaTarefa

class BootStrap {

    def init = { servletContext ->
        15.times { i ->
            ListaTarefa listaTarefa = new ListaTarefa(nome: "Lista ${i + 1}").save(failOnError: true)
        }

    }
    def destroy = {
    }
}


