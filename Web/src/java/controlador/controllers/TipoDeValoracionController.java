/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import javax.faces.bean.ManagedBean;
import model.monitoring.ValuationTypeEnum;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "tipoDeValoracionController")
public class TipoDeValoracionController {
    
    public ValuationTypeEnum[] getTipoDeValoracionValues(){
        return ValuationTypeEnum.values();
    }
}
