/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.monitoreo;

/**
 *
 * @author fredy
 */
public enum Riesgo {

    /**
     *
     */
    NO,

    /**
     *
     */
    BAJO,

    /**
     *
     */
    MEDIO,

    /**
     *
     */
    ALTO;

    @Override
    public String toString() {
        switch (this) {
            case ALTO:
                return "Alto";
            case BAJO:
                return "Bajo";
            case MEDIO:
                return "Medio";
            default:
                return "No";
        }
    }

}
