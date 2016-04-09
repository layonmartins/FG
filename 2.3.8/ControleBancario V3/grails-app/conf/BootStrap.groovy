
import br.ufscar.dc.dsw.Agencia
import br.ufscar.dc.dsw.Banco
import br.ufscar.dc.dsw.CaixaEletronico
import br.ufscar.dc.dsw.Cidade
import br.ufscar.dc.dsw.Cliente
import br.ufscar.dc.dsw.ClienteFisico
import br.ufscar.dc.dsw.ClienteJuridico
import br.ufscar.dc.dsw.ContaCliente
import br.ufscar.dc.dsw.ContaCorrente
import br.ufscar.dc.dsw.ContaPoupanca
import br.ufscar.dc.dsw.Endereco
import br.ufscar.dc.dsw.Estado
import br.ufscar.dc.dsw.Gerente
import br.ufscar.dc.dsw.Papel
import br.ufscar.dc.dsw.Transacao
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
        )
        
        admin.save()
        if (admin.hasErrors()) {
            println admin.errors
        }
        UsuarioPapel.create(admin,adminPapel)
       
        print 'populando usuário admin - ok'
        
        def sp = new Estado(sigla: 'SP', nome: 'São Paulo')
        
        sp.save()
        if (sp.hasErrors()) {
            println sp.errors
        }
        
        print 'populando estados - ok'
        
        def sanca = new Cidade(nome: 'São Carlos', estado: sp)
        
        sanca.save()
        if (sanca.hasErrors()) {
            println sanca.errors
        }
        
        def sampa = new Cidade(nome: 'São Paulo', estado: sp)
        
        sampa.save()
        if (sampa.hasErrors()) {
            println sampa.errors
        }
        
        print 'populando cidades - ok'
                        
        def end1 = new Endereco(logradouro: 'R. Conde do Pinhal', numero: 1909, 
            bairro: 'Centro', CEP: '13560-648', cidade: sanca)
        
        end1.save()
        if (end1.hasErrors()) {
            println end1.errors
        }
        
        def end2 = new Endereco(logradouro: 'R. Treze de Maio', numero: 1930, 
            bairro: 'Centro', CEP: '13560-647', cidade: sanca)
        
        end2.save()
        if (end2.hasErrors()) {
            println end2.errors
        }
        
        def end3 = new Endereco(logradouro: 'R. Nilton Coelho de Andrade',
            numero: 772, bairro: 'Vila Maria', CEP:'03092-324', cidade: sampa)
        
        end3.save()
        if (end3.hasErrors()) {
            println end3.errors
        }
        
        def end4 = new Endereco(logradouro: 'R. Humberto Manelli', numero: 50, 
            complemento: 'Apto 31', bairro: 'Jardim Gibertoni', 
            CEP:'13562-420', cidade: sanca)
        
        end4.save()
        if (end4.hasErrors()) {
            println end4.errors
        }
        
        print 'populando endereços - ok'
        
        def bb = new Banco(numero: 1, nome: 'Banco do Brasil', 
            CNPJ: '00.000.000/0001-91')
        
        bb.save()
        if (bb.hasErrors()) {
            println bb.errors
        }
        
        def santander = new Banco(numero: 33, nome: 'Santander', 
            CNPJ: '90.400.888/0001-42')
        
        santander.save()
        if (santander.hasErrors()) {
            println santander.errors
        }

        print 'populando bancos - ok'
        
        def agencia1 = new Agencia(numero: 1888, nome: 'Conde do Pinhal', 
            endereco: end1, banco: bb)
        
        agencia1.save()
        if (agencia1.hasErrors()) {
            println agencia1.errors
        }
        
        def agencia2 = new Agencia(numero: 24, nome: 'Treze de Maio', 
            endereco: end2, banco: santander)
        
        agencia2.save()
        if (agencia2.hasErrors()) {
            println agencia2.errors
        }
        
        print 'populando agências - ok'

        def gerentePapel = Papel.findByAuthority("ROLE_GERENTE")?:
        new Papel(authority: "ROLE_GERENTE").save()
        
        def gerente1 = new Gerente(username: 'carlos', password: 'carlos',
            enabled: true, nome: 'Carlos da Silva', rg: '1234 SSP/SP',
            CPF: '129.304.458-07', agencia: agencia1
        )
    
        gerente1.save()
        if (gerente1.hasErrors()) {
            println gerente1.errors
        }
        
        UsuarioPapel.create(gerente1, gerentePapel)
        
        def gerente2 = new Gerente(username: "joao", password: "joao",
            enabled: true, nome: 'João Maria', rg: '3467 SSP/RJ',            
            CPF: '018.990.444-50', agencia: agencia2
        )
    
        gerente2.save()
        if (gerente2.hasErrors()) {
            println gerente2.errors
        }
       
        UsuarioPapel.create(gerente2, gerentePapel)
        
        print 'populando gerentes - ok'
        
        def caixa1 = new CaixaEletronico(banco: bb, endereco: end1)
        
        caixa1.save()
        if (caixa1.hasErrors()) {
            println agencia1.errors
        }
        
        def caixa2 = new CaixaEletronico(banco: santander, endereco: end2)
        
        caixa2.save()
        if (caixa2.hasErrors()) {
            println agencia1.errors
        }
        
        print 'populando caixas eletrônicos - ok'

        def clientePapel = Papel.findByAuthority("ROLE_CLIENTE")?:
        new Papel(authority: "ROLE_CLIENTE").save()
        
        def cliFisico = new ClienteFisico(username: 'maria', password: 'maria',
            enabled: true, nome: 'Maria da Silva', 
            rg: '13567 SSP/SP', CPF: '018.990.444-50', endereco: end4,
            dtMoradia: new Date(), status: Cliente.ATIVO) 
        
        cliFisico.save()
        if (cliFisico.hasErrors()) {
            println cliFisico.errors
        }
        
        UsuarioPapel.create(cliFisico, clientePapel)
        
        def cliFisico2 = new ClienteFisico(username: 'pedro', password: 'pedro',
            enabled: false, nome: 'Pedro Soares', 
            rg: '13567 SSP/SP', CPF: '784.232.889-78', endereco: end4,
            dtMoradia: new Date(), status: Cliente.ATIVO) 
        
        cliFisico2.save()
        if (cliFisico2.hasErrors()) {
            println cliFisico2.errors
        }
        
        UsuarioPapel.create(cliFisico2, clientePapel)
        
        print 'populando clientes físicos - ok'
                
        def cliJuridico = new ClienteJuridico(username: 'cometa', 
            password: 'cometa', enabled: true, nome: 'Viação Cometa S/A', 
            CNPJ: '61.084.018/0001-03', endereco: end3,
            dtMoradia: new Date(), status: Cliente.ATIVO) 
        
        cliJuridico.save()
        if (cliJuridico.hasErrors()) {
            println cliJuridico.errors
        }
        
        UsuarioPapel.create(cliJuridico, clientePapel)
        
        print 'populando clientes jurídicos - ok'
        
        def corrente = new ContaCorrente(agencia: agencia1, 
            numero: '010414688', saldo: 1000.56d, limite: 500.00d,
            abertura: new Date("06/10/14")
        )
    
        corrente.save()
        if (corrente.hasErrors()) {
            println corrente.errors
        }
        
        print 'populando contas correntes - ok'
                
        def poupanca = new ContaPoupanca(agencia: agencia2, 
            numero: '261327', saldo: 1000.56d, juros: 0.50d,
            correcao: 1.20d, dia: 23, abertura: new Date("06/11/14")
        )
    
        poupanca.save()
        if (poupanca.hasErrors()) {
            println poupanca.errors
        }
        
        print 'populando contas poupanças - ok'
        
        def contaCli1 = new ContaCliente(conta: corrente, 
            cliente: cliJuridico, titular: true
        )
    
        contaCli1.save()
        if (contaCli1.hasErrors()) {
            println contaCli1.errors
        }
    
        def contaCli2 = new ContaCliente(conta: poupanca, 
            cliente: cliFisico, titular: true
        )
    
        contaCli2.save()
        if (contaCli2.hasErrors()) {
            println contaCli2.errors
        }
        
        print 'relacionando contas x clientes - ok'
        
	def deposito = new Transacao(contaCliente: contaCli2, caixaEletronico: caixa2, 
            valor: 50d, data: new Date("06/12/14"), quem: 'Próprio', motivo: 'Depósito',
            tipo: Transacao.CRÉDITO
        )
        
        deposito.save()
        if (deposito.hasErrors()) {
            println deposito.errors
        }
        
        poupanca.saldo += 50d;
        poupanca.save()
        
        if (poupanca.hasErrors()) {
            println poupanca.errors
        }
        
        print 'populando depositos - ok'
        
        def saque = new Transacao(contaCliente: contaCli1, caixaEletronico: caixa1, 
            valor: 100d, data: new Date("06/13/14"), quem: 'Próprio', motivo: 'Saque', 
            tipo: Transacao.DÉBITO)
        
        saque.save()
        if (saque.hasErrors()) {
            println saque.errors
        }
        corrente.saldo -= 100d;
        corrente.save()
        
        print 'populando saques - ok'
        
        def transf1 = new Transacao(contaCliente: contaCli1, 
            caixaEletronico: caixa2, valor: 25d, data: new Date("06/14/14"),
            quem: 'Próprio', motivo: 'Transferência', tipo: Transacao.DÉBITO)
        
        def transf2 = new Transacao(contaCliente: contaCli2, 
            caixaEletronico: caixa2, valor: 25d, data: new Date("06/14/14"),
            quem: 'Fulano de Tal', motivo: 'Transferência', 
            tipo: Transacao.CRÉDITO)
        
        transf1.save()
        if (transf1.hasErrors()) {
            println transf1.errors
        }
        corrente.saldo -= 25d;
        corrente.save()
        
        transf2.save()
        if (transf2.hasErrors()) {
            println transf2.errors
        }
        
        poupanca.saldo += 25d;
        poupanca.save()
        
        print 'populando transferencias - ok'
    }

    def destroy = {
    }
}
