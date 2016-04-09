class LoginTagLib {
    def springSecurityService
    def loginControl = {
        if (springSecurityService.isLoggedIn()) {
            def usuario = springSecurityService.getCurrentUser() 
            def authority = usuario.getAuthorities()[0].getAuthority()
            def papel
            
            def span = "<span style=\"text-align:center;padding-left:25px;padding-right:25px\">"
            
            StringBuilder sb = new StringBuilder();
            
            if (session.contaCliente) {
                sb.append("Conta: ")
                sb.append(session.contaCliente.conta)
                sb.append(" [")
                sb.append(session.contaCliente.conta.agencia)
                sb.append("]")
                sb.append(span)
            } else if (session.agencia) { 
                sb.append("Agência: ")
                sb.append(session.agencia)
            }
            
            out << span
            out << session.papel
            out << "</span>"
            out << span
            out << sb
            out << "</span>"
            out << "<span style=\"padding-right:25px\">"
            out << """ [${link(controller: "logout"){"Logout"}}]"""
            out << "</span>"
        }
    }
}