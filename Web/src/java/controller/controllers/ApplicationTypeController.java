/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import javax.faces.bean.ManagedBean;
import model.applications.ApplicationTypeEnum;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "applicationTypeController")
public class ApplicationTypeController {
    
    public ApplicationTypeEnum[] getApplicationTypeValues(){
        return ApplicationTypeEnum.values();
    }
}