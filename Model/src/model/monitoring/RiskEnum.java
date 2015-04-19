/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.monitoring;

/**
 *
 * @author fredy
 */
public enum RiskEnum {

    NO,
    LOW,
    MEDIUM,
    HIGH;

    @Override
    public String toString() {
        switch (this) {
            case HIGH:
                return "Alto";
            case LOW:
                return "Bajo";
            case MEDIUM:
                return "Medio";
            case NO:
                return "No";
            default:
                return "";
        }
    }

}
