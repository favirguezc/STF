/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import javax.faces.bean.ManagedBean;
import model.util.Action;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "actionController")
public class ActionController {

    public Action[] getActionValues() {
        return Action.values();
    }

}
