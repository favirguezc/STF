/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.applications;

/**
 *
 * @author fredy
 */
public enum ApplicationMeasurementUnitEnum {

    /**
     *
     */
    CC,

    /**
     *
     */
    GR;

    @Override
    public String toString() {
        switch(this){
            case CC:
                return "Cc";
            default:
                return "Gr";
        }
    }
    
}
