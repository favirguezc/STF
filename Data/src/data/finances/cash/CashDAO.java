/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.finances.cash;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import data.exceptions.NonexistentEntityException;
import java.io.Serializable;
import model.finances.cash.Cash;
import model.administration.Farm;

/**
 *
 * @author JohnFredy
 */
public class CashDAO implements Serializable {
    
    public CashDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cash cash) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cash);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cash cash) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cash = em.merge(cash);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = cash.getId();
                if (findCash(id) == null) {
                    throw new NonexistentEntityException("The cash with id " + id + " no longer exists.");
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
            Cash cash;
            try {
                cash = em.getReference(Cash.class, id);
                cash.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cash with id " + id + " no longer exists.", enfe);
            }
            em.remove(cash);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cash> findCashEntities() {
        return findCashEntities(true, -1, -1);
    }

    public List<Cash> findCashEntities(int maxResults, int firstResult) {
        return findCashEntities(false, maxResults, firstResult);
    }

    private List<Cash> findCashEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cash.class));
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

    public Cash findCash(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cash.class, id);
        } finally {
            em.close();
        }
    }
    
    public Cash findCash(String name) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cash> query = em.createQuery("SELECT c FROM Cash c WHERE c.name = :name", Cash.class);
            query.setParameter("name", name);
            Cash cash = query.getSingleResult();
            return cash;
        } finally {
            em.close();
        }
    }

    public int getCashCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cash> rt = cq.from(Cash.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Cash> findCashEntitiesForSelectedFarm(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cash> query = em.createQuery("SELECT c FROM Cash c WHERE c.farm = :farm", Cash.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
