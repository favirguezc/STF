/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.finanzas;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import datos.finanzas.ConceptoCajaDAO;
import datos.exceptions.NonexistentEntityException;
import datos.util.EntityManagerFactorySingleton;
import modelo.finanzas.caja.Caja;
import modelo.finanzas.caja.ConceptoCaja;

/**
 *
 * @author JohnFredy
 */
public class ConceptoCajaControlador {
    
    private ConceptoCajaDAO dao;

    public ConceptoCajaControlador() {
        dao = new ConceptoCajaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public ConceptoCaja nuevo(Date fecha, String descripcion, boolean entrada, float valor, Caja caja) {
        return new ConceptoCaja(fecha, descripcion, entrada, valor, caja);
    }

    public ConceptoCaja buscar(long id) throws Exception {
        return dao.findConceptoCaja(id);
    }

    public ConceptoCaja buscar(String descripcion, Caja caja) throws Exception {
        return dao.findConceptoCaja(descripcion, caja);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(ConceptoCaja nueva) {
        dao.create(nueva);
    }

    public void editar(ConceptoCaja seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<ConceptoCaja> leerLista() {
        return dao.findConceptoCajaEntities();
    }

    public List<ConceptoCaja> leerLista(Caja caja) {
        return dao.findConceptoCajaEntities(caja);
    }

    public boolean validar(ConceptoCaja nuevo) {
        if (nuevo.getDescripcion() == null || nuevo.getDescripcion().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        try {
            if (dao.findConceptoCaja(nuevo.getDescripcion(), nuevo.getCaja()) != null
                    && dao.findConceptoCaja(nuevo.getDescripcion(), nuevo.getCaja()).getId() != nuevo.getId()) {
                JOptionPane.showMessageDialog(null, "El registro ya está en la base de datos.", "Registro duplicado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
        }
        return true;
    }
}
