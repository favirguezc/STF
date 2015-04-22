package propertiesgenerator;

import java.lang.reflect.Field;

/**
 *
 * @author fredy
 */
public class PropertiesGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        generarTitulos();
        generarPropiedades(model.applications.Chemical.class);
    }

    private static String g(String campo) {
        String retorno = (campo.charAt(0) + "").toUpperCase();
        for (char c : campo.substring(1).toCharArray()) {
            if (Character.isUpperCase(c)) {
                retorno += " ";
            }
            retorno += c;
        }
        return retorno;
    }

    private static void generarTitulos() {
        System.out.println("PersistenceErrorOccured=Error de persistencia.");
        System.out.println("Create=Crear");
        System.out.println("View=Ver");
        System.out.println("Edit=Editar");
        System.out.println("Delete=Borrar");
        System.out.println("Close=Cerrar");
        System.out.println("Cancel=Cancelar");
        System.out.println("Save=Guardar");
        System.out.println("SelectOneMessage=Selecciona Uno...");
        System.out.println("Home=Inicio");
        System.out.println("Maintenance=Maintenimiento");
        System.out.println("AppName=STF - Web");
        System.out.println("");
        System.out.println("");
        System.out.println("Management=Administración");
        System.out.println("Application=Aplicación");
        System.out.println("Work=Trabajo");
        System.out.println("Monitoring=Monitoreo");
        System.out.println("Weather=Clima");
        System.out.println("Finances=Finanzas");
    }

    private static void generarPropiedades(Class aClass) {
        Class c = aClass;
        String nombre = c.getSimpleName();
        System.out.println();
        System.out.println();
        System.out.println(nombre + "Created=" + g(nombre) + " creado satisfactoriamente.");
        System.out.println(nombre + "Updated=" + g(nombre) + " actualizado satisfactoriamente.");
        System.out.println(nombre + "Deleted=" + g(nombre) + " borrado satisfactoriamente.");

        System.out.println("Create" + nombre + "Title=Crear Nuevo Registro de " + g(nombre));
        System.out.println("Create" + nombre + "SaveLink=Guardar");
        System.out.println("Create" + nombre + "ShowAllLink=Mostrar Todos los Registros de " + g(nombre));
        System.out.println("Create" + nombre + "IndexLink=Índice");
        for (Field f : c.getDeclaredFields()) {

            String campo = f.getName();
            System.out.println("Create" + nombre + "Label_" + campo + "=" + g(campo) + ":");
            System.out.println("Create" + nombre + "RequiredMessage_" + campo + "=El campo " + g(campo) + " es necesario.");
            System.out.println("Create" + nombre + "Title_" + campo + "=" + g(campo));
        }

        System.out.println("Edit" + nombre + "Title=Editar " + g(nombre));
        System.out.println("Edit" + nombre + "SaveLink=Guardar");
        System.out.println("Edit" + nombre + "ViewLink=Ver");
        System.out.println("Edit" + nombre + "ShowAllLink=Mostrar Todos los Registros de " + g(nombre));
        System.out.println("Edit" + nombre + "IndexLink=Índice");
        for (Field f : c.getDeclaredFields()) {

            String campo = f.getName();
            System.out.println("Edit" + nombre + "Label_" + campo + "=" + g(campo) + ":");
            System.out.println("Edit" + nombre + "RequiredMessage_" + campo + "=El campo " + g(campo) + " es necesario.");
            System.out.println("Edit" + nombre + "Title_" + campo + "=" + g(campo) + "");
        }

        System.out.println("View" + nombre + "Title=Ver");
        System.out.println("View" + nombre + "DestroyLink=Eliminar");
        System.out.println("View" + nombre + "EditLink=Editar");
        System.out.println("View" + nombre + "CreateLink=Crear Nuevo Registro de " + g(nombre));
        System.out.println("View" + nombre + "ShowAllLink=Mostrar Todos los Registros de " + g(nombre));
        System.out.println("View" + nombre + "IndexLink=Índice");
        for (Field f : c.getDeclaredFields()) {

            String campo = f.getName();
            System.out.println("View" + nombre + "Label_" + campo + "=" + g(campo) + ":");
            System.out.println("View" + nombre + "Title_" + campo + "=" + g(campo) + "");
        }

        System.out.println("List" + nombre + "Title=Lista");
        System.out.println("List" + nombre + "Empty=(No se encontraron registros de " + g(nombre) + ")");
        System.out.println("List" + nombre + "DestroyLink=Eliminar");
        System.out.println("List" + nombre + "EditLink=Editar");
        System.out.println("List" + nombre + "ViewLink=Ver");
        System.out.println("List" + nombre + "CreateLink=Crear Nuevo Registro de " + g(nombre));
        System.out.println("List" + nombre + "IndexLink=Índice");
        for (Field f : c.getDeclaredFields()) {
            String campo = f.getName();
            System.out.println("List" + nombre + "Title_" + campo + "=" + g(campo) + "");
        }
    }

}
