/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.recoleccion;

/**
 *
 * @author fredy
 */
public enum TipoDeFresa {

    EXTRA,
    PRIMERA,
    SEGUNDA,
    TERCERA,
    CUARTA,
    QUINTA,
    DEFORME,
    DANADA;

    @Override
    public String toString() {
        switch (this) {
            case CUARTA:
                return "Cuarta";
            case DANADA:
                return "Dañada";
            case DEFORME:
                return "Deforme";
            case EXTRA:
                return "Extra";
            case PRIMERA:
                return "Primera";
            case QUINTA:
                return "Quinta";
            case SEGUNDA:
                return "Segunda";
            case TERCERA:
                return "Tercera";
            default:
                return "";
        }
    }

}
