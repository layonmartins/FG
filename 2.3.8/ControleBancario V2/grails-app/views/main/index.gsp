<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:javascript library="jquery" />
    </head>
    <body>
        <div id="status" role="complementary">

            <h1>Opções</h1>
            <table>
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                    <g:set var="name" value="${c.logicalPropertyName}" />
                    <g:if test="${name != 'logout' && name != 'login' && name != 'main'}">
                        <sec:access url='${createLink(controller: c.logicalPropertyName, base: "/")}'>
                            <tr><td>    
                                    <g:link controller="${c.logicalPropertyName}">${c.naturalName.replace(" Controller","")}</g:link>
                                    </td></tr>
                            </sec:access>
                    </g:if>
                </g:each>
            </table>
        </div>
    </body>
</html>
