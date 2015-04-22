package controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.administration.SexEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = SexEnum.class)
public class SexEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Masculino")) {
            return SexEnum.MASCULINE;
        }
        if (value.equals("Femenino")) {
            return SexEnum.FEMENINE;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof SexEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
