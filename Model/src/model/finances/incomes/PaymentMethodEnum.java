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
public enum PaymentMethodEnum {
    CASH,
    CONSIGNMENT,
    CREDIT,
    DEBIT;

    @Override
    public String toString() {
        switch(this){
            case CASH:
                return "Efectivo";
            case CONSIGNMENT:
                return "Consignación";
            case CREDIT:
                return "Crédito";
            case DEBIT:
                return "Débito";
            default:
                return "";
        }
    }
    
}
