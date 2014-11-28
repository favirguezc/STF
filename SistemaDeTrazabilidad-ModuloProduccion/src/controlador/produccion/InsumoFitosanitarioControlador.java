/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.InsumoFitosanitarioDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.produccion.InsumoFitosanitario;

/**
 *
 * @author fredy
 */
public class InsumoFitosanitarioControlador {

    InsumoFitosanitarioDAO dao;

    public InsumoFitosanitarioControlador() {
        dao = new InsumoFitosanitarioDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public InsumoFitosanitario nuevo(String nombre, String ingredienteActivo, String unidades) {
        return new InsumoFitosanitario(nombre, ingredienteActivo, unidades);
    }

    public InsumoFitosanitario buscar(long id) throws Exception {
        return dao.findProductoFitosanitario(id);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(InsumoFitosanitario nueva) {
        dao.create(nueva);
    }

    public void editar(InsumoFitosanitario seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<InsumoFitosanitario> leerLista() {
        return dao.findProductoFitosanitarioEntities();
    }

    public InsumoFitosanitario buscar(String nombre) throws Exception {
        return dao.findProductoFitosanitario(nombre);
    }

    public boolean validar(InsumoFitosanitario pf) {
        if (pf.getNombre() == null || pf.getNombre().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        try {
            InsumoFitosanitario l = dao.findProductoFitosanitario(pf.getNombre());
            if (l != null && l.getId() != pf.getId()) {
                JOptionPane.showMessageDialog(null, "El registro ya está en la base de datos.", "Registro duplicado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } catch (Exception e) {
        }
        if (pf.getIngredienteActivo() == null || pf.getIngredienteActivo().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo ingrediente activo no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
}
