/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.MonitoreoDeEnfermedadesDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import modelo.produccion.administracion.Lote;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.monitoreo.Monitoreo;
import modelo.produccion.monitoreo.MonitoreoDeVariables;
import modelo.produccion.monitoreo.Riesgo;
import modelo.produccion.monitoreo.Variable;

/**
 *
 * @author fredy
 */
public class MonitoreoDeEnfermedadesControlador {
    
    private MonitoreoDeEnfermedadesDAO dao;

    public MonitoreoDeEnfermedadesControlador() {
        dao = new MonitoreoDeEnfermedadesDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }
    
    public MonitoreoDeVariables nuevo(Monitoreo monitoreo, Modulo modulo, Variable variable, int conteo, String relacion, Riesgo riesgo){
        return new MonitoreoDeVariables(monitoreo, modulo, variable, conteo, relacion, riesgo);
    }
    
    public MonitoreoDeVariables buscar(long id) throws Exception {
        return dao.findMonitoreoDe(id);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(MonitoreoDeEnfermedades nueva) {
        dao.create(nueva);
    }

    public void editar(MonitoreoDeEnfermedades seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<MonitoreoDeEnfermedades> leerLista(Lote lote, Date fecha) {
        return dao.findMonitoreoDeEnfermedadesEntities(lote, fecha);
    }
    
    public boolean validar(MonitoreoDeEnfermedades me){
        return true;
    }
}
