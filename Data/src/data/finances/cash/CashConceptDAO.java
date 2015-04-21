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
import model.finances.cash.CashConcept;
import model.finances.cash.Cash;
import model.administration.Farm;

/**
 *
 * @author JohnFredy
 */
public class CashConceptDAO implements Serializable{
    
    public CashConceptDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CashConcept cashConcept) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cashConcept);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CashConcept cashConcept) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cashConcept = em.merge(cashConcept);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = cashConcept.getId();
                if (findCashConcept(id) == null) {
                    throw new NonexistentEntityException("The cashConcept with id " + id + " no longer exists.");
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
            CashConcept cashConcept;
            try {
                cashConcept = em.getReference(CashConcept.class, id);
                cashConcept.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cashConcept with id " + id + " no longer exists.", enfe);
            }
            em.remove(cashConcept);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CashConcept> findCashConceptEntities() {
        return findCashConceptEntities(true, -1, -1);
    }

    public List<CashConcept> findCashConceptEntities(int maxResults, int firstResult) {
        return findCashConceptEntities(false, maxResults, firstResult);
    }

    private List<CashConcept> findCashConceptEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CashConcept.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(CashConcept.class).get("conceptDate")));
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
   
    public CashConcept findCashConcept(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CashConcept.class, id);
        } finally {
            em.close();
        }
    }
    
    public CashConcept findCashConcept(String description) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<CashConcept> query = em.createQuery("SELECT cc FROM CashConcept cc WHERE cc.description = :description", CashConcept.class);
            query.setParameter("description", description);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public CashConcept findCashConcept(String description, Cash cash) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<CashConcept> query = em.createQuery("SELECT cc FROM CashConcept cc WHERE cc.description = :description AND cc.cash = :cash", CashConcept.class);
            query.setParameter("description", description);
            query.setParameter("cash", cash);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<CashConcept> findCashConceptEntities(Cash cash) {
        EntityManager em = getEntityManager();
        String queryString ="SELECT cc FROM CashConcept cc";
        if(cash != null){
            queryString += " WHERE cc.cash = :cash";
        }
        queryString += " ORDER BY cc.conceptDate ASC";
        try {
            TypedQuery<CashConcept> query = em.createQuery(queryString, CashConcept.class);
            if(cash !=null){
                query.setParameter("cash", cash);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getCashConceptCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CashConcept> rt = cq.from(CashConcept.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<CashConcept> findCashConceptEntitiesForSelectedFarm(Farm selectedFarm) {
        EntityManager em = getEntityManager();
        String queryString ="SELECT cc FROM CashConcept cc WHERE cc.cash.farm = :farm ORDER BY cc.conceptDate ASC";
        try {
            TypedQuery<CashConcept> query = em.createQuery(queryString, CashConcept.class);
            query.setParameter("farm", selectedFarm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
