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
import model.finances.expenses.CostItemEnum;

/**
 *
 * @author John Fredy
 */
@FacesConverter(forClass = CostItemEnum.class)
public class CostItemEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Horas Maquina")) {
            return CostItemEnum.MACHINETIME;
        }
        if (value.equals("Horas")) {
            return CostItemEnum.HOURS;
        }
        if (value.equals("Jornales")) {
            return CostItemEnum.DAILYWAGE;
        }
        if (value.equals("Plantulas")) {
            return CostItemEnum.SEEDLINGS;
        }
        if (value.equals("Litros")) {
            return CostItemEnum.LITRES;
        }
        if (value.equals("Rollos de 1000 mt")) {
            return CostItemEnum.ROLLOF100METRES;
        }
        if (value.equals("Kilos")) {
            return CostItemEnum.KILOS;
        }
        if (value.equals("Metros")) {
            return CostItemEnum.METRES;
        }
        if (value.equals("Unidades")) {
            return CostItemEnum.UNITS;
        }
        if (value.equals("Galones")) {
            return CostItemEnum.GALLONS;
        }
        if (value.equals("Uniforme/Operario")) {
            return CostItemEnum.UNIFORMWORKER;
        }
        if (value.equals("Mensual")) {
            return CostItemEnum.MONTHLY;
        }
        if (value.equals("2 Visitas Mensuales")) {
            return CostItemEnum.TWOMONTHLYVISITS;
        }
        if (value.equals("Ninguno")) {
            return CostItemEnum.NOTHING;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof CostItemEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
