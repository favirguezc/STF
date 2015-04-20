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
public enum ApplicationTypeEnum {

    /**
     *
     *//**
     *
     */
    PHYTOSANITARY,

    /**
     *
     */
    FERTILIZATION;

    @Override
    public String toString() {
        switch(this){
            case FERTILIZATION:
                return "Fertilización";
            case PHYTOSANITARY:
                return "Fitosanitaria";
            default:
                return "";
        }
    }
}
