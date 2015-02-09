/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.administracion;

import dao.administracion.RolPersonaDAO;
import dao.exceptions.NonexistentEntityException;
import dao.util.EntityManagerFactorySingleton;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.produccion.administracion.Persona;
import modelo.produccion.administracion.Rol;
import modelo.produccion.administracion.RolPersona;
import util.Validador;

/**
 *
 * @author fredy
 */
public class RolPersonaControlador {

    private RolPersonaDAO dao;

    public RolPersonaControlador() {
        dao = new RolPersonaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public RolPersona nuevo(Persona persona, Rol rol) {
        return new RolPersona(persona, rol);
    }

    public void guardar(RolPersona rolpersona) {
        dao.create(rolpersona);
    }

    public RolPersona buscar(long id) throws Exception {
        return dao.findRolPersona(id);
    }

    public RolPersona buscar(Persona p, Rol r) throws Exception {
        return dao.findRolPersona(p, r);
    }

    public List<Persona> leerLista(Rol rol) {
        return dao.findPersonaEntities(rol);
    }

    public List<Rol> leerLista(Persona persona) {
        return dao.findRolEntities(persona);
    }

    public void eliminar(long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void editar(RolPersona rp) throws Exception {
        dao.edit(rp);
    }

    public List<RolPersona> leerLista() {
        return dao.findRolPersonaEntities();
    }

    public boolean validar(RolPersona rp, boolean nuevo) {
        if (rp.getPersona() == null) {
            JOptionPane.showMessageDialog(null, "El campo persona no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (rp.getRol() == null) {
            JOptionPane.showMessageDialog(null, "El campo rol no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (nuevo) {
            RolPersona rp2 = null;
            try {
                rp2 = buscar(rp.getPersona(), rp.getRol());
            } catch (Exception e) {
            }
            if (rp2 != null) {
                JOptionPane.showMessageDialog(null, "El registro ya está en la base de datos.", "Registro duplicado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
