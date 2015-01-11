<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Variable Detail</title>
            <link rel="stylesheet" type="text/css" href="/Web/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Variable Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{variable.variable.id}" title="Id" />
                    <h:outputText value="Nombre:"/>
                    <h:outputText value="#{variable.variable.nombre}" title="Nombre" />
                    <h:outputText value="Abreviacion:"/>
                    <h:outputText value="#{variable.variable.abreviacion}" title="Abreviacion" />
                    <h:outputText value="TipoDeValoracion:"/>
                    <h:outputText value="#{variable.variable.tipoDeValoracion}" title="TipoDeValoracion" />


                </h:panelGrid>
                <br />
                <h:commandLink action="#{variable.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentVariable" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][variable.variable][variable.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{variable.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentVariable" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][variable.variable][variable.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{variable.createSetup}" value="New Variable" />
                <br />
                <h:commandLink action="#{variable.listSetup}" value="Show All Variable Items"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
