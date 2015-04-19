/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import javax.faces.bean.ManagedBean;
import model.applications.AplicationMethodEnum;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "metodoDeAplicacionController")
public class MetodoDeAplicacionController {

    public AplicationMethodEnum[] getMetodoDeAplicacionValues(){
        return AplicationMethodEnum.values();
    }
    
}
