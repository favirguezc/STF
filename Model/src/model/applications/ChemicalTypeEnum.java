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
public enum ChemicalTypeEnum {

    INSECTICIDE,
    ACARICIDE,
    FUNGICIDE,
    HERBICIDE,
    PREVENTIVE,
    SOIL_AMENDMENT,
    DISINFECTANT,
    FERTILIZER,
    ADJUVANT;

    @Override
    public String toString() {
        switch (this) {
            case INSECTICIDE:
                return "Insecticida";
            case ACARICIDE:
                return "Acaricida";
            case FUNGICIDE:
                return "Fungicida";
            case HERBICIDE:
                return "Herbicida";
            case PREVENTIVE:
                return "Preventivo";
            case SOIL_AMENDMENT:
                return "Corrector de Suelo";
            case DISINFECTANT:
                return "Desinfectante";
            case FERTILIZER:
                return "Fertilizante";
            case ADJUVANT:
                return "Coadyuvante";
            default:
                return "";
        }
    }
}
