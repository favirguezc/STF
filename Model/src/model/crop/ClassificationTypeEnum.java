/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.crop;

/**
 *
 * @author fredy
 */
public enum ClassificationTypeEnum {

    EXTRA,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    DUMMIE,
    DAMAGED;

    @Override
    public String toString() {
        switch (this) {
            case FOUR:
                return "Cuarta";
            case DAMAGED:
                return "Dañada";
            case EXTRA:
                return "Extra";
            case DUMMIE:
                return "Muñeco";
            case ONE:
                return "Primera";
            case FIVE:
                return "Quinta";
            case TWO:
                return "Segunda";
            case THREE:
                return "Tercera";
            default:
                return "";
        }
    }

}
