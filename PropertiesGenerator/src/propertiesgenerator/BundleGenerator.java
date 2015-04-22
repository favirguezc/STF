/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertiesgenerator;

/**
 *
 * @author fredy
 */
public class BundleGenerator {

    public static void main(String[] args) {
        String[] cs = {"Variety"};
        for (String c : cs) {
            System.out.println("<resource-bundle>\n"
                    + "            <base-name>/Bundle" + c + "</base-name>\n"
                    + "            <var>bundle" + c + "</var>\n"
                    + "        </resource-bundle>");
        }
    }
}
