/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import javax.faces.bean.ManagedBean;
import model.administration.PageEnum;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "pageController")
public class PageController {
    
    public PageEnum[] getPageValues(){
        return PageEnum.values();
    }
    
}
