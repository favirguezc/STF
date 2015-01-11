/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.administracion;

import dao.administracion.ModuloDAO;
import dao.exceptions.NonexistentEntityException;
import dao.util.EntityManagerFactorySingleton;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.produccion.administracion.Lote;
import modelo.produccion.administracion.Modulo;

/**
 *
 * @author fredy
 */
public class ModuloControlador {

    private ModuloDAO dao;

    public ModuloControlador() {
        dao = new ModuloDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Modulo nuevo(String nombre, double area, Lote lote) {
        return new Modulo(nombre, area, lote);
    }

    public Modulo buscar(long id) throws Exception {
        return dao.findModulo(id);
    }

    public Modulo buscar(String nombre, Lote lote) throws Exception {
        return dao.findModulo(nombre, lote);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Modulo nueva) {
        dao.create(nueva);
    }

    public void editar(Modulo seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<Modulo> leerLista() {
        return dao.findModuloEntities();
    }

    public List<Modulo> leerLista(Lote lote) {
        return dao.findModuloEntities(lote);
    }

    public boolean validar(Modulo nuevo) {
        if (nuevo.getNombre() == null || nuevo.getNombre().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        try {
            if (dao.findModulo(nuevo.getNombre(), nuevo.getLote()) != null
                    && dao.findModulo(nuevo.getNombre(), nuevo.getLote()).getId() != nuevo.getId()) {
                JOptionPane.showMessageDialog(null, "El registro ya está en la base de datos.", "Registro duplicado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
        }
        return true;
    }
}
