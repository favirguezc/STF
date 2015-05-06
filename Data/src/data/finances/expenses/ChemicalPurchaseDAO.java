/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.finances.expenses;

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
import model.finances.expenses.ChemicalPurchase;
import model.administration.Farm;

/**
 *
 * @author John Fredy
 */
public class ChemicalPurchaseDAO implements Serializable {
    
    public ChemicalPurchaseDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ChemicalPurchase purchase) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(purchase);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ChemicalPurchase purchase) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            purchase = em.merge(purchase);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = purchase.getId();
                if (findChemicalPurchase(id) == null) {
                    throw new NonexistentEntityException("The chemicalPurchase with id " + id + " no longer exists.");
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
            ChemicalPurchase purchase;
            try {
                purchase = em.getReference(ChemicalPurchase.class, id);
                purchase.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chemicalPurchase with id " + id + " no longer exists.", enfe);
            }
            em.remove(purchase);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ChemicalPurchase> findChemicalPurchaseEntities() {
        return findChemicalPurchaseEntities(true, -1, -1);
    }

    public List<ChemicalPurchase> findChemicalPurchaseEntities(int maxResults, int firstResult) {
        return findChemicalPurchaseEntities(false, maxResults, firstResult);
    }

    private List<ChemicalPurchase> findChemicalPurchaseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ChemicalPurchase.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(ChemicalPurchase.class).get("purchaseDate")));
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

    public ChemicalPurchase findChemicalPurchase(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ChemicalPurchase.class, id);
        } finally {
            em.close();
        }
    }

    public int getChemicalPurchaseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ChemicalPurchase> rt = cq.from(ChemicalPurchase.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<ChemicalPurchase> findChemicalPurchaseEntities(Farm farm, Date start, Date end) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT cp FROM ChemicalPurchase cp";
        if (farm != null || start != null || end != null) {
            queryString += " WHERE";
        }
        if (farm != null) {
            queryString += " cp.farm = :farm";
            a = true;
        }
        if (end != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " cp.purchaseDate BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " cp.purchaseDate = :date1";
            b = true;
        }
        queryString += " ORDER BY cp.purchaseDate ASC";
        try {
            TypedQuery<ChemicalPurchase> query = em.createQuery(queryString, ChemicalPurchase.class);
            if (b) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (c) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("farm", farm);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<ChemicalPurchase> findChemicalPurchaseEntitiesForSelectedFarm(Farm selectedFarm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ChemicalPurchase> query = em.createQuery("SELECT cp FROM ChemicalPurchase cp WHERE cp.farm = :farm ORDER BY cp.purchaseDate ASC", ChemicalPurchase.class);
            query.setParameter("farm", selectedFarm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
