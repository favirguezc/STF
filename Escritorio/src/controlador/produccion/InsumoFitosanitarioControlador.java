/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.InsumoDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.produccion.Insumo;
import modelo.produccion.Unidades;

/**
 *
 * @author fredy
 */
public class InsumoFitosanitarioControlador {

    InsumoDAO dao;

    public InsumoFitosanitarioControlador() {
        dao = new InsumoDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Insumo nuevo(String nombre, String ingredienteActivo, Unidades unidades) {
        return new Insumo(nombre, ingredienteActivo, unidades);
    }

    public Insumo buscar(long id) throws Exception {
        return dao.findInsumo(id);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Insumo nueva) {
        dao.create(nueva);
    }

    public void editar(Insumo seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<Insumo> leerLista() {
        return dao.findInsumoEntities();
    }

    public Insumo buscar(String nombre) throws Exception {
        return dao.findInsumo(nombre);
    }

    public boolean validar(Insumo pf) {
        if (pf.getNombre() == null || pf.getNombre().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        try {
            Insumo l = dao.findInsumo(pf.getNombre());
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