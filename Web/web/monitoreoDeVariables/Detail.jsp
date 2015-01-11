<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>MonitoreoDeVariables Detail</title>
            <link rel="stylesheet" type="text/css" href="/Web/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>MonitoreoDeVariables Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{monitoreoDeVariables.monitoreoDeVariables.id}" title="Id" />
                    <h:outputText value="Monitoreo:"/>
                    <h:panelGroup>
                        <h:outputText value="#{monitoreoDeVariables.monitoreoDeVariables.monitoreo}"/>
                        <h:panelGroup rendered="#{monitoreoDeVariables.monitoreoDeVariables.monitoreo != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{monitoreo.detailSetup}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentMonitoreo" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables.monitoreo][monitoreo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="monitoreoDeVariables"/>
                                <f:param name="jsfcrud.relatedControllerType" value="controlador.MonitoreoDeVariablesController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{monitoreo.editSetup}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentMonitoreo" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables.monitoreo][monitoreo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="monitoreoDeVariables"/>
                                <f:param name="jsfcrud.relatedControllerType" value="controlador.MonitoreoDeVariablesController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{monitoreo.destroy}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentMonitoreo" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables.monitoreo][monitoreo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="monitoreoDeVariables"/>
                                <f:param name="jsfcrud.relatedControllerType" value="controlador.MonitoreoDeVariablesController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Modulo:"/>
                    <h:panelGroup>
                        <h:outputText value="#{monitoreoDeVariables.monitoreoDeVariables.modulo}"/>
                        <h:panelGroup rendered="#{monitoreoDeVariables.monitoreoDeVariables.modulo != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{modulo.detailSetup}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentModulo" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables.modulo][modulo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="monitoreoDeVariables"/>
                                <f:param name="jsfcrud.relatedControllerType" value="controlador.MonitoreoDeVariablesController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{modulo.editSetup}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentModulo" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables.modulo][modulo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="monitoreoDeVariables"/>
                                <f:param name="jsfcrud.relatedControllerType" value="controlador.MonitoreoDeVariablesController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{modulo.destroy}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentModulo" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables.modulo][modulo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="monitoreoDeVariables"/>
                                <f:param name="jsfcrud.relatedControllerType" value="controlador.MonitoreoDeVariablesController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Variable:"/>
                    <h:panelGroup>
                        <h:outputText value="#{monitoreoDeVariables.monitoreoDeVariables.variable}"/>
                        <h:panelGroup rendered="#{monitoreoDeVariables.monitoreoDeVariables.variable != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{variable.detailSetup}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentVariable" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables.variable][variable.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="monitoreoDeVariables"/>
                                <f:param name="jsfcrud.relatedControllerType" value="controlador.MonitoreoDeVariablesController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{variable.editSetup}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentVariable" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables.variable][variable.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="monitoreoDeVariables"/>
                                <f:param name="jsfcrud.relatedControllerType" value="controlador.MonitoreoDeVariablesController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{variable.destroy}">
                                <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables][monitoreoDeVariables.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentVariable" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables.variable][variable.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="monitoreoDeVariables"/>
                                <f:param name="jsfcrud.relatedControllerType" value="controlador.MonitoreoDeVariablesController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Valor:"/>
                    <h:outputText value="#{monitoreoDeVariables.monitoreoDeVariables.valor}" title="Valor" />


                </h:panelGrid>
                <br />
                <h:commandLink action="#{monitoreoDeVariables.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables][monitoreoDeVariables.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{monitoreoDeVariables.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentMonitoreoDeVariables" value="#{jsfcrud_class['controlador.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][monitoreoDeVariables.monitoreoDeVariables][monitoreoDeVariables.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{monitoreoDeVariables.createSetup}" value="New MonitoreoDeVariables" />
                <br />
                <h:commandLink action="#{monitoreoDeVariables.listSetup}" value="Show All MonitoreoDeVariables Items"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
