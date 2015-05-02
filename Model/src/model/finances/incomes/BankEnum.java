/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.finances.incomes;

/**
 *
 * @author John Fredy
 */
public enum BankEnum {
    NOONE,
    BANCOBOGOTA,
    BANCOPOPULAR,
    BANCOCORPBANCA,
    BANCOLOMBIA,
    CITIBANK,
    BANCOGNBSUDAMERIS,
    BBVACOLOMBIA,
    BANCOOCCIDENTE,
    BCSC,
    DAVIVIENDA,
    COLPATRIA,
    BANAGRARIO,
    AVVILLAS,
    PROCREDIT,
    BANCAMIA,
    WWB,
    BANCOOMEVA,
    FINANDINA,
    BANCOFALABELLA,
    BANCOPICHINCHA,
    COOPCENTRAL,
    BANCOSANTANDER,
    OTHER;

    @Override
    public String toString() {
        switch(this){
            case BANCOBOGOTA:
                return "Banco de Bogotá";
            case BANCOPOPULAR:
                return "Banco Popular S.A.";
            case BANCOCORPBANCA:
                return "Banco CorpBanca S.A.";
            case BANCOLOMBIA:
                return "Bancolombia";
            case CITIBANK:
                return "Citibank Colombia";
            case BANCOGNBSUDAMERIS:
                return "Banco GNB Sudameris";
            case BBVACOLOMBIA:
                return "BBVA Colombia";
            case BANCOOCCIDENTE:
                return "Banco de Occidente S.A.";
            case BCSC:
                return "Banco Caja Social S.A.";
            case DAVIVIENDA:
                return "Banco Davivienda";
            case COLPATRIA:
                return "Banco Colpatria";
            case BANAGRARIO:
                return "Banco Agrario";
            case AVVILLAS:
                return "Banco AV Villas";
            case PROCREDIT:
                return "Banco Procredit";
            case BANCAMIA:
                return "Bancamía S.A.";
            case WWB:
                return "Banco WWB S.A.";
            case BANCOOMEVA:
                return "Bancoomeva";
            case FINANDINA:
                return "Banco Finandina";
            case BANCOFALABELLA:
                return "Banco Falabella";
            case BANCOPICHINCHA:
                return "Banco Pichincha";
            case COOPCENTRAL:
                return "Bacno Coopcentral";
            case BANCOSANTANDER:
                return "Banco Santander";
            case OTHER:
                return "Otro";
            case NOONE:
                return "Ninguno";
            default:
                return "";
        }
    }
    
}
