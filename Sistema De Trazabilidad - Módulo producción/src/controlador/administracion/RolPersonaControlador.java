/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.administracion;

import dao.administracion.RolPersonaDAO;
import dao.exceptions.NonexistentEntityException;
import dao.util.EntityManagerFactorySingleton;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.administracion.Persona;
import modelo.administracion.Rol;
import modelo.administracion.RolPersona;
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

    public RolPersona nuevo(Persona persona, Rol rol, String contraseña) {
        return new RolPersona(persona, rol, contraseña);
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
        List<RolPersona> findPersonaEntities = dao.findPersonaEntities(rol);
        List<Persona> lista = new LinkedList<>();
        for (RolPersona rp : findPersonaEntities) {
            lista.add(rp.getPersona());
        }
        return lista;
    }

    public List<Rol> leerLista(Persona persona) {
        List<RolPersona> findRolEntities = dao.findRolEntities(persona);
        List<Rol> lista = new LinkedList<>();
        for (RolPersona rp : findRolEntities) {
            lista.add(rp.getRol());
        }
        return lista;
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
        if (rp.getRol().isExigeContraseña()) {
            switch (Validador.validarContraseña(rp.getContraseña(), 4, 8, true, true, true)) {
                case Validador.CONTRASEÑA_MUY_CORTA:
                    JOptionPane.showMessageDialog(null, "La contraseña es muy corta. La longitud mínima es " + 4 + ".", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                case Validador.CONTRASEÑA_MUY_LARGA:
                    JOptionPane.showMessageDialog(null, "La contraseña es muy larga. La longitud máxima es " + 8 + ".", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                case Validador.CONTRASEÑA_SIN_MAYUSCULAS:
                    JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos una letra mayúscula.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                case Validador.CONTRASEÑA_SIN_MINUSCULAS:
                    JOptionPane.showMessageDialog(null, "La contraseña debe contener la menos una letra minúscula.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                case Validador.CONTRASEÑA_SIN_NUMERO:
                    JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos un número.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                case Validador.CONTRASEÑA_VACIA:
                    JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
                    return false;
            }
        }
        return true;
    }
}
