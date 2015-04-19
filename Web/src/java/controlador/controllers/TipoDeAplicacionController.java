/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import javax.faces.bean.ManagedBean;
import model.applications.AplicationTypeEnum;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "tipoDeAplicacionController")
public class TipoDeAplicacionController {
    
    public AplicationTypeEnum[] getTipoDeAplicacionValues(){
        return AplicationTypeEnum.values();
    }
}
