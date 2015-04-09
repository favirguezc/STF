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
import modelo.produccion.administracion.Rol;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = Rol.class)
public class RolConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Asistente Administrativo")) {
            return Rol.ASISTENTE_ADMINISTRATIVO;
        }
        if (value.equals("Cliente")) {
            return Rol.CLIENTE;
        }
        if (value.equals("Contador")) {
            return Rol.CONTADOR;
        }
        if (value.equals("Especialista")) {
            return Rol.ESPECIALISTA;
        }
        if (value.equals("Gerente")) {
            return Rol.GERENTE;
        }
        if (value.equals("Jefe de Campo")) {
            return Rol.JEFE_DE_CAMPO;
        }
        if (value.equals("Socio")) {
            return Rol.SOCIO;
        }
        if (value.equals("Trabajador")) {
            return Rol.TRABAJADOR;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Rol) {
            return value.toString() + "";
        } else {
            return null;
        }
    }

}
