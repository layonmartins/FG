import br.ufscar.minhasTarefas.ListaTarefa
import br.ufscar.minhasTarefas.Tarefa
import br.ufscar.minhasTarefas.seguranca.Permissao
import br.ufscar.minhasTarefas.seguranca.Usuario
import br.ufscar.minhasTarefas.seguranca.UsuarioPermissao

class BootStrap {

    def init = { servletContext ->
        criarUsuariosAutorizados()
        criarListasComTarefas()
    }

    private void criarUsuariosAutorizados() {
        Permissao permissaoAdministrador = new Permissao(authority: 'ROLE_ADMIN').save(failOnError: true)
        Usuario usuarioAdministrador = new Usuario(username: 'admin', password: 'root').save(failOnError: true)
        UsuarioPermissao autorizacaoAdministrador = new UsuarioPermissao(usuario: usuarioAdministrador,
                permissao: permissaoAdministrador).save(failOnError: true)
        Permissao permissaoGerenciarListas = new Permissao(authority: 'ROLE_GERENCIAR_LISTAS').save(failOnError: true)
        Usuario usuarioNormal = new Usuario(username: 'usuario', password: 'normal').save(failOnError: true)
        UsuarioPermissao autorizacaoGerenciaListas = new UsuarioPermissao(usuario: usuarioNormal,
                permissao: permissaoGerenciarListas).save(failOnError: true)
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


