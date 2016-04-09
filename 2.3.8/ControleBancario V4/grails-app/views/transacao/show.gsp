<%@ page import="br.ufscar.dc.dsw.Transacao" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'transacao.label', default: 'Transacao')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <r:require module="pure-all" />
        <resource:tabView />
        <resource:accordion skin="default" />
    </head>
    <body>
        <a href="#show-transacao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="pure-menu pure-menu-open pure-menu-horizontal">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                       <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>

                       <li>
                       <sec:access controller="transacao" action='create'>
                        <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
                            </sec:access>
                    </li>
        <li><g:link controller="logout">Logout</g:link></li>
                </ul>
            </div>
            <div id="show-transacao" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <richui:tabView id="tabView">
            <richui:tabLabels>
                <richui:tabLabel selected="true" title="${message(code:'transacao.main')}" />
                <richui:tabLabel title="${message(code:'transacao.value')}" />
                <richui:tabLabel title="${message(code:'transacao.data')}" />
                <richui:tabLabel title="${message(code:'transacao.quem')}" />
                <richui:tabLabel title="${message(code:'transacao.motivo')}" />
                <richui:tabLabel title="${message(code:'transacao.tipo')}" />
            </richui:tabLabels>

            <richui:tabContents>
                <richui:tabContent>
                    <richui:accordion>
                        <richui:accordionItem id="1" caption="Conta Cliente">
                            <g:if test="${transacaoInstance?.contaCliente}">
                                <g:link controller="contaCliente" action="show" id="${transacaoInstance?.contaCliente?.id}">${transacaoInstance?.contaCliente?.encodeAsHTML()}</g:link>
                            </g:if>
                        </richui:accordionItem>
                        <richui:accordionItem caption="Caixa Eletrônico">
                            <g:if test="${transacaoInstance?.caixaEletronico}">


                                <g:link controller="caixaEletronico" action="show" id="${transacaoInstance?.caixaEletronico?.id}">${transacaoInstance?.caixaEletronico?.encodeAsHTML()}</g:link>

                            </g:if>
                        </richui:accordionItem>
                    </richui:accordion>
                </richui:tabContent>

                <richui:tabContent>
                    <g:if test="${transacaoInstance?.valor}">                        
                        <g:formatNumber number="${transacaoInstance.valor}" type="currency" />
                    </g:if>
                </richui:tabContent>
                <richui:tabContent>
                    <g:if test="${transacaoInstance?.data}">
                        <g:formatDate date="${transacaoInstance?.data}" type="datetime" style="LONG" timeStyle="SHORT"/>
                    </g:if>
                </richui:tabContent>
                <richui:tabContent>
                    <g:if test="${transacaoInstance?.quem}">
                        <g:fieldValue bean="${transacaoInstance}" field="quem"/>
                    </g:if>
                </richui:tabContent>
                <richui:tabContent>
                    <g:if test="${transacaoInstance?.motivo}">
                        <g:fieldValue bean="${transacaoInstance}" field="motivo"/>
                    </g:if>
                </richui:tabContent>
                <richui:tabContent>
                    <g:if test="${transacaoInstance?.tipo}">
                        <g:fieldValue bean="${transacaoInstance}" field="tipo"/></span>
                    </g:if>
                </richui:tabContent>
            </richui:tabContents>
        </richui:tabView>    

        <g:form url="[resource:transacaoInstance, action:'delete']" method="DELETE">
            <fieldset class="buttons">
                <sec:access controller="transacao" action='edit'>
                    <g:link class="edit" action="edit" resource="${transacaoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    </sec:access>
                    <sec:access controller="transacao" action='delete'>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </sec:access>
            </fieldset>
        </g:form>
    </div>
</body>
</html>
