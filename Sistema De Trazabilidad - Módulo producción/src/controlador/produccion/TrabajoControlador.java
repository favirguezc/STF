/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.TrabajoDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import modelo.administracion.Lote;
import modelo.administracion.Modulo;
import modelo.administracion.Persona;
import modelo.produccion.LaborCultural;
import modelo.produccion.Trabajo;

/**
 *
 * @author fredy
 */
public class TrabajoControlador {

    private TrabajoDAO dao;

    public TrabajoControlador() {
        dao = new TrabajoDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Trabajo nuevo(Date fecha, Modulo modulo, LaborCultural labor, Persona operario, float jornales, String observaciones, Persona asistente, Persona productor) {
        return new Trabajo(fecha, modulo, labor, operario, jornales, observaciones, asistente, productor);
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
