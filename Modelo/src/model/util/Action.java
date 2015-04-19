/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

/**
 *
 * @author fredy
 */
public enum Action {

    READ,
    WRITE,
    DELETE;

    @Override
    public String toString() {
        switch (this) {
            case DELETE:
                return "Eliminar";
            case READ:
                return "Leer";
            case WRITE:
                return "Escribir";
            default:
                return "";
        }
    }

}
