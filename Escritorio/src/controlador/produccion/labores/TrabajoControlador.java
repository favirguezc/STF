/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.labores;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.TrabajoDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import modelo.produccion.administracion.Lote;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.administracion.Persona;
import modelo.produccion.labores.Labor;
import modelo.produccion.labores.Trabajo;

/**
 *
 * @author fredy
 */
public class TrabajoControlador {

    private TrabajoDAO dao;

    public TrabajoControlador() {
        dao = new TrabajoDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Trabajo nuevo(Date fecha, Modulo modulo, Labor labor, Persona operario, float jornales, String observaciones, Persona asistente) {
        return new Trabajo(fecha, modulo, labor, operario, jornales, observaciones, asistente);
    }

    public Trabajo buscar(long id) throws Exception {
        return dao.findTrabajo(id);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Trabajo nueva) {
        dao.create(nueva);
    }

    public void editar(Trabajo seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<Trabajo> leerLista(Lote lote, Date inicio, Date fin) {
        return dao.findTrabajoEntities(lote, inicio, fin);
    }

    public boolean validar(Trabajo t) {

        return true;
    }
}
