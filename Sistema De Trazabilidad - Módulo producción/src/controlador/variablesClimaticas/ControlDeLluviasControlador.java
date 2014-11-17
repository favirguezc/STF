/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.variablesClimaticas;

import dao.variablesClimaticas.ControlDeLluviasDAO;
import dao.util.EntityManagerFactorySingleton;
import dao.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.variablesClimaticas.ControlDeLluvias;

/**
 *
 * @author fredy
 */
public class ControlDeLluviasControlador {

    private ControlDeLluviasDAO dao;

    public ControlDeLluviasControlador() {
        dao = new ControlDeLluviasDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public ControlDeLluvias nuevo(Date date, float mm) {
        return new ControlDeLluvias(date, mm);
    }

    public boolean validar(ControlDeLluvias t, Boolean nueva) {
        if (nueva) {
            try {
                if (dao.findControlDeLluvias(t.getFecha()) != null) {
                    JOptionPane.showMessageDialog(null, "Ya existe un registro para la fecha.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } catch (Exception ex) {                
            }
        }
        return true;
    }

    public ControlDeLluvias buscar(Date fecha) throws Exception{
        return dao.findControlDeLluvias(fecha);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(ControlDeLluvias nueva) {
        dao.create(nueva);
    }

    public void editar(ControlDeLluvias seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<ControlDeLluvias> buscarLista(Date esteMes, Date siguienteMes) {
        return dao.findControlDeLluviasEntities(esteMes, siguienteMes);
    }
}
