/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.finances.incomes;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import data.exceptions.NonexistentEntityException;
import javax.persistence.TypedQuery;
import model.finances.incomes.Sale;
import model.finances.incomes.SaleItem;

/**
 *
 * @author John Fredy
 */
public class SaleItemDAO implements Serializable {

    public SaleItemDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SaleItem saleItem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(saleItem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SaleItem saleItem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            saleItem = em.merge(saleItem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = saleItem.getId();
                if (findSaleItem(id) == null) {
                    throw new NonexistentEntityException("The saleItem with id " + id + " no longer exists.");
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
            SaleItem saleItem;
            try {
                saleItem = em.getReference(SaleItem.class, id);
                saleItem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The saleItem with id " + id + " no longer exists.", enfe);
            }
            em.remove(saleItem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SaleItem> findSaleItemEntities() {
        return findSaleItemEntities(true, -1, -1);
    }

    public List<SaleItem> findSaleItemEntities(int maxResults, int firstResult) {
        return findSaleItemEntities(false, maxResults, firstResult);
    }

    private List<SaleItem> findSaleItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SaleItem.class));
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

    public SaleItem findSaleItem(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SaleItem.class, id);
        } finally {
            em.close();
        }
    }

    public int getSaleItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SaleItem> rt = cq.from(SaleItem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<SaleItem> findSaleItemEntities(Sale sale){
        EntityManager em = getEntityManager();
        try {
            TypedQuery<SaleItem> query = em.createQuery("SELECT si FROM SaleItem si WHERE si.sale = :sale", SaleItem.class);
            query.setParameter("sale", sale);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
