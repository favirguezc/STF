/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import modelo.produccion.monitoreo.TipoDeValoracion;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "tipoDeValoracionController")
public class TipoDeValoracionController {
    
    public TipoDeValoracion[] getTipoDeValoracionValues(){
        return TipoDeValoracion.values();
    }
}
