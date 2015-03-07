/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = boolean.class)
public class BooleanConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return value.equals("Si");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Boolean) {
            Boolean o = (Boolean) value;
            if (o) {
                return "Si";
            }
            return "No";
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "value {0} is of type {1}; expected type: {2}", new Object[]{value, value.getClass().getName(), Boolean.class.getName()});
            return null;
        }
    }

}
