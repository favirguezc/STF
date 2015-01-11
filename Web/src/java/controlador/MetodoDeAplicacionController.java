/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import modelo.produccion.aplicaciones.MetodoDeAplicacion;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "metodoDeAplicacionController")
public class MetodoDeAplicacionController {

    public MetodoDeAplicacion[] getMetodoDeAplicacionValues(){
        return MetodoDeAplicacion.values();
    }
    
}
