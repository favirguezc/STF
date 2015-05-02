/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.finances.incomes.BankEnum;

/**
 *
 * @author John Fredy
 */
@FacesConverter(forClass = BankEnum.class)
public class BankEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Banco de Bogotá")) {
            return BankEnum.BANCOBOGOTA;
        }
        if (value.equals("Banco Popular S.A.")) {
            return BankEnum.BANCOPOPULAR;
        }
        if (value.equals("Banco CorpBanca S.A.")) {
            return BankEnum.BANCOCORPBANCA;
        }
        if (value.equals("Bancolombia")) {
            return BankEnum.BANCOLOMBIA;
        }
        if (value.equals("Citibank Colombia")) {
            return BankEnum.CITIBANK;
        }
        if (value.equals("Banco GNB Sudameris")) {
            return BankEnum.BANCOGNBSUDAMERIS;
        }
        if (value.equals("BBVA Colombia")) {
            return BankEnum.BBVACOLOMBIA;
        }
        if (value.equals("Banco de Occidente S.A.")) {
            return BankEnum.BANCOOCCIDENTE;
        }
        if (value.equals("Banco Caja Social S.A.")) {
            return BankEnum.BCSC;
        }
        if (value.equals("Banco Davivienda")) {
            return BankEnum.DAVIVIENDA;
        }
        if (value.equals("Banco Colpatria")) {
            return BankEnum.COLPATRIA;
        }
        if (value.equals("Banco Agrario")) {
            return BankEnum.BANAGRARIO;
        }
        if (value.equals("Banco AV Villas")) {
            return BankEnum.AVVILLAS;
        }
        if (value.equals("Banco Procredit")) {
            return BankEnum.PROCREDIT;
        }
        if (value.equals("Bancamía S.A.")) {
            return BankEnum.BANCAMIA;
        }
        if (value.equals("Banco WWB S.A.")) {
            return BankEnum.WWB;
        }
        if (value.equals("Bancoomeva")) {
            return BankEnum.BANCOOMEVA;
        }
        if (value.equals("Banco Finandina")) {
            return BankEnum.FINANDINA;
        }
        if (value.equals("Banco Falabella")) {
            return BankEnum.BANCOFALABELLA;
        }
        if (value.equals("Banco Pichincha")) {
            return BankEnum.BANCOPICHINCHA;
        }
        if (value.equals("Bacno Coopcentral")) {
            return BankEnum.COOPCENTRAL;
        }
        if (value.equals("Banco Santander")) {
            return BankEnum.BANCOSANTANDER;
        }
        if (value.equals("Ninguno")) {
            return BankEnum.NOONE;
        }
        if (value.equals("Otro")) {
            return BankEnum.OTHER;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BankEnum) {
            return value + "";
        } else {
            return null;
        }
    }
}
