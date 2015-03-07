package controlador.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.produccion.aplicaciones.TipoDeAplicacion;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = TipoDeAplicacion.class)
public class TipoDeAplicacionConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Fitosanitaria")) {
            return TipoDeAplicacion.FITOSANITARIA;
        }
        if (value.equals("Fertilización")) {
            return TipoDeAplicacion.FERTILIZACION;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof TipoDeAplicacion) {
            return value + "";
        } else {
            return null;
        }
    }
}
