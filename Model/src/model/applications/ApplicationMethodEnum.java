/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.applications;

/**
 *
 * @author fredy
 */
public enum ApplicationMethodEnum {

    INJECTED,
    IRRIGATION,
    FOLIAR;

    @Override
    public String toString() {
        switch (this) {
            case FOLIAR:
                return "Foliar";
            case INJECTED:
                return "Inyectado";
            case IRRIGATION:
                return "Riego";
            default:
                return "";
        }
    }

}
