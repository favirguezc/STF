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
import modelo.produccion.MonitoreoDeEnfermedades;

/**
 *
 * @author fredy
 */
public class MonitoreoDeEnfermedadesJpaController implements Serializable {

    public MonitoreoDeEnfermedadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MonitoreoDeEnfermedades monitoreoDeEnfermedades) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(monitoreoDeEnfermedades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MonitoreoDeEnfermedades monitoreoDeEnfermedades) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            monitoreoDeEnfermedades = em.merge(monitoreoDeEnfermedades);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = monitoreoDeEnfermedades.getId();
                if (findMonitoreoDeEnfermedades(id) == null) {
                    throw new NonexistentEntityException("The monitoreoDeEnfermedades with id " + id + " no longer exists.");
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
            MonitoreoDeEnfermedades monitoreoDeEnfermedades;
            try {
                monitoreoDeEnfermedades = em.getReference(MonitoreoDeEnfermedades.class, id);
                monitoreoDeEnfermedades.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The monitoreoDeEnfermedades with id " + id + " no longer exists.", enfe);
            }
            em.remove(monitoreoDeEnfermedades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MonitoreoDeEnfermedades> findMonitoreoDeEnfermedadesEntities() {
        return findMonitoreoDeEnfermedadesEntities(true, -1, -1);
    }

    public List<MonitoreoDeEnfermedades> findMonitoreoDeEnfermedadesEntities(int maxResults, int firstResult) {
        return findMonitoreoDeEnfermedadesEntities(false, maxResults, firstResult);
    }

    private List<MonitoreoDeEnfermedades> findMonitoreoDeEnfermedadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MonitoreoDeEnfermedades.class));
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

    public MonitoreoDeEnfermedades findMonitoreoDeEnfermedades(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MonitoreoDeEnfermedades.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonitoreoDeEnfermedadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MonitoreoDeEnfermedades> rt = cq.from(MonitoreoDeEnfermedades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
