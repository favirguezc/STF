/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import javax.faces.bean.ManagedBean;
import model.finances.cost.CostTypeEnum;

/**
 *
 * @author fredy
 */
@ManagedBean(name="tipoCostoController")
public class TipoCostoController {
    
    public CostTypeEnum[] getTipoCostoValues(){
        return CostTypeEnum.values();
    }
}
