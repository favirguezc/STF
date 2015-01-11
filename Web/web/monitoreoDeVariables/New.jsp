<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New MonitoreoDeVariables</title>
            <link rel="stylesheet" type="text/css" href="/Web/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New MonitoreoDeVariables</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{monitoreoDeVariables.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Monitoreo:"/>
                    <h:selectOneMenu id="monitoreo" value="#{monitoreoDeVariables.monitoreoDeVariables.monitoreo}" title="Monitoreo" required="true" requiredMessage="The monitoreo field is required." >
                        <f:selectItems value="#{monitoreo.monitoreoItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Modulo:"/>
                    <h:selectOneMenu id="modulo" value="#{monitoreoDeVariables.monitoreoDeVariables.modulo}" title="Modulo" required="true" requiredMessage="The modulo field is required." >
                        <f:selectItems value="#{modulo.moduloItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Variable:"/>
                    <h:selectOneMenu id="variable" value="#{monitoreoDeVariables.monitoreoDeVariables.variable}" title="Variable" required="true" requiredMessage="The variable field is required." >
                        <f:selectItems value="#{variable.variableItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Valor:"/>
                    <h:inputText id="valor" value="#{monitoreoDeVariables.monitoreoDeVariables.valor}" title="Valor" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{monitoreoDeVariables.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{monitoreoDeVariables.listSetup}" value="Show All MonitoreoDeVariables Items" immediate="true"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
