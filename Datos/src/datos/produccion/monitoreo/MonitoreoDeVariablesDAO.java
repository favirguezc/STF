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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.monitoring.Monitoring;
import model.monitoring.ParameterMonitoring;

/**
 *
 * @author fredy
 */
public class MonitoreoDeVariablesDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public MonitoreoDeVariablesDAO(EntityManagerFactory emf) {
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
     * @param monitoreoDeVariables
     */
    public void create(ParameterMonitoring monitoreoDeVariables) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(monitoreoDeVariables);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param monitoreoDeVariables
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(ParameterMonitoring monitoreoDeVariables) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            monitoreoDeVariables = em.merge(monitoreoDeVariables);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = monitoreoDeVariables.getId();
                if (findMonitoreoDeVariables(id) == null) {
                    throw new NonexistentEntityException("The monitoreoDeVariables with id " + id + " no longer exists.");
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
            ParameterMonitoring monitoreoDeVariables;
            try {
                monitoreoDeVariables = em.getReference(ParameterMonitoring.class, id);
                monitoreoDeVariables.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The monitoreoDeVariables with id " + id + " no longer exists.", enfe);
            }
            em.remove(monitoreoDeVariables);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param monitoreo
     * @return
     */
    public List<ParameterMonitoring> findMonitoreoDeVariablesEntities(Monitoring monitoreo) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ParameterMonitoring> query = em.createQuery("SELECT t FROM MonitoreoDeVariables t WHERE t.monitoreo = :monitoreo", ParameterMonitoring.class);
            query.setParameter("monitoreo", monitoreo);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public List<ParameterMonitoring> findMonitoreoDeVariablesEntities() {
        return findMonitoreoDeVariablesEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<ParameterMonitoring> findMonitoreoDeVariablesEntities(int maxResults, int firstResult) {
        return findMonitoreoDeVariablesEntities(false, maxResults, firstResult);
    }

    private List<ParameterMonitoring> findMonitoreoDeVariablesEntities(boolean all, int maxResults, int firstResult) {
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
    public ParameterMonitoring findMonitoreoDeVariables(long id) {
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
    public int getMonitoreoDeVariablesCount() {
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
