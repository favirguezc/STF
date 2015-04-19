/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author fredy
 */
public class EntityManagerFactorySingleton {

    private static EntityManagerFactory emf = null;

    private EntityManagerFactorySingleton() {
    }

    /**
     *
     * @return
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("DatosPU");
            startConection();
        }
        return emf;
    }
    
    private static void startConection() {
        emf.createEntityManager();
    }
}
