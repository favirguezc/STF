package controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.applications.ApplicationMethodEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = ApplicationMethodEnum.class)
public class ApplicationMethodEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Inyectado")) {
            return ApplicationMethodEnum.INJECTED;
        }
        if (value.equals("Riego")) {
            return ApplicationMethodEnum.IRRIGATION;
        }
        if (value.equals("Foliar")) {
            return ApplicationMethodEnum.FOLIAR;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof ApplicationMethodEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
