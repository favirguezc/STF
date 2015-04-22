package controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.administration.RHEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = RHEnum.class)
public class RHEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Positivo")) {
            return RHEnum.POSITIVE;
        }
        if (value.equals("Negativo")) {
            return RHEnum.NEGATIVE;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof RHEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
