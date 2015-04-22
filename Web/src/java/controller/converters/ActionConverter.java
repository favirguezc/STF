package controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.util.Action;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = Action.class)
public class ActionConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Leer")) {
            return Action.READ;
        }
        if (value.equals("Escribir")) {
            return Action.WRITE;
        }
        if (value.equals("Eliminar")) {
            return Action.DELETE;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Action) {
            return value + "";
        } else {
            return null;
        }
    }
}
