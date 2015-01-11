/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.produccion.monitoreo.MonitoreoDeVariables;

/**
 *
 * @author fredy
 */
public class MonitoreoDeVariablesJpaController implements Serializable {

    public MonitoreoDeVariablesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MonitoreoDeVariables monitoreoDeVariables) {
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

    public void edit(MonitoreoDeVariables monitoreoDeVariables) throws NonexistentEntityException, Exception {
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

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MonitoreoDeVariables monitoreoDeVariables;
            try {
                monitoreoDeVariables = em.getReference(MonitoreoDeVariables.class, id);
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

    public List<MonitoreoDeVariables> findMonitoreoDeVariablesEntities() {
        return findMonitoreoDeVariablesEntities(true, -1, -1);
    }

    public List<MonitoreoDeVariables> findMonitoreoDeVariablesEntities(int maxResults, int firstResult) {
        return findMonitoreoDeVariablesEntities(false, maxResults, firstResult);
    }

    private List<MonitoreoDeVariables> findMonitoreoDeVariablesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MonitoreoDeVariables.class));
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

    public MonitoreoDeVariables findMonitoreoDeVariables(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MonitoreoDeVariables.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonitoreoDeVariablesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MonitoreoDeVariables> rt = cq.from(MonitoreoDeVariables.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
