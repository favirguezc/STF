<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Variable Items</title>
            <link rel="stylesheet" type="text/css" href="/Web/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Variable Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Variable Items Found)<br />" rendered="#{variable.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{variable.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{variable.pagingInfo.firstItem + 1}..#{variable.pagingInfo.lastItem} of #{variable.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{variable.prev}" value="Previous #{variable.pagingInfo.batchSize}" rendered="#{variable.pagingInfo.firstItem >= variable.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{variable.next}" value="Next #{variable.pagingInfo.batchSize}" rendered="#{variable.pagingInfo.lastItem + variable.pagingInfo.batchSize <= variable.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{variable.next}" value="Remaining #{variable.pagingInfo.itemCount - variable.pagingInfo.lastItem}"
                                   rendered="#{variable.pagingInfo.lastItem < variable.pagingInfo.itemCount && variable.pagingInfo.lastItem + variable.pagingInfo.batchSize > variable.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{variable.variableItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Nombre"/>
                            </f:facet>
                            <h:outputText value="#{item.nombre}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Abreviacion"/>
                            </f:facet>
                            <h:outputText value="#{item.abreviacion}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TipoDeValoracion"/>
                            </f:facet>
                            <h:outputText value="#{item.tipoDeValoracion}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{variable.detailSetup}">
                                <f:param name="jsfcrud.currentVariable" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][variable.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{variable.editSetup}">
                                <f:param name="jsfcrud.currentVariable" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][variable.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{variable.destroy}">
                                <f:param name="jsfcrud.currentVariable" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][variable.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{variable.createSetup}" value="New Variable"/>
                <br />


            </h:form>
        </body>
    </html>
</f:view>
