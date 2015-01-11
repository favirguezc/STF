<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Monitoreo</title>
            <link rel="stylesheet" type="text/css" href="/Web/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Monitoreo</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{monitoreo.monitoreo.id}" title="Id" />
                    <h:outputText value="NumeroDeMonitoreo:"/>
                    <h:inputText id="numeroDeMonitoreo" value="#{monitoreo.monitoreo.numeroDeMonitoreo}" title="NumeroDeMonitoreo" />
                    <h:outputText value="Fecha (MM/dd/yyyy):"/>
                    <h:inputText id="fecha" value="#{monitoreo.monitoreo.fecha}" title="Fecha" required="true" requiredMessage="The fecha field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{monitoreo.edit}" value="Save">
                    <f:param name="jsfcrud.currentMonitoreo" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreo.monitoreo][monitoreo.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{monitoreo.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentMonitoreo" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreo.monitoreo][monitoreo.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{monitoreo.listSetup}" value="Show All Monitoreo Items" immediate="true"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
