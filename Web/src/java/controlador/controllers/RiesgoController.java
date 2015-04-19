/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import javax.faces.bean.ManagedBean;
import model.monitoring.RiskEnum;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "riesgoController")
public class RiesgoController {
    
    public RiskEnum[] getRiesgoValues(){
        return RiskEnum.values();
    }
}
