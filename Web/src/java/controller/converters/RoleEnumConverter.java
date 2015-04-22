package controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.administration.RoleEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = RoleEnum.class)
public class RoleEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Gerente")) {
            return RoleEnum.MANAGER;
        }
        if (value.equals("Socio")) {
            return RoleEnum.PARTNER;
        }
        if (value.equals("Jefe de Campo")) {
            return RoleEnum.FIELD_BOSS;
        }
        if (value.equals("Asistente Administrativo")) {
            return RoleEnum.ADMINISTRATIVE_ASSISTANT;
        }
        if (value.equals("Contador")) {
            return RoleEnum.ACCOUNTANT;
        }
        if (value.equals("Especialista")) {
            return RoleEnum.SPECIALIST;
        }
        if (value.equals("Trabajador")) {
            return RoleEnum.WORKER;
        }
        if (value.equals("Cliente")) {
            return RoleEnum.CLIENT;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof RoleEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
