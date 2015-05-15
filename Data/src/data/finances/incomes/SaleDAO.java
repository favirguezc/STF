/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.finances.incomes;

import data.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.finances.incomes.Sale;
import model.administration.Farm;
import model.administration.Person;

/**
 *
 * @author JohnFredy
 */
public class SaleDAO implements Serializable {
    
    private EntityManagerFactory emf = null;
    
    public SaleDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
   
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Sale sale) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sale);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Sale sale) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sale = em.merge(sale);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = sale.getId();
                if (findSale(id) == null) {
                    throw new NonexistentEntityException("The sale with id " + id + " no longer exists.");
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
            Sale sale;
            try {
                sale = em.getReference(Sale.class, id);
                sale.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sale with id " + id + " no longer exists.", enfe);
            }
            em.remove(sale);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Sale> findSaleEntities() {
        return findSaleEntities(true, -1, -1);
    }

    public List<Sale> findSaleEntities(int maxResults, int firstResult) {
        return findSaleEntities(false, maxResults, firstResult);
    }
    
    private List<Sale> findSaleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sale.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Sale.class).get("saleDate")));
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
    
    public Sale findSale(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sale.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getSaleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sale> rt = cq.from(Sale.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Sale> findSaleEntities(Person customer, Date start, Date end, Farm farm) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT s FROM Sale s";
        if (customer != null || start != null || end != null || farm != null) {
            queryString += " WHERE";
        }
        if (customer != null) {
            queryString += " s.customer = :customer";
            a = true;
        }
        if (end != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " s.saleDate BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " s.saleDate = :date1";
            b = true;
        }
        if (farm != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " s.farm = :farm";
            d = true;
        }
        queryString += " ORDER BY s.saleDate ASC";
        try {
            TypedQuery<Sale> query = em.createQuery(queryString, Sale.class);
            if (b) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (c) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("customer", customer);
            }
            if (d) {
                query.setParameter("farm", farm);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Sale findSale(Sale sale) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Sale> query = em.createQuery("SELECT s FROM Sale s WHERE s.saleDate = :saleDate AND s.customer = :customer AND s.farm = :farm  AND s.saleTotalValue = :saleTotalValue", Sale.class);
            query.setParameter("saleDate", sale.getSaleDate());
            query.setParameter("customer", sale.getCustomer());
            query.setParameter("farm", sale.getFarm());
            query.setParameter("saleTotalValue", sale.getSaleTotalValue());
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<Sale> findSaleEntitiesForSelectedFarm(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Sale> query = em.createQuery("SELECT s FROM Sale s WHERE s.farm = :farm  ORDER BY s.saleDate ASC", Sale.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Sale> findSaleEntitiesPayable(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Sale> query = em.createQuery("SELECT s FROM Sale s WHERE s.farm = :farm AND s.valuePayable > 0 ORDER BY s.saleDate ASC", Sale.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Sale> findSaleEntitiesPaidAndPayable(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Sale> query = em.createQuery("SELECT s FROM Sale s WHERE s.farm = :farm AND s.valuePayable < s.saleTotalValue ORDER BY s.saleDate ASC", Sale.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
