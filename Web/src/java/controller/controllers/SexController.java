/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import javax.faces.bean.ManagedBean;
import model.administration.SexEnum;

/**
 *
 * @author fredy
 */
@ManagedBean(name="sexController")
public class SexController {
    
    public SexEnum[] getSexValues(){
        return SexEnum.values();
    }
}