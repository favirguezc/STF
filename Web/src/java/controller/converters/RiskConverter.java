/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.monitoring.RiskEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = RiskEnum.class)
public class RiskConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("No")) {
            return RiskEnum.NO;
        }
        if (value.equals("Bajo")) {
            return RiskEnum.LOW;
        }
        if (value.equals("Medio")) {
            return RiskEnum.MEDIUM;
        }
        if (value.equals("Alto")) {
            return RiskEnum.HIGH;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof RiskEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
