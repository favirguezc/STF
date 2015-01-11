<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Variable</title>
            <link rel="stylesheet" type="text/css" href="/Web/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Variable</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{variable.variable.id}" title="Id" />
                    <h:outputText value="Nombre:"/>
                    <h:inputText id="nombre" value="#{variable.variable.nombre}" title="Nombre" required="true" requiredMessage="The nombre field is required." />
                    <h:outputText value="Abreviacion:"/>
                    <h:inputText id="abreviacion" value="#{variable.variable.abreviacion}" title="Abreviacion" required="true" requiredMessage="The abreviacion field is required." />
                    <h:outputText value="TipoDeValoracion:"/>
                    <h:inputText id="tipoDeValoracion" value="#{variable.variable.tipoDeValoracion}" title="TipoDeValoracion" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{variable.edit}" value="Save">
                    <f:param name="jsfcrud.currentVariable" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][variable.variable][variable.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{variable.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentVariable" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][variable.variable][variable.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{variable.listSetup}" value="Show All Variable Items" immediate="true"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
