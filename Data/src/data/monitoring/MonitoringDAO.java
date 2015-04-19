/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.monitoring;

import data.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.monitoring.Monitoring;

/**
 *
 * @author fredy
 */
public class MonitoringDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public MonitoringDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    /**
     *
     * @return
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     *
     * @param monitoring
     */
    public void create(Monitoring monitoring) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(monitoring);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param monitoring
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Monitoring monitoring) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            monitoring = em.merge(monitoring);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = monitoring.getId();
                if (findMonitoring(id) == null) {
                    throw new NonexistentEntityException("The monitoring with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param id
     * @throws NonexistentEntityException
     */
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Monitoring monitoring;
            try {
                monitoring = em.getReference(Monitoring.class, id);
                monitoring.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The monitoring with id " + id + " no longer exists.", enfe);
            }
            em.remove(monitoring);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @return
     */
    public List<Monitoring> findMonitoringEntities() {
        return findMonitoringEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Monitoring> findMonitoringEntities(int maxResults, int firstResult) {
        return findMonitoringEntities(false, maxResults, firstResult);
    }

    private List<Monitoring> findMonitoringEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Monitoring.class));
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

    /**
     *
     * @param id
     * @return
     */
    public Monitoring findMonitoring(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Monitoring.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getMonitoringCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Monitoring> rt = cq.from(Monitoring.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
