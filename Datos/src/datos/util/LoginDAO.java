/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.util;

import datos.produccion.administracion.PersonaDAO;
import javax.persistence.EntityManagerFactory;
import modelo.produccion.administracion.Persona;

/**
 *
 * @author fredy
 */
public class LoginDAO {

    private EntityManagerFactory emf = null;

    public LoginDAO(EntityManagerFactory entityManagerFactory) {
        this.emf = entityManagerFactory;
    }

    public boolean login(String user, String pass) {
        long id = -1;
        try {
            id = Long.parseLong(user);
        } catch (Exception e) {
            return false;
        }
        if (id <= 0) {
            return false;
        }
        Persona persona = new PersonaDAO(emf).findPersonaPorCedula(id);
        if (persona == null) {
            return false;
        }
        return persona.getContrasena().equals(pass);
    }

}
