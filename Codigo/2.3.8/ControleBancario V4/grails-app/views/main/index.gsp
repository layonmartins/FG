<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:javascript library="jquery" />
    <r:require module="pure-all" />
</head>
<body>

    <div class="pure-menu pure-menu-open pure-menu-horizontal">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link controller="logout">Logout</g:link></li>
            </ul>
        </div>

    <h2><g:message code="main.options"/></h2>

    
    <div class="pure-menu pure-menu-open">

        <ul>
            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                <g:set var="name" value="${c.logicalPropertyName}" />
                <g:if test="${name != 'logout' && name != 'login' && name != 'main'}">
                    <sec:access url='${createLink(controller: c.logicalPropertyName, base: "/")}'>

                        <li><g:link controller="${c.logicalPropertyName}">${c.naturalName.replace(" Controller","")}</g:link></li>

                    </sec:access>
                </g:if>
            </g:each>
        </ul>

    </div>
</body>
</html>
