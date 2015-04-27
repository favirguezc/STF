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
public enum CostItemEnum {
    
    MACHINETIME,
    HOURS,
    DAILYWAGE,
    SEEDLINGS,
    LITRES,
    ROLLOF100METRES,
    KILOS,
    METRES,
    UNITS,
    GALLONS,
    UNIFORMWORKER,
    MONTHLY,
    TWOMONTHLYVISITS,
    NOTHING;

    @Override
    public String toString() {
        switch(this){
            case MACHINETIME:
                return "Horas Maquina";
            case HOURS:
                return "Horas";
            case DAILYWAGE:
                return "Jornales";
            case SEEDLINGS:
                return "Plantulas";
            case LITRES:
                return "Litros";
            case ROLLOF100METRES:
                return "Rollos de 1000 mt";
            case KILOS:
                return "Kilos";
            case METRES:
                return "Metros";
            case UNITS:
                return "Unidades";
            case GALLONS:
                return "Galones";
            case UNIFORMWORKER:
                return "Uniforme/Operario";
            case MONTHLY:
                return "Mensual";
            case TWOMONTHLYVISITS:
                return "2 Visitas Mensuales";
            case NOTHING:
                return "Ninguno";
            default:
                return "";
        }
    }
    
}
