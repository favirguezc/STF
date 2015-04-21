/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.finanzas;

import data.exceptions.NonexistentEntityException;
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
import model.finances.purchase.ChemicalPurchase;
import model.administration.Farm;

/**
 *
 * @author John Fredy
 */
public class CompraDAO {
    
    public CompraDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ChemicalPurchase compra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ChemicalPurchase compra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            compra = em.merge(compra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = compra.getId();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
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
            ChemicalPurchase compra;
            try {
                compra = em.getReference(ChemicalPurchase.class, id);
                compra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ChemicalPurchase> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<ChemicalPurchase> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<ChemicalPurchase> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ChemicalPurchase.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(ChemicalPurchase.class).get("dateCompra")));
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

    public ChemicalPurchase findCompra(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ChemicalPurchase.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
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
    
    public List<ChemicalPurchase> findCompraEntities(Farm farm, Date start, Date end) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT t FROM Compra t";
        if (farm != null || start != null || end != null) {
            queryString += " WHERE";
        }
        if (farm != null) {
            queryString += " t.farm = :farm";
            a = true;
        }
        if (end != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.dateCompra BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.dateCompra = :date1";
            b = true;
        }
        queryString += " ORDER BY t.dateCompra ASC";
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
    
    public List<ChemicalPurchase> findCompraEntitiesForSelectedFarm(Farm selectedFarm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ChemicalPurchase> query = em.createQuery("SELECT co FROM Compra co WHERE co.farm = :farm ORDER BY co.dateCompra ASC", ChemicalPurchase.class);
            query.setParameter("farm", selectedFarm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
