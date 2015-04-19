/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import javax.faces.bean.ManagedBean;
import model.util.Action;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "accionController")
public class AccionController {

    public Action[] getAccionValues() {
        return Action.values();
    }

}
