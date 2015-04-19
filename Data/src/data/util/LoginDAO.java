/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.util;

import data.administration.PersonDAO;
import javax.persistence.EntityManagerFactory;
import model.administration.Person;

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
        Person person = new PersonDAO(emf).findPersonByIdNumber(id);
        if (person == null) {
            return false;
        }
        return person.getPassword().equals(pass);
    }

}
