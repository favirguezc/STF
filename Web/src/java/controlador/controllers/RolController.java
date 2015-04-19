package controlador.controllers;

import model.administration.RoleEnum;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "rolController")
public class RolController {

    public RolController() {
    }
    
    public RoleEnum[] getRolValues(){
        return RoleEnum.values();
    }
}
