/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import modelo.produccion.cosecha.TipoDeFresa;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "tipoDeFresaController")
public class TipoDeFresaController {

    public TipoDeFresa[] getTipoDeFresaValues() {
        return TipoDeFresa.values();
    }
}
