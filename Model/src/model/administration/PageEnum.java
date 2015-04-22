/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.administration;

/**
 *
 * @author fredy
 */
public enum PageEnum {
//Add new pages alphabetically

    APPLICATION,
    CASH,
    CASHCONCEPT,
    CHEMICAL,
    CHEMICALPURCHASE,
    CLASSIFICATION,
    CONTRACT,
    COST,
    DEPARTMENT,
    FARM,
    INDEX,
    JOB,
    RAINFALL,
    LOT,
    MODULECLASS,
    MONITORING,
    PARAMETERMONITORING,
    MUNICIPALITY,
    PAYROLL,
    PERMISSION,
    PERSON,
    SALE,
    CROP,
    TEMPERATURE,
    THERMOMETER,
    WORK,
    INSECTTRAP,
    MONITORABLEPARAMETER,
    Venta;

    @Override
    public String toString() {
        switch (this) {
            case APPLICATION:
                return "Aplicación";
            case CHEMICAL:
                return "Insumo";
            case CLASSIFICATION:
                return "Clasificación";
            case CROP:
                return "Recolección";
            case Caja:
                return "Caja";
            case Compra:
                return "Compra";
            case ConceptoCaja:
                return "Concepto Caja";
            case CONTRACT:
                return "Contrato";
            case Costo:
                return "Costo";
            case DEPARTMENT:
                return "Departamento";
            case FARM:
                return "Finca";
            case INDEX:
                return "Index";
            case INSECTTRAP:
                return "Trampa De Insectos";
            case JOB:
                return "Labor";
            case LOT:
                return "Lote";
            case MODULECLASS:
                return "Módulo";
            case MONITORABLEPARAMETER:
                return "Variable";
            case MONITORING:
                return "Monitoreo";
            case MUNICIPALITY:
                return "Municipio";
            case Nomina:
                return "Nómina";
            case PARAMETERMONITORING:
                return "Monitoreo de Variables";
            case PERMISSION:
                return "Permiso";
            case PERSON:
                return "Persona";
            case RAINFALL:
                return "Lluvia";
            case TEMPERATURE:
                return "Temperatura";
            case THERMOMETER:
                return "Termómetro";
            case Venta:
                return "Venta";
            case WORK:
                return "Trabajo";
            default:
                return "";
        }
    }

    MONITORABLEPARAMETER;
}
