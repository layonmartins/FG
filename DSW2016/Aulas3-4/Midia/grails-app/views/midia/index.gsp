<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'midia.label', default: 'Midia')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-midia" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link controller="logout">Logout</g:link></li>
    </ul>
</div>

<div id="list-midia" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table>
        <thead>
        <tr>

            <g:sortableColumn property="nome"
                              title="${message(code: 'midia.titulo.label',
                                      default: 'Título')}"/>

            <th><g:message code="midia.ano.label" default="Ano"/></th>

            </th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${midiaList}" status="i" var="midia">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${midia.id}">${fieldValue(bean: midia, field: "titulo")}</g:link></td>

                <td>${fieldValue(bean: midia, field: "ano")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${midiaCount ?: 0}"/>
    </div>
</div>
</body>
</html>