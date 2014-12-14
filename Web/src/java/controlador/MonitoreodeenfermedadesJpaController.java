/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Monitoreodeenfermedades;

/**
 *
 * @author fredy
 */
public class MonitoreodeenfermedadesJpaController implements Serializable {

    public MonitoreodeenfermedadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Monitoreodeenfermedades monitoreodeenfermedades) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(monitoreodeenfermedades);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMonitoreodeenfermedades(monitoreodeenfermedades.getId()) != null) {
                throw new PreexistingEntityException("Monitoreodeenfermedades " + monitoreodeenfermedades + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Monitoreodeenfermedades monitoreodeenfermedades) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            monitoreodeenfermedades = em.merge(monitoreodeenfermedades);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = monitoreodeenfermedades.getId();
                if (findMonitoreodeenfermedades(id) == null) {
                    throw new NonexistentEntityException("The monitoreodeenfermedades with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Monitoreodeenfermedades monitoreodeenfermedades;
            try {
                monitoreodeenfermedades = em.getReference(Monitoreodeenfermedades.class, id);
                monitoreodeenfermedades.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The monitoreodeenfermedades with id " + id + " no longer exists.", enfe);
            }
            em.remove(monitoreodeenfermedades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Monitoreodeenfermedades> findMonitoreodeenfermedadesEntities() {
        return findMonitoreodeenfermedadesEntities(true, -1, -1);
    }

    public List<Monitoreodeenfermedades> findMonitoreodeenfermedadesEntities(int maxResults, int firstResult) {
        return findMonitoreodeenfermedadesEntities(false, maxResults, firstResult);
    }

    private List<Monitoreodeenfermedades> findMonitoreodeenfermedadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Monitoreodeenfermedades.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Monitoreodeenfermedades findMonitoreodeenfermedades(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Monitoreodeenfermedades.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonitoreodeenfermedadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Monitoreodeenfermedades> rt = cq.from(Monitoreodeenfermedades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
