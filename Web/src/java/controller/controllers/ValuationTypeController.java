/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import javax.faces.bean.ManagedBean;
import model.monitoring.ValuationTypeEnum;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "valuationTypeController")
public class ValuationTypeController {
    
    public ValuationTypeEnum[] getValuationTypeValues(){
        return ValuationTypeEnum.values();
    }
}
