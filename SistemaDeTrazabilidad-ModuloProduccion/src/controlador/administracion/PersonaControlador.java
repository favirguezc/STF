/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.administracion;

import dao.administracion.PersonaDAO;
import dao.exceptions.NonexistentEntityException;
import dao.util.EntityManagerFactorySingleton;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.administracion.Persona;

/**
 *
 * @author fredy
 */
public class PersonaControlador {

    private PersonaDAO dao;

    public PersonaControlador() {
        dao = new PersonaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Persona nuevo(String nombre, String nombre2, String apellido, String apellido2, long cedula, String sexo, long telefono, String sangre) {
        return new Persona(nombre, nombre2, apellido, apellido2, cedula, sexo, telefono, sangre);
    }

    public Persona buscar(long id) throws Exception {
        return dao.findPersona(id);
    }

    public Persona buscarPorCedula(long cedula) throws Exception {
        return dao.findPersonaPorCedula(cedula);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Persona nueva) {
        dao.create(nueva);
    }

    public void editar(Persona seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<Persona> leerLista() {
        return dao.findPersonaEntities();
    }

    public boolean validar(Persona nuevo) {
        if (nuevo.getNombre() == null) {
            JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (nuevo.getNombre().length() < 2) {
            JOptionPane.showMessageDialog(null, "El campo nombre no puede ser tan corto.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (nuevo.getApellido() == null) {
            JOptionPane.showMessageDialog(null, "El campo apellido no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (nuevo.getApellido().length() < 2) {
            JOptionPane.showMessageDialog(null, "El campo apellido no puede ser tan corto.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (nuevo.getCedula() == 0) {
            JOptionPane.showMessageDialog(null, "El campo cédula no puede ser cero.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        try {
            if (buscarPorCedula(nuevo.getCedula()) != null && buscarPorCedula(nuevo.getCedula()).getId() != nuevo.getId()) {
                JOptionPane.showMessageDialog(null, "El número de cédula " + nuevo.getCedula() + " ya se encuentra asignado.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } catch (Exception e) {
        }

        return true;
    }
}
