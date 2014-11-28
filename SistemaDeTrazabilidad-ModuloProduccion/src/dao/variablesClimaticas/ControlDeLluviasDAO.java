/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.variablesClimaticas;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.variablesClimaticas.ControlDeLluvias;

/**
 *
 * @author fredy
 */
public class ControlDeLluviasDAO implements Serializable {

    public ControlDeLluviasDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ControlDeLluvias controlDeLluvias) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(controlDeLluvias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ControlDeLluvias controlDeLluvias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            controlDeLluvias = em.merge(controlDeLluvias);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = controlDeLluvias.getId();
                if (findControlDeLluvias(id) == null) {
                    throw new NonexistentEntityException("The controlDeLluvias with id " + id + " no longer exists.");
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
            ControlDeLluvias controlDeLluvias;
            try {
                controlDeLluvias = em.getReference(ControlDeLluvias.class, id);
                controlDeLluvias.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The controlDeLluvias with id " + id + " no longer exists.", enfe);
            }
            em.remove(controlDeLluvias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ControlDeLluvias> findControlDeLluviasEntities() {
        return findControlDeLluviasEntities(true, -1, -1);
    }

    public List<ControlDeLluvias> findControlDeLluviasEntities(int maxResults, int firstResult) {
        return findControlDeLluviasEntities(false, maxResults, firstResult);
    }

    private List<ControlDeLluvias> findControlDeLluviasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ControlDeLluvias.class));
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

    public ControlDeLluvias findControlDeLluvias(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ControlDeLluvias.class, id);
        } finally {
            em.close();
        }
    }
    
    public ControlDeLluvias findControlDeLluvias(Date d) throws Exception{
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ControlDeLluvias> query = em.createQuery("SELECT t FROM ControlDeLluvias t WHERE t.fecha = :fecha", ControlDeLluvias.class);
            query.setParameter("fecha", d, TemporalType.DATE);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<ControlDeLluvias> findControlDeLluviasEntities(Date esteMes, Date siguienteMes) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ControlDeLluvias> query = em.createQuery("SELECT t FROM ControlDeLluvias t WHERE t.fecha BETWEEN :fecha1 AND :fecha2", ControlDeLluvias.class);
            query.setParameter("fecha1", esteMes, TemporalType.DATE);
            query.setParameter("fecha2", siguienteMes, TemporalType.DATE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public int getControlDeLluviasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ControlDeLluvias> rt = cq.from(ControlDeLluvias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
