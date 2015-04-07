/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import javax.faces.bean.ManagedBean;
import modelo.produccion.administracion.Pagina;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "paginaController")
public class PaginaController {
    
    public Pagina[] getPaginaValues(){
        return Pagina.values();
    }
    
}
