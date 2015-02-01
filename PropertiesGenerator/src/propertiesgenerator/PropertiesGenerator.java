/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertiesgenerator;

import modelo.produccion.variablesClimaticas.*;
import modelo.produccion.utilidades.*;
import modelo.produccion.administracion.*;
import modelo.produccion.labores.*;
import modelo.produccion.recoleccion.*;
import modelo.produccion.monitoreo.*;
import modelo.produccion.aplicaciones.*;
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

//        generarPropiedades(Lote.class);
//        generarPropiedades(Modulo.class);
//        generarPropiedades(Persona.class);
//        generarPropiedades(Rol.class);
//        generarPropiedades(RolPersona.class);
//        generarPropiedades(Aplicacion.class);
        generarPropiedades(Insumo.class);
//        generarPropiedades(Labor.class);
//        generarPropiedades(Trabajo.class);
//        generarPropiedades(Monitoreo.class);
//        generarPropiedades(MonitoreoDeVariables.class);
//        generarPropiedades(TrampaDeInsectos.class);
//        generarPropiedades(Variable.class);
//        generarPropiedades(Recoleccion.class);
//        generarPropiedades(Nota.class);
//        generarPropiedades(HumedadDelSuelo.class);
//        generarPropiedades(Lluvia.class);
//        generarPropiedades(Temperatura.class);
//        generarPropiedades(Termometro.class);
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
