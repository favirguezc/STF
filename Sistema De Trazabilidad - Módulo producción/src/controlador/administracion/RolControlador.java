/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.administracion;

import dao.administracion.RolDAO;
import dao.exceptions.NonexistentEntityException;
import dao.util.EntityManagerFactorySingleton;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.administracion.Rol;

/**
 *
 * @author fredy
 */
public class RolControlador {

    private RolDAO dao;

    public RolControlador() {
        dao = new RolDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Rol nuevo(String nombre, boolean b) {
        return new Rol(nombre, b);
    }

    public Rol buscar(long id) throws Exception {
        return dao.findRol(id);
    }

    public Rol buscar(String nombre) throws Exception {
        return dao.findRol(nombre);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Rol nueva) {
        dao.create(nueva);
    }

    public void editar(Rol seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<Rol> leerLista() {
        return dao.findRolEntities();
    }

    public boolean validar(Rol nuevo, boolean b) {
        if (nuevo.getNombre() == null) {
            JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        try {
            if (dao.findRol(nuevo.getNombre()) != null) {
                return false;
            }
        } catch (Exception ex) {
        }
        return true;
    }

    public void comprobarRegistros() {
        String[] roles = {"Recolector(a)","Trabajador(a)", "Administrador(a)", "Asistente Técnico(a)", "Productor(a)", "Secretario(a)"};
        boolean[] bs = {false, false, false, false, false, true};
        for (int i = 0; i < roles.length; i++) {
            try {
                if (buscar(roles[i]) == null) {
                    guardar(nuevo(roles[i], bs[i]));
                }
            } catch (Exception ex) {
                guardar(nuevo(roles[i], bs[i]));
            }
        }
    }
}
