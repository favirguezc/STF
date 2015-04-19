/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.administration;

/**
 *
 * @author fredy
 */
public enum SexEnum {

    /**
     *
     *//**
     *
     */
    MASCULINE,
    /**
     *
     */
    FEMENINE;

    @Override
    public String toString() {
        switch (this) {
            case MASCULINE:
                return "Masculino";
            case FEMENINE:
                return "Femenino";
            default:
                return "";
        }
    }

}
