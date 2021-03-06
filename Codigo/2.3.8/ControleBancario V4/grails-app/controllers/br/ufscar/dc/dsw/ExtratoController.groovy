package br.ufscar.dc.dsw

import java.text.SimpleDateFormat
import org.springframework.security.access.annotation.Secured

@Secured('ROLE_CLIENTE')
class ExtratoController {
    
    def index() {
        
        def linhas = getLinhas()
        
        if(params?.format && params.format == "pdf"){
            render( filename:"extrato.pdf",
                view:"/extrato/_pdf",
                model:[lines: linhas, contaCliente:session.contaCliente],
                marginLeft:20,
                marginTop:35,
                marginBottom:20,
                marginRight:20,
                headerSpacing:10,
            )   
        }
        model:[lines: linhas]
    }
    
    List<Line> getLinhas() {
        def conta = Conta.get(session.contaCliente.conta.id)
        def saldo = conta.saldo
		
        def results = Transacao.findAllByContaCliente(session.contaCliente, [sort:"data"])
        
        results.each {
            saldo -= it.getValorReal()
        }
        
        List<Line> linhas = new ArrayList<Line>();
        Line linha = new Line(session.contaCliente.conta.abertura, "ABERTURA", saldo)
        linhas.add(linha)
                
        results.each {
            saldo += it.getValorReal()
            linha = new Line(it.data, it.tipo, saldo, it.valor, it.motivo)
            linhas.add(linha)
        }
        
        linha = new Line(new Date(), "SALDO ATUAL", saldo)
        linhas.add(linha)
        return linhas
    }
    
    def chart = {
        def columns = [['date', 'Dia'], ['number', 'Saldo (R$)']]
        def lines = []
        
        def linhas = getLinhas()
        
        def anterior = null
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy")
        linhas.reverse().each {
            def atual = formato.format(it.data)
            if (!atual.equals(anterior)) {
                lines.add([it.data, it.saldo])                
            }
            anterior = it.data
        }
          
        ["columns": columns, "lines": lines]
    }
}
