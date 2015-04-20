/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import javax.faces.bean.ManagedBean;
import modelo.finanzas.costo.ItemCosto;

/**
 *
 * @author John Fredy
 */
@ManagedBean(name="itemCostoController")
public class ItemCostoController {
    
    public ItemCosto[] getItemCostoValues(){
        return ItemCosto.values();
    }
}
