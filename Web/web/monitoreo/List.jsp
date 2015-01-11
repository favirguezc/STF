<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Monitoreo Items</title>
            <link rel="stylesheet" type="text/css" href="/Web/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Monitoreo Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Monitoreo Items Found)<br />" rendered="#{monitoreo.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{monitoreo.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{monitoreo.pagingInfo.firstItem + 1}..#{monitoreo.pagingInfo.lastItem} of #{monitoreo.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{monitoreo.prev}" value="Previous #{monitoreo.pagingInfo.batchSize}" rendered="#{monitoreo.pagingInfo.firstItem >= monitoreo.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{monitoreo.next}" value="Next #{monitoreo.pagingInfo.batchSize}" rendered="#{monitoreo.pagingInfo.lastItem + monitoreo.pagingInfo.batchSize <= monitoreo.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{monitoreo.next}" value="Remaining #{monitoreo.pagingInfo.itemCount - monitoreo.pagingInfo.lastItem}"
                                   rendered="#{monitoreo.pagingInfo.lastItem < monitoreo.pagingInfo.itemCount && monitoreo.pagingInfo.lastItem + monitoreo.pagingInfo.batchSize > monitoreo.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{monitoreo.monitoreoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="NumeroDeMonitoreo"/>
                            </f:facet>
                            <h:outputText value="#{item.numeroDeMonitoreo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Fecha"/>
                            </f:facet>
                            <h:outputText value="#{item.fecha}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{monitoreo.detailSetup}">
                                <f:param name="jsfcrud.currentMonitoreo" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][monitoreo.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{monitoreo.editSetup}">
                                <f:param name="jsfcrud.currentMonitoreo" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][monitoreo.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{monitoreo.destroy}">
                                <f:param name="jsfcrud.currentMonitoreo" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][monitoreo.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{monitoreo.createSetup}" value="New Monitoreo"/>
                <br />


            </h:form>
        </body>
    </html>
</f:view>
