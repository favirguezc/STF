/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import javax.faces.bean.ManagedBean;
import modelo.produccion.aplicaciones.TipoDeAplicacion;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "tipoDeAplicacionController")
public class TipoDeAplicacionController {
    
    public TipoDeAplicacion[] getTipoDeAplicacionValues(){
        return TipoDeAplicacion.values();
    }
}