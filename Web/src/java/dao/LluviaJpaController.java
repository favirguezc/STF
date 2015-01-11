/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
import modelo.produccion.variablesClimaticas.Lluvia;

/**
 *
 * @author fredy
 */
public class LluviaJpaController implements Serializable {

    public LluviaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lluvia lluvia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(lluvia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lluvia lluvia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            lluvia = em.merge(lluvia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = lluvia.getId();
                if (findLluvia(id) == null) {
                    throw new NonexistentEntityException("The lluvia with id " + id + " no longer exists.");
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
            Lluvia lluvia;
            try {
                lluvia = em.getReference(Lluvia.class, id);
                lluvia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lluvia with id " + id + " no longer exists.", enfe);
            }
            em.remove(lluvia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lluvia> findLluviaEntities() {
        return findLluviaEntities(true, -1, -1);
    }

    public List<Lluvia> findLluviaEntities(int maxResults, int firstResult) {
        return findLluviaEntities(false, maxResults, firstResult);
    }

    private List<Lluvia> findLluviaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lluvia.class));
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

    public List<Lluvia> findLluviaEntities(Date fecha1, Date fecha2) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Lluvia> query = em.createQuery("SELECT t FROM Lluvia t WHERE t.fecha BETWEEN :fecha1 AND :fecha2", Lluvia.class);
            query.setParameter("fecha1", fecha1, TemporalType.DATE);
            query.setParameter("fecha2", fecha2, TemporalType.DATE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Lluvia> findLluviaEntities(Date fecha1) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Lluvia> query = em.createQuery("SELECT t FROM Lluvia t WHERE t.fecha = :fecha1", Lluvia.class);
            query.setParameter("fecha1", fecha1, TemporalType.DATE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Lluvia findLluvia(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lluvia.class, id);
        } finally {
            em.close();
        }
    }

    public int getLluviaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lluvia> rt = cq.from(Lluvia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
