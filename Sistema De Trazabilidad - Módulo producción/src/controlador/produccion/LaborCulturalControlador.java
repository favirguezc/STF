/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.LaborCulturalDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.List;
import modelo.produccion.LaborCultural;

/**
 *
 * @author fredy
 */
public class LaborCulturalControlador {

    private LaborCulturalDAO dao;

    public LaborCulturalControlador() {
        dao = new LaborCulturalDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public LaborCultural nuevo(String nombre, String descripcion) {
        return new LaborCultural(nombre, descripcion);
    }

    public void guardar(LaborCultural lc) {
        dao.create(lc);
    }

    public void editar(LaborCultural lc) throws Exception {
        dao.edit(lc);
    }

    public void eliminar(long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public LaborCultural buscar(String nombre) throws Exception {
        return dao.findLaborCultural(nombre);
    }

    public List<LaborCultural> leerLista() {
        return dao.findLaborCulturalEntities();
    }

    public void comprobarRegistros() {
        String[] roles = {
            "Aplicación Agroquímicos",
            "Aplicación de herbicidas pre-siembra",
            "Arrancada de plantas",
            "Cargue y descargue de plantas",
            "Clavado de patas",
            "Control de malezas en camas y calles",
            "Desclavado de patas",
            "Fertilización",
            "Fertilización de presiembra",
            "Implementación del sistema de riego",
            "Instalación de arcos",
            "Instalación de mulch (plástico)",
            "Levantada de camas",
            "Mantenimiento de macrotúneles",
            "Marcado y perforación del plástico",
            "Poda fitosanitaria",
            "Postura de ganchos",
            "Postura plásticos macros",
            "Preparación de camas",
            "Recolección y clasificación de fruta",
            "Reproceso de fruta",
            "Resiembra de plantas",
            "Retirada de plástico macrotúnel",
            "Retiro de arcos",
            "Retiro de estolones",
            "Siembra",
            "Subida plástico",
            "Sunchar"
        };
        for (int i = 0; i < roles.length; i++) {
            try {
                if (buscar(roles[i]) == null) {
                    guardar(nuevo(roles[i], null));
                }
            } catch (Exception ex) {
                guardar(nuevo(roles[i], null));
            }
        }
    }
}
