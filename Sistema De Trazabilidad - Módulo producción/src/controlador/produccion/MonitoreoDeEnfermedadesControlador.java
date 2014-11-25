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
import modelo.administracion.Lote;
import modelo.administracion.Modulo;
import modelo.produccion.MonitoreoDeEnfermedades;

/**
 *
 * @author fredy
 */
public class MonitoreoDeEnfermedadesControlador {
    
    private MonitoreoDeEnfermedadesDAO dao;

    public MonitoreoDeEnfermedadesControlador() {
        dao = new MonitoreoDeEnfermedadesDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }
    
    public MonitoreoDeEnfermedades nuevo(Modulo modulo, Date fecha, int botrytis, boolean antracnosis, int mycospharella, boolean mildeoPolvoso, boolean phytophtora, boolean bacteriosis){
        return new MonitoreoDeEnfermedades(modulo, fecha, botrytis, antracnosis, mycospharella, mildeoPolvoso, phytophtora, bacteriosis);
    }
    
    public MonitoreoDeEnfermedades buscar(long id) throws Exception {
        return dao.findMonitoreoDeEnfermedades(id);
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
