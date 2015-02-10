/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.variablesClimaticas;

import datos.util.EntityManagerFactorySingleton;
import datos.exceptions.NonexistentEntityException;
import datos.produccion.variablesClimaticas.LluviaDAO;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.produccion.variablesClimaticas.Lluvia;

/**
 *
 * @author fredy
 */
public class LluviaControlador {

    private LluviaDAO dao;

    public LluviaControlador() {
        dao = new LluviaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Lluvia nuevo(Date date, float mm) {
        return new Lluvia(date, mm);
    }

    public boolean validar(Lluvia t, Boolean nueva) {
        if (nueva) {
            try {
                if (dao.findLluviaEntities(t.getFecha()) != null && dao.findLluviaEntities(t.getFecha()).size() > 0) {
                    JOptionPane.showMessageDialog(null, "Ya existe un registro para la fecha.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } catch (Exception ex) {
            }
        }
        return true;
    }

    public Lluvia buscar(Date fecha) {
        return dao.findLluviaEntities(fecha).get(0);
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
        return dao.findLluviaEntities(esteMes, siguienteMes);
    }
}
