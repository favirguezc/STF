/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.produccion.monitoreo.TipoDeValoracion;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = TipoDeValoracion.class)
public class TipoDeValoracionConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Conteo")) {
            return TipoDeValoracion.CONTEO;
        }
        if (value.equals("Riesgo")) {
            return TipoDeValoracion.RIESGO;
        }
        if (value.equals("Relación")) {
            return TipoDeValoracion.RELACION;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof TipoDeValoracion) {
            return value + "";
        } else {
            return null;
        }
    }
}
