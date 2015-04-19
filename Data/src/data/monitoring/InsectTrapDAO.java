/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.monitoring;

import data.exceptions.NonexistentEntityException;
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
import model.monitoring.InsectTrap;

/**
 *
 * @author fredy
 */
public class InsectTrapDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public InsectTrapDAO(EntityManagerFactory emf) {
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
     * @param insectTrap
     */
    public void create(InsectTrap insectTrap) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(insectTrap);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param insectTrap
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(InsectTrap insectTrap) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            insectTrap = em.merge(insectTrap);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = insectTrap.getId();
                if (findInsectTrap(id) == null) {
                    throw new NonexistentEntityException("The insectTrap with id " + id + " no longer exists.");
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
            InsectTrap insectTrap;
            try {
                insectTrap = em.getReference(InsectTrap.class, id);
                insectTrap.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The insectTrap with id " + id + " no longer exists.", enfe);
            }
            em.remove(insectTrap);
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
    public List<InsectTrap> findInsectTrapEntities() {
        return findInsectTrapEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<InsectTrap> findInsectTrapEntities(int maxResults, int firstResult) {
        return findInsectTrapEntities(false, maxResults, firstResult);
    }

    private List<InsectTrap> findInsectTrapEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InsectTrap.class));
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
    public InsectTrap findInsectTrap(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InsectTrap.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getInsectTrapCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InsectTrap> rt = cq.from(InsectTrap.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param date1
     * @param date2
     * @return
     */
    public List<InsectTrap> findInsectTrapEntities(Date date1, Date date2) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<InsectTrap> query = em.createQuery("SELECT i FROM InsectTrap i WHERE i.registerDate BETWEEN :date1 AND :date2", InsectTrap.class
            );
            query.setParameter(
                    "date1", date1, TemporalType.DATE);
            query.setParameter(
                    "date2", date2, TemporalType.DATE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
