/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.produccion.administracion.Rol;

/**
 *
 * @author fredy
 */
public class PermisosPorRol {

    private static final String[] paginas = {
        "/secure/index.xhtml",//0
        "/secure/lote/List.xhtml",//1
        "/secure/modulo/List.xhtml",//2
        "/secure/persona/List.xhtml",//3
        "/secure/rolPersona/List.xhtml",//4
        "/secure/aplicacion/List.xhtml",//5
        "/secure/insumo/List.xhtml",//6
        "/secure/trabajo/List.xhtml",//7
        "/secure/labor/List.xhtml",//8
        "/secure/monitoreo/List.xhtml",//9
        "/secure/monitoreoDeVariables/List.xhtml",//10
        "/secure/variable/List.xhtml",//11
        "/secure/trampaDeInsectos/List.xhtml",//12
        "/secure/temperatura/List.xhtml",//13
        "/secure/lluvia/List.xhtml",//14
        "/secure/humedadDelSuelo/List.xhtml",//15
        "/secure/termometro/List.xhtml",//16
        "/secure/recoleccion/List.xhtml",//17
        "/secure/venta/List.xhtml"//18
    };
    private static final int[] permisosAsistenteTecnico = {0, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    private static final int[] permisosTrabajador = {0, 5, 7, 9, 10, 12, 13, 14, 15};

    public PermisosPorRol() {

    }

    public static boolean tienePermiso(Rol rol, String pagina) {
        if (rol == Rol.ADMINISTRADOR || rol == Rol.GERENTE || rol == Rol.SECRETARIO) {
            return true;
        }
        if (rol == Rol.CLIENTE || rol == Rol.RECOLECTOR) {
            return false;
        }
        int[] permisos = {};
        if (rol == Rol.ASISTENTE_TECNICO) {
            permisos = permisosAsistenteTecnico;
        }
        if (rol == Rol.TRABAJADOR) {
            permisos = permisosTrabajador;
        }
        for (int i : permisos) {
            if (pagina.equals(paginas[i])) {
                return true;
            }
        }
        return false;
    }

}
