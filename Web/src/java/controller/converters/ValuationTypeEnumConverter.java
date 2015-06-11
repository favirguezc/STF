package controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.monitoring.ValuationTypeEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = ValuationTypeEnum.class)
public class ValuationTypeEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Conteo")) {
            return ValuationTypeEnum.COUNT;
        }
        if (value.equals("Riesgo")) {
            return ValuationTypeEnum.RISK;
        }
        if (value.equals("Relación")) {
            return ValuationTypeEnum.RELATION;
        }
        if (value.equals("Presencia")) {
            return ValuationTypeEnum.PRESENCE;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof ValuationTypeEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
