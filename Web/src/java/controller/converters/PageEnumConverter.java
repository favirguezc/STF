package controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.administration.PageEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = PageEnum.class)
public class PageEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Aplicación")) {
            return PageEnum.APPLICATION;
        }
        if (value.equals("Caja")) {
            return PageEnum.Caja;
        }
        if (value.equals("Insumo")) {
            return PageEnum.CHEMICAL;
        }
        if (value.equals("Clasificación")) {
            return PageEnum.CLASSIFICATION;
        }
        if (value.equals("Compra")) {
            return PageEnum.Compra;
        }
        if (value.equals("Concepto Caja")) {
            return PageEnum.ConceptoCaja;
        }
        if (value.equals("Contrato")) {
            return PageEnum.CONTRACT;
        }
        if (value.equals("Costo")) {
            return PageEnum.Costo;
        }
        if (value.equals("Departamento")) {
            return PageEnum.DEPARTMENT;
        }
        if (value.equals("Finca")) {
            return PageEnum.FARM;
        }
        if (value.equals("Index")) {
            return PageEnum.INDEX;
        }
        if (value.equals("Labor")) {
            return PageEnum.JOB;
        }
        if (value.equals("Lluvia")) {
            return PageEnum.RAINFALL;
        }
        if (value.equals("Lote")) {
            return PageEnum.LOT;
        }
        if (value.equals("Módulo")) {
            return PageEnum.MODULECLASS;
        }
        if (value.equals("Monitoreo")) {
            return PageEnum.MONITORING;
        }
        if (value.equals("Monitoreo de Variables")) {
            return PageEnum.PARAMETERMONITORING;
        }
        if (value.equals("Municipio")) {
            return PageEnum.MUNICIPALITY;
        }
        if (value.equals("Nómina")) {
            return PageEnum.Nomina;
        }
        if (value.equals("Permiso")) {
            return PageEnum.PERMISSION;
        }
        if (value.equals("Persona")) {
            return PageEnum.PERSON;
        }
        if (value.equals("Recolección")) {
            return PageEnum.CROP;
        }
        if (value.equals("Temperatura")) {
            return PageEnum.TEMPERATURE;
        }
        if (value.equals("Termómetro")) {
            return PageEnum.THERMOMETER;
        }
        if (value.equals("Trabajo")) {
            return PageEnum.WORK;
        }
        if (value.equals("Trampa De Insectos")) {
            return PageEnum.INSECTTRAP;
        }
        if (value.equals("Variable")) {
            return PageEnum.MONITORABLEPARAMETER;
        }
        if (value.equals("Venta")) {
            return PageEnum.Venta;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof PageEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
