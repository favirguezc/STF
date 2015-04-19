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
public enum ItemCosto {
    
    hrMaquina,
    horas,
    jornales,
    plantulas,
    litros,
    rollosde1000mt,
    kilos,
    metros,
    unidades,
    galones,
    uniformeoperario,
    mensual,
    dosvisitasmensuales,
    ninguno;

    @Override
    public String toString() {
        switch(this){
            case hrMaquina:
                return "Horas Maquina";
            case horas:
                return "Horas";
            case jornales:
                return "Jornales";
            case plantulas:
                return "Plantulas";
            case litros:
                return "Litros";
            case rollosde1000mt:
                return "Rollos de 1000 mt";
            case kilos:
                return "Kilos";
            case metros:
                return "Metros";
            case unidades:
                return "Unidades";
            case galones:
                return "Galones";
            case uniformeoperario:
                return "Uniforme/Operario";
            case mensual:
                return "Mensual";
            case dosvisitasmensuales:
                return "2 Visitas Mensuales";
            case ninguno:
                return "Ninguno";
            default:
                return "";
        }
    }
    
}
