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
import model.finances.incomes.PaymentMethodEnum;

/**
 *
 * @author John Fredy
 */
@FacesConverter(forClass = PaymentMethodEnum.class)
public class PaymentMethodEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Efectivo")) {
            return PaymentMethodEnum.CASH;
        }
        if (value.equals("Consignación")) {
            return PaymentMethodEnum.CONSIGNMENT;
        }
        if (value.equals("Crédito")) {
            return PaymentMethodEnum.CREDIT;
        }
        if (value.equals("Débito")) {
            return PaymentMethodEnum.DEBIT;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof PaymentMethodEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
