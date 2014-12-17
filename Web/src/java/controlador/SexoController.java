/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.faces.bean.ManagedBean;
import modelo.administracion.Sexo;

/**
 *
 * @author fredy
 */
@ManagedBean(name="sexoController")
public class SexoController {
    
    public Sexo[] getSexoValues(){
        return Sexo.values();
    }
}
