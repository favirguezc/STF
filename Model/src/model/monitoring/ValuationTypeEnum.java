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
public enum ValuationTypeEnum {

    COUNT,
    RISK,
    RELATION,
    PRESENCE;

    @Override
    public String toString() {
        switch (this) {
            case COUNT:
                return "Conteo";
            case RISK:
                return "Riesgo";
            case RELATION:
                return "Relación";
            case PRESENCE:
                return "Presencia";
            default:
                return "";
        }
    }

}
