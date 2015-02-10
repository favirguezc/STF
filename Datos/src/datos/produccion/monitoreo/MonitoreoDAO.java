/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.monitoreo;

import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.produccion.monitoreo.Monitoreo;

/**
 *
 * @author fredy
 */
public class MonitoreoDAO implements Serializable {

    public MonitoreoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Monitoreo monitoreo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(monitoreo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Monitoreo monitoreo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            monitoreo = em.merge(monitoreo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = monitoreo.getId();
                if (findMonitoreo(id) == null) {
                    throw new NonexistentEntityException("The monitoreo with id " + id + " no longer exists.");
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
            Monitoreo monitoreo;
            try {
                monitoreo = em.getReference(Monitoreo.class, id);
                monitoreo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The monitoreo with id " + id + " no longer exists.", enfe);
            }
            em.remove(monitoreo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Monitoreo> findMonitoreoEntities() {
        return findMonitoreoEntities(true, -1, -1);
    }

    public List<Monitoreo> findMonitoreoEntities(int maxResults, int firstResult) {
        return findMonitoreoEntities(false, maxResults, firstResult);
    }

    private List<Monitoreo> findMonitoreoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Monitoreo.class));
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

    public Monitoreo findMonitoreo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Monitoreo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonitoreoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Monitoreo> rt = cq.from(Monitoreo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
