/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.finances;

import data.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.finances.Price;

/**
 *
 * @author John Fredy
 */
public class PriceDAO {
    
    public PriceDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Price price) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(price);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Price price) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            price = em.merge(price);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = price.getId();
                if (findPrice(id) == null) {
                    throw new NonexistentEntityException("The price with id " + id + " no longer exists.");
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
            Price price;
            try {
                price = em.getReference(Price.class, id);
                price.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The price with id " + id + " no longer exists.", enfe);
            }
            em.remove(price);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Price> findPriceEntities() {
        return findPriceEntities(true, -1, -1);
    }

    public List<Price> findPriceEntities(int maxResults, int firstResult) {
        return findPriceEntities(false, maxResults, firstResult);
    }

    private List<Price> findPriceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Price.class));
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

    public Price findPrice(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Price.class, id);
        } finally {
            em.close();
        }
    }
    
    public Price findPrice(String item) {
        EntityManager em = getEntityManager();
        String queryString = "SELECT p FROM Price p";
        if (item != null) {
            queryString += " WHERE p.item = :item";
        }
        try {
            TypedQuery<Price> query = em.createQuery(queryString, Price.class);
            query.setParameter("item", item);
            List<Price> result = query.getResultList();
            if(result == null || result.isEmpty()){
                return null;
            }
            return result.get(0);
        } finally {
            em.close();
        }
    }

    public int getPriceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Price> rt = cq.from(Price.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
