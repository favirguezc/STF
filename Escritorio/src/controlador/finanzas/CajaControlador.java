/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.finanzas;

import java.util.List;
import javax.swing.JOptionPane;
import datos.finanzas.CajaDAO;
import datos.exceptions.NonexistentEntityException;
import datos.util.EntityManagerFactorySingleton;
import modelo.finanzas.caja.Caja;

/**
 *
 * @author JohnFredy
 */
public class CajaControlador {
    
    private CajaDAO dao;

    public CajaControlador() {
        dao = new CajaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Caja nuevo(String nombre) {
        return new Caja(nombre);
    }

    public Caja buscar(long id) throws Exception {
        return dao.findCaja(id);
    }
    
    public Caja buscar(String nombre) throws Exception {
        return dao.findCaja(nombre);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Caja nueva) {
        dao.create(nueva);
    }

    public void editar(Caja seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<Caja> leerLista() {
        return dao.findCajaEntities();
    }

    public boolean validar(Caja nuevo) {
        if (nuevo.getNombre() == null) {
            JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        Caja caja = null;
        try {
            caja = dao.findCaja(nuevo.getNombre());
        } catch (Exception ex) {
        }
        if (caja == null) {
            return true;
        } else {
            if (caja.getId() == nuevo.getId()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El registro ya está en la base de datos.", "Registro duplicado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
    }
    
    public void eliminarConcepto(long id, long idConcepto) throws NonexistentEntityException, Exception{
        dao.eliminarConcepto(id, idConcepto);
    }
}
