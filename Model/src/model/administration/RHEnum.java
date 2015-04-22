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
public enum RHEnum {

    /**
     *
     */
    POSITIVE,
    /**
     *
     */
    NEGATIVE;

    @Override
    public String toString() {
        switch (this) {
            case NEGATIVE:
                return "Negativo";
            case POSITIVE:
                return "Positivo";
            default:
                return "";
        }
    }

}
