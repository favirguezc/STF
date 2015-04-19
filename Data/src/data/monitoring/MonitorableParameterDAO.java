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
import model.monitoring.MonitorableParameter;

/**
 *
 * @author fredy
 */
public class MonitorableParameterDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public MonitorableParameterDAO(EntityManagerFactory emf) {
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
     * @param monitorableParameter
     */
    public void create(MonitorableParameter monitorableParameter) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(monitorableParameter);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param monitorableParameter
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(MonitorableParameter monitorableParameter) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            monitorableParameter = em.merge(monitorableParameter);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = monitorableParameter.getId();
                if (findMonitorableParameter(id) == null) {
                    throw new NonexistentEntityException("The monitorableParameter with id " + id + " no longer exists.");
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
            MonitorableParameter monitorableParameter;
            try {
                monitorableParameter = em.getReference(MonitorableParameter.class, id);
                monitorableParameter.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The monitorableParameter with id " + id + " no longer exists.", enfe);
            }
            em.remove(monitorableParameter);
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
    public List<MonitorableParameter> findMonitorableParameterEntities() {
        return findMonitorableParameterEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<MonitorableParameter> findMonitorableParameterEntities(int maxResults, int firstResult) {
        return findMonitorableParameterEntities(false, maxResults, firstResult);
    }

    private List<MonitorableParameter> findMonitorableParameterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MonitorableParameter.class));
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
    public MonitorableParameter findMonitorableParameter(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MonitorableParameter.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getMonitorableParameterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MonitorableParameter> rt = cq.from(MonitorableParameter.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
