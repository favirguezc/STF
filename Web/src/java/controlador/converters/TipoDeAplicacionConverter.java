package controlador.converters;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.applications.AplicationTypeEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = AplicationTypeEnum.class)
public class TipoDeAplicacionConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Fitosanitaria")) {
            return AplicationTypeEnum.PHYTOSANITARY;
        }
        if (value.equals("Fertilización")) {
            return AplicationTypeEnum.FERTILIZATION;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof AplicationTypeEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
