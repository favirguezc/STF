/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.aplicaciones;

/**
 *
 * @author fredy
 */
public enum TipoDeAplicacion {

    /**
     *
     */
    FITOSANITARIA,

    /**
     *
     */
    FERTILIZACION;

    @Override
    public String toString() {
        switch(this){
            case FERTILIZACION:
                return "Fertilización";
            case FITOSANITARIA:
                return "Fitosanitaria";
            default:
                return "";
        }
    }
}
