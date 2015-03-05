/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import modelo.finanzas.costo.TipoCosto;

/**
 *
 * @author fredy
 */
@ManagedBean(name="tipoCostoController")
public class TipoCostoController {
    
    public TipoCosto[] getTipoCostoValues(){
        return TipoCosto.values();
    }
}
