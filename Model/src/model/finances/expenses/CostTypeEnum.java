/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.finances.expenses;

/**
 *
 * @author John Fredy
 */
public enum CostTypeEnum {
    MACHINETIME,
    MANPOWER,
    CHEMICALS,
    TOOLS,
    SERVICES;
    
    @Override    
    public String toString() {
        switch(this){
            case MACHINETIME:
                return "Horas Maquina";
            case MANPOWER:
                return "Mano de Obra";
            case CHEMICALS:
                return "Insumos";
            case TOOLS:
                return "Equipos e Implementos";
            case SERVICES:
                return "Servicios";
            default:
                return "";
        }
    }
}
