package controller.converters;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.applications.ApplicationTypeEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = ApplicationTypeEnum.class)
public class ApplicationTypeConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Fitosanitaria")) {
            return ApplicationTypeEnum.PHYTOSANITARY;
        }
        if (value.equals("Fertilización")) {
            return ApplicationTypeEnum.FERTILIZATION;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof ApplicationTypeEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
