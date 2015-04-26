/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertiesgenerator;

import model.finances.cost.CostItemEnum;

/**
 *
 * @author fredy
 */
public class ConverterGenerator {

    public static void generarGetAsObject(Class c) {
        System.out.println("public Object getAsObject(FacesContext context, UIComponent component, String value) {");
        System.out.println("if (value == null) {\n"
                + "            return null;\n"
                + "        }");
        for (Object o : c.getEnumConstants()) {
            System.out.println("if (value.equals(\"" + o.toString() + "\")) {");
            System.out.println("return " + c.getSimpleName() + "." + ((Enum) o).name() + ";");
            System.out.println("}");
        }
        System.out.println("return null;");
        System.out.println("}");
    }

    public static void generarGetAsString(Class c) {
        System.out.println("public String getAsString(FacesContext context, UIComponent component, Object value) {\nif (value == null) {\n"
                + "            return null;\n"
                + "        }\n"
                + "        if (value instanceof " + c.getSimpleName() + ") {\n"
                + "            return value + \"\";\n"
                + "        } else {\n"
                + "            return null;\n"
                + "        }\n}");
    }

    private static void generarEncabezado(Class c) {
        System.out.println("/**\n"
                + " *\n"
                + " * @author fredy\n"
                + " */");
        System.out.println("@FacesConverter(forClass = " + c.getSimpleName() + ".class)\n"
                + "public class " + c.getSimpleName() + "Converter implements Converter {");
    }

    public static void main(String args[]) {
        Class c = CostItemEnum.class;
        System.out.println("import javax.faces.component.UIComponent;\n"
                + "import javax.faces.context.FacesContext;\n"
                + "import javax.faces.convert.Converter;\n"
                + "import javax.faces.convert.FacesConverter;");
        System.out.println("");
        generarEncabezado(c);
        System.out.println("");
        generarGetAsObject(c);
        System.out.println("");
        generarGetAsString(c);
        System.out.println("}");
    }

}
