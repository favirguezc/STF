package controlador;

import modelo.produccion.administracion.Rol;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "rolController")
public class RolController {

    public RolController() {
    }
    
    public Rol[] getRolValues(){
        return Rol.values();
    }
}
