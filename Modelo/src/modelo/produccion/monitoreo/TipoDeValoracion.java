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
public enum TipoDeValoracion {

    /**
     *
     */
    CONTEO,

    /**
     *
     */
    RIESGO,

    /**
     *
     */
    RELACION;

    @Override
    public String toString() {
        switch (this) {
            case CONTEO:
                return "Conteo";
            case RIESGO:
                return "Riesgo";
            default:
                return "Relación";
        }
    }

}
