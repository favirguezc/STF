/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import javax.faces.bean.ManagedBean;
import modelo.produccion.aplicaciones.Unidades;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "unidadesController")
public class UnidadesController {

    public Unidades[] getUnidadesValues() {
        return Unidades.values();
    }
}