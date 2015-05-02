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
import model.finances.expenses.CostTypeEnum;

/**
 *
 * @author John Fredy
 */
@FacesConverter(forClass = CostTypeEnum.class)
public class CostTypeEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Horas Maquina")) {
            return CostTypeEnum.MACHINETIME;
        }
        if (value.equals("Mano de Obra")) {
            return CostTypeEnum.MANPOWER;
        }
        if (value.equals("Insumos")) {
            return CostTypeEnum.CHEMICALS;
        }
        if (value.equals("Equipos e Implementos")) {
            return CostTypeEnum.TOOLS;
        }
        if (value.equals("Servicios")) {
            return CostTypeEnum.SERVICES;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof CostTypeEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
