class LoginTagLib {
    //static defaultEncodeAs = [taglib:'html']
    def springSecurityService
    def loginControl = {

        if (springSecurityService.isLoggedIn()) {
            def usuario = springSecurityService.getCurrentUser()
            def authority = usuario.getAuthorities()[0].getAuthority()
            def papel

            def span = "<span style=\"text-align:center;padding-left:25px;padding-right:25px\">"

            StringBuilder sb = new StringBuilder();

            if (authority.equals('ROLE_ADMIN')) {
                papel = "Administrador"
            } else if (authority.equals('ROLE_CLIENTE')) {
                papel = "Cliente"
                if (session.conta) {
                    sb.append("Conta: ")
                    sb.append(session.conta)
                    sb.append(" [")
                    session.agencia.attach()
                    sb.append(session.agencia)
                    sb.append("]")
                    sb.append(span)
                }
            } else {
                papel = "Gerente"
                sb.append("Agência: ")
                sb.append(session.agencia)
            }

            out << span
            out << papel
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

