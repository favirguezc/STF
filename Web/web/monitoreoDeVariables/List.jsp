<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing MonitoreoDeVariables Items</title>
            <link rel="stylesheet" type="text/css" href="/Web/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing MonitoreoDeVariables Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No MonitoreoDeVariables Items Found)<br />" rendered="#{monitoreoDeVariables.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{monitoreoDeVariables.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{monitoreoDeVariables.pagingInfo.firstItem + 1}..#{monitoreoDeVariables.pagingInfo.lastItem} of #{monitoreoDeVariables.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{monitoreoDeVariables.prev}" value="Previous #{monitoreoDeVariables.pagingInfo.batchSize}" rendered="#{monitoreoDeVariables.pagingInfo.firstItem >= monitoreoDeVariables.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{monitoreoDeVariables.next}" value="Next #{monitoreoDeVariables.pagingInfo.batchSize}" rendered="#{monitoreoDeVariables.pagingInfo.lastItem + monitoreoDeVariables.pagingInfo.batchSize <= monitoreoDeVariables.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{monitoreoDeVariables.next}" value="Remaining #{monitoreoDeVariables.pagingInfo.itemCount - monitoreoDeVariables.pagingInfo.lastItem}"
                                   rendered="#{monitoreoDeVariables.pagingInfo.lastItem < monitoreoDeVariables.pagingInfo.itemCount && monitoreoDeVariables.pagingInfo.lastItem + monitoreoDeVariables.pagingInfo.batchSize > monitoreoDeVariables.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{monitoreoDeVariables.monitoreoDeVariablesItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Monitoreo"/>
                            </f:facet>
                            <h:outputText value="#{item.monitoreo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Modulo"/>
                            </f:facet>
                            <h:outputText value="#{item.modulo}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Variable"/>
                            </f:facet>
                            <h:outputText value="#{item.variable}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Valor"/>
                            </f:facet>
                            <h:outputText value="#{item.valor}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{monitoreoDeVariables.detailSetup}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{monitoreoDeVariables.editSetup}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{monitoreoDeVariables.destroy}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{monitoreoDeVariables.createSetup}" value="New MonitoreoDeVariables"/>
                <br />


            </h:form>
        </body>
    </html>
</f:view>
