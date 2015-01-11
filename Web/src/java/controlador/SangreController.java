/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import modelo.produccion.administracion.GrupoSanguineo;
import modelo.produccion.administracion.RH;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "sangreController")
public class SangreController {

    /**
     * Creates a new instance of GrupoSanguineoController
     */
    public SangreController() {
    }

    public RH[] getRhValues() {
        return RH.values();
    }

    public GrupoSanguineo[] getGrupoSanguineoValues() {
        return GrupoSanguineo.values();
    }

}
