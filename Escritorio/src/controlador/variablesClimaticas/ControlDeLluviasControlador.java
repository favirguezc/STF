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
import modelo.produccion.variablesClimaticas.Lluvia;

/**
 *
 * @author fredy
 */
public class ControlDeLluviasControlador {

    private ControlDeLluviasDAO dao;

    public ControlDeLluviasControlador() {
        dao = new ControlDeLluviasDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Lluvia nuevo(Date date, float mm) {
        return new Lluvia(date, mm);
    }

    public boolean validar(Lluvia t, Boolean nueva) {
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

    public Lluvia buscar(Date fecha) throws Exception{
        return dao.findControlDeLluvias(fecha);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Lluvia nueva) {
        dao.create(nueva);
    }

    public void editar(Lluvia seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<Lluvia> buscarLista(Date esteMes, Date siguienteMes) {
        return dao.findControlDeLluviasEntities(esteMes, siguienteMes);
    }
}
