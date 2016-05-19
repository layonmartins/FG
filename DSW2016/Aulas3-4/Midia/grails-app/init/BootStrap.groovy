import br.ufscar.dc.dsw.Ator
import br.ufscar.dc.dsw.Cd
import br.ufscar.dc.dsw.Dvd
import br.ufscar.dc.dsw.Faixa
import br.ufscar.dc.dsw.Jogo
import br.ufscar.dc.dsw.Papel
import br.ufscar.dc.dsw.Usuario
import br.ufscar.dc.dsw.UsuarioPapel

class BootStrap {

    def init = { servletContext ->

        def adminPapel = Papel.findByAuthority("ROLE_ADMIN") ?:
                new Papel(authority: "ROLE_ADMIN").save()

        def admin = new Usuario(
                username: "admin",
                password: "admin",
                nome: "Administrador",
                enabled : true
        ).save(failOnError: true)

        UsuarioPapel.create(admin,adminPapel)

        println 'populando usuário admin - ok'

        def userPapel = Papel.findByAuthority("ROLE_USER")?:
                new Papel(authority: "ROLE_USER").save()

        def ana = new Usuario(
                username: 'ana',
                password: 'ana',
                nome: "Ana",
                enabled: true
        ).save(failOnError: true)

        UsuarioPapel.create(ana,userPapel)

        println 'populando usuário ana - ok'

        def cd = new Cd(titulo: 'The Wall', ano: 1976, artista: 'Pink Floyd', usuario: ana).save(failOnError: true)

        new Faixa(nome:'Another Brick in the Wall', duracao: 190, cd: cd).save(failOnError: true)

        def dvd = new Dvd(titulo: 'O Senhor dos Anéis', ano: 2010, diretor: 'Peter Jackson', usuario: ana).save(failOnError: true)

        new Ator(nome: 'Elijah Wood', papel: 'Frodo Bolseiro', dvd: dvd).save(failOnError: true)

        new Jogo(titulo: 'Fifa 2016', ano: 2016, genero: 'Esporte', usuario: ana).save(failOnError: true)

    }
    def destroy = {
    }
}
