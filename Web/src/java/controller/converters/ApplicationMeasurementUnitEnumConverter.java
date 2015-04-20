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
import model.applications.ApplicationMeasurementUnitEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = ApplicationMeasurementUnitEnum.class)
public class ApplicationMeasurementUnitEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Cc")) {
            return ApplicationMeasurementUnitEnum.CC;
        }
        if (value.equals("Gr")) {
            return ApplicationMeasurementUnitEnum.GR;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof ApplicationMeasurementUnitEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
