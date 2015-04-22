package controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.crop.ClassificationTypeEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = ClassificationTypeEnum.class)
public class ClassificationTypeEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Extra")) {
            return ClassificationTypeEnum.EXTRA;
        }
        if (value.equals("Primera")) {
            return ClassificationTypeEnum.ONE;
        }
        if (value.equals("Segunda")) {
            return ClassificationTypeEnum.TWO;
        }
        if (value.equals("Tercera")) {
            return ClassificationTypeEnum.THREE;
        }
        if (value.equals("Cuarta")) {
            return ClassificationTypeEnum.FOUR;
        }
        if (value.equals("Quinta")) {
            return ClassificationTypeEnum.FIVE;
        }
        if (value.equals("Muñeco")) {
            return ClassificationTypeEnum.DUMMIE;
        }
        if (value.equals("Dañada")) {
            return ClassificationTypeEnum.DAMAGED;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof ClassificationTypeEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
