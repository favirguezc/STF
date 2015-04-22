/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertiesgenerator;

import model.administration.PageEnum;
import model.administration.RHEnum;
import model.administration.RoleEnum;
import model.administration.SexEnum;
import model.applications.ApplicationMeasurementUnitEnum;
import model.applications.ApplicationMethodEnum;
import model.applications.ApplicationTypeEnum;
import model.crop.ClassificationTypeEnum;
import model.monitoring.RiskEnum;
import model.monitoring.ValuationTypeEnum;
import model.util.Action;

/**
 *
 * @author fredy
 */
public class FacesConfigGenerator {

    public static void main(String[] args) {
        Class[] cs = {
            Action.class,
            ApplicationMeasurementUnitEnum.class,
            ApplicationMethodEnum.class,
            ApplicationTypeEnum.class,
            Boolean.class,
            ClassificationTypeEnum.class,
            PageEnum.class,
            RHEnum.class,
            RiskEnum.class,
            RoleEnum.class,
            SexEnum.class,
            ValuationTypeEnum.class};
        for (Class c : cs) {
            System.out.println("<managed-bean>\n"
                    + "        <managed-bean-name>" + c.getSimpleName() + "Converter</managed-bean-name>\n"
                    + "        <managed-bean-class>controller.converters." + c.getSimpleName() + "Converter</managed-bean-class>        \n"
                    + "        <managed-bean-scope>application</managed-bean-scope>\n"
                    + "    </managed-bean>");
        }
    }
}
