/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.finanzas.costo;

/**
 *
 * @author John Fredy
 */
public enum TipoCosto {
    HORASMAQUINA,
    MANODEOBRA,
    INSUMOS,
    EQUIPOS,
    SERVICIOS;
    
    @Override    
    public String toString() {
        switch(this){
            case HORASMAQUINA:
                return "Horas Maquina";
            case MANODEOBRA:
                return "Mano de Obra";
            case INSUMOS:
                return "Insumos";
            case EQUIPOS:
                return "Equipos e Implementos";
            case SERVICIOS:
                return "Servicios";
            default:
                return "";
        }
    }
}
