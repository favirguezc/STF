/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.produccion.cosecha.TipoDeFresa;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = TipoDeFresa.class)
public class TipoDeFresaConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Extra")) {
            return TipoDeFresa.EXTRA;
        }
        if (value.equals("Primera")) {
            return TipoDeFresa.PRIMERA;
        }
        if (value.equals("Segunda")) {
            return TipoDeFresa.SEGUNDA;
        }
        if (value.equals("Tercera")) {
            return TipoDeFresa.TERCERA;
        }
        if (value.equals("Cuarta")) {
            return TipoDeFresa.CUARTA;
        }
        if (value.equals("Quinta")) {
            return TipoDeFresa.QUINTA;
        }
        if (value.equals("Deforme")) {
            return TipoDeFresa.DEFORME;
        }
        if (value.equals("Dañada")) {
            return TipoDeFresa.DANADA;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof TipoDeFresa) {
            return value + "";
        } else {
            return null;
        }
    }
}
