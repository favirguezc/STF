/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertiesgenerator;

import model.finances.cost.CostItemEnum;
import model.finances.cost.CostTypeEnum;

/**
 *
 * @author fredy
 */
public class FacesConfigGenerator {

    public static void main(String[] args) {
        Class[] cs = {
            CostItemEnum.class,
            CostTypeEnum.class};
        for (Class c : cs) {
            System.out.println("<managed-bean>\n"
                    + "        <managed-bean-name>" + c.getSimpleName() + "Converter</managed-bean-name>\n"
                    + "        <managed-bean-class>controller.converters." + c.getSimpleName() + "Converter</managed-bean-class>        \n"
                    + "        <managed-bean-scope>application</managed-bean-scope>\n"
                    + "    </managed-bean>");
        }
    }
}
