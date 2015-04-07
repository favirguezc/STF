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
import modelo.produccion.aplicaciones.Unidades;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = Unidades.class)
public class UnidadesConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Cc")) {
            return Unidades.CC;
        }
        if (value.equals("Gr")) {
            return Unidades.GR;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Unidades) {
            return value + "";
        } else {
            return null;
        }
    }
}
