/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.monitoreo;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.MonitoreoDeVariablesDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.monitoreo.Monitoreo;
import modelo.produccion.monitoreo.MonitoreoDeVariables;
import modelo.produccion.monitoreo.Riesgo;
import modelo.produccion.monitoreo.Variable;

/**
 *
 * @author fredy
 */
public class MonitoreoDeVariablesControlador {
    
    private MonitoreoDeVariablesDAO dao;

    public MonitoreoDeVariablesControlador() {
        dao = new MonitoreoDeVariablesDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }
    
    public MonitoreoDeVariables nuevo(Monitoreo monitoreo, Modulo modulo, Variable variable, int conteo, String relacion, Riesgo riesgo){
        return new MonitoreoDeVariables(monitoreo, modulo, variable, conteo, relacion, riesgo);
    }
    
    public MonitoreoDeVariables buscar(long id) throws Exception {
        return dao.findMonitoreoDeVariables(id);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(MonitoreoDeVariables nueva) {
        dao.create(nueva);
    }

    public void editar(MonitoreoDeVariables seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<MonitoreoDeVariables> leerLista() {
        return dao.findMonitoreoDeVariablesEntities();
    }
    
    public List<MonitoreoDeVariables> leerLista(Modulo modulo) {
        return dao.findMonitoreoDeVariablesEntities(modulo);
    }
    
    public boolean validar(MonitoreoDeVariables mv){
        return true;
    }
}
