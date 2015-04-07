/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.produccion.monitoreo.Riesgo;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = Riesgo.class)
public class RiesgoConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("No")) {
            return Riesgo.NO;
        }
        if (value.equals("Bajo")) {
            return Riesgo.BAJO;
        }
        if (value.equals("Medio")) {
            return Riesgo.MEDIO;
        }
        if (value.equals("Alto")) {
            return Riesgo.ALTO;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Riesgo) {
            return value + "";
        } else {
            return null;
        }
    }
}
