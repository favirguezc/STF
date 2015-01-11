<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Monitoreo</title>
            <link rel="stylesheet" type="text/css" href="/Web/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Monitoreo</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{monitoreo.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="NumeroDeMonitoreo:"/>
                    <h:inputText id="numeroDeMonitoreo" value="#{monitoreo.monitoreo.numeroDeMonitoreo}" title="NumeroDeMonitoreo" />
                    <h:outputText value="Fecha (MM/dd/yyyy):"/>
                    <h:inputText id="fecha" value="#{monitoreo.monitoreo.fecha}" title="Fecha" required="true" requiredMessage="The fecha field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{monitoreo.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{monitoreo.listSetup}" value="Show All Monitoreo Items" immediate="true"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
