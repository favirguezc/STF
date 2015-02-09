/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.monitoreo;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.MonitoreoDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import modelo.produccion.monitoreo.Monitoreo;

/**
 *
 * @author fredy
 */
public class MonitoreoControlador {

    private MonitoreoDAO dao;
    
    public MonitoreoControlador() {
        dao = new MonitoreoDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Monitoreo nuevo(long numeroDeMonitoreo, Date fecha) {
        return new Monitoreo(numeroDeMonitoreo, fecha);
    }

    public Monitoreo buscar(long id) {
        return dao.findMonitoreo(id);
    }

    public void eliminar(long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Monitoreo t) {
        dao.create(t);
    }

    public void editar(Monitoreo t) throws Exception {
        dao.edit(t);
    }

    public List<Monitoreo> leerLista() {
        return dao.findMonitoreoEntities();
    }
    
}
