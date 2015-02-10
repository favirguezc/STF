/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.administracion;

import datos.exceptions.NonexistentEntityException;
import datos.produccion.administracion.LoteDAO;
import datos.util.EntityManagerFactorySingleton;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.produccion.administracion.Lote;

/**
 *
 * @author fredy
 */
public class LoteControlador {

    private LoteDAO dao;

    public LoteControlador() {
        dao = new LoteDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Lote nuevo(String nombre) {
        return new Lote(nombre);
    }

    public Lote buscar(long id) throws Exception {
        return dao.findLote(id);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Lote nueva) {
        dao.create(nueva);
    }

    public void editar(Lote seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<Lote> leerLista() {
        return dao.findLoteEntities();
    }

    public boolean validar(Lote nuevo, boolean b) {
        if (nuevo.getNombre() == null) {
            JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        Lote l = null;
        try {
            l = dao.findLote(nuevo.getNombre());
        } catch (Exception ex) {
        }
        if (l == null) {
            return true;
        } else {
            if (l.getId() == nuevo.getId()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El registro ya está en la base de datos.", "Registro duplicado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
    }
}
