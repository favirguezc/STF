/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.MonitoreoDePlagasDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.administracion.Lote;
import modelo.produccion.MonitoreoDePlagas;

/**
 *
 * @author fredy
 */
public class MonitoreoDePlagasControlador {

    private MonitoreoDePlagasDAO dao;

    public MonitoreoDePlagasControlador() {
        dao = new MonitoreoDePlagasDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public MonitoreoDePlagas nuevo(Lote lote, int modulo, int aranita, int thrips, boolean cyclamen, boolean chisas, boolean babosas, String otro, boolean otrov, boolean flor, boolean fruto, int coronas, Date fecha) {
        return new MonitoreoDePlagas(lote, modulo, aranita, thrips, cyclamen, chisas, babosas, otro, otrov, flor, fruto, coronas, fecha);
    }

    public MonitoreoDePlagas buscar(long id) throws Exception {
        return dao.findMonitoreoDePlagas(id);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(MonitoreoDePlagas nueva) {
        dao.create(nueva);
    }

    public void editar(MonitoreoDePlagas seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<MonitoreoDePlagas> leerLista(Lote lote, Date fecha) {
        return dao.findMonitoreoDePlagasEntities(lote, fecha);
    }

    public boolean validar(MonitoreoDePlagas monitoreo) {
        if(monitoreo.getAranita()>2){
            JOptionPane.showMessageDialog(null, "El campo arañita no puede ser mayor a 2.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(monitoreo.getCoronas()<0){
            JOptionPane.showMessageDialog(null, "El campo número de coronas no puede ser negativo.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(monitoreo.getModulo()<0){
            JOptionPane.showMessageDialog(null, "El campo módulo no puede ser negativo.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(monitoreo.getThrips()>2){
            JOptionPane.showMessageDialog(null, "El campo thrips no puede ser mayor a 2.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
}
