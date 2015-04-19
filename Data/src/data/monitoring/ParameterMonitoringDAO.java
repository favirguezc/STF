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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.monitoring.Monitoring;
import model.monitoring.ParameterMonitoring;

/**
 *
 * @author fredy
 */
public class ParameterMonitoringDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public ParameterMonitoringDAO(EntityManagerFactory emf) {
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
     * @param parameterMonitoring
     */
    public void create(ParameterMonitoring parameterMonitoring) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(parameterMonitoring);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param parameterMonitoring
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(ParameterMonitoring parameterMonitoring) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            parameterMonitoring = em.merge(parameterMonitoring);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = parameterMonitoring.getId();
                if (findParameterMonitoring(id) == null) {
                    throw new NonexistentEntityException("The parameterMonitoring with id " + id + " no longer exists.");
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
    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ParameterMonitoring parameterMonitoring;
            try {
                parameterMonitoring = em.getReference(ParameterMonitoring.class, id);
                parameterMonitoring.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parameterMonitoring with id " + id + " no longer exists.", enfe);
            }
            em.remove(parameterMonitoring);
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
     * @return
     */
    public List<ParameterMonitoring> findParameterMonitoringEntities(Monitoring monitoring) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ParameterMonitoring> query = em.createQuery("SELECT t FROM ParameterMonitoring t WHERE t.monitoring = :monitoring", ParameterMonitoring.class);
            query.setParameter("monitoring", monitoring);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public List<ParameterMonitoring> findParameterMonitoringEntities() {
        return findParameterMonitoringEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<ParameterMonitoring> findParameterMonitoringEntities(int maxResults, int firstResult) {
        return findParameterMonitoringEntities(false, maxResults, firstResult);
    }

    private List<ParameterMonitoring> findParameterMonitoringEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ParameterMonitoring.class));
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
    public ParameterMonitoring findParameterMonitoring(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ParameterMonitoring.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getParameterMonitoringCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ParameterMonitoring> rt = cq.from(ParameterMonitoring.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
