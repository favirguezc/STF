/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import javax.faces.bean.ManagedBean;
import model.administration.BloodGroupEnum;
import model.administration.RHEnum;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "bloodController")
public class BloodController {

    public RHEnum[] getRhFactorValues() {
        return RHEnum.values();
    }

    public BloodGroupEnum[] getBloodGroupValues() {
        return BloodGroupEnum.values();
    }

}
