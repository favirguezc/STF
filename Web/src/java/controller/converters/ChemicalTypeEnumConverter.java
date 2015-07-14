package controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.applications.ChemicalTypeEnum;

/**
 *
 * @author fredy
 */
@FacesConverter(forClass = ChemicalTypeEnum.class)
public class ChemicalTypeEnumConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.equals("Insecticida")) {
            return ChemicalTypeEnum.INSECTICIDE;
        }
        if (value.equals("Acaricida")) {
            return ChemicalTypeEnum.ACARICIDE;
        }
        if (value.equals("Fungicida")) {
            return ChemicalTypeEnum.FUNGICIDE;
        }
        if (value.equals("Herbicida")) {
            return ChemicalTypeEnum.HERBICIDE;
        }
        if (value.equals("Preventivo")) {
            return ChemicalTypeEnum.PREVENTIVE;
        }
        if (value.equals("Corrector de Suelo")) {
            return ChemicalTypeEnum.SOIL_AMENDMENT;
        }
        if (value.equals("Desinfectante")) {
            return ChemicalTypeEnum.DISINFECTANT;
        }
        if (value.equals("Fertilizante")) {
            return ChemicalTypeEnum.FERTILIZER;
        }
        if (value.equals("Coadyuvante")) {
            return ChemicalTypeEnum.ADJUVANT;
        }
        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof ChemicalTypeEnum) {
            return value + "";
        } else {
            return null;
        }
    }
    
}
