/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.finances.cost;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import data.exceptions.NonexistentEntityException;
import data.exceptions.PreexistingEntityException;
import java.util.Date;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import model.finances.cost.Cost;
import model.finances.cost.CostTypeEnum;
import model.administration.Farm;
import model.administration.ModuleClass;

/**
 *
 * @author John Fredy
 */
public class CostDAO implements Serializable {

    public CostDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cost cost) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cost);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCost(cost.getId()) != null) {
                throw new PreexistingEntityException("Cost " + cost + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cost cost) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cost = em.merge(cost);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = cost.getId();
                if (findCost(id) == null) {
                    throw new NonexistentEntityException("The cost with id " + id + " no longer exists.");
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
            Cost cost;
            try {
                cost = em.getReference(Cost.class, id);
                cost.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cost with id " + id + " no longer exists.", enfe);
            }
            em.remove(cost);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cost> findCostEntities() {
        return findCostEntities(true, -1, -1);
    }

    public List<Cost> findCostEntities(int maxResults, int firstResult) {
        return findCostEntities(false, maxResults, firstResult);
    }

    private List<Cost> findCostEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cost.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Cost.class).get("costDate")));
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

    public Cost findCost(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cost.class, id);
        } finally {
            em.close();
        }
    }

    public int getCostCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cost> rt = cq.from(Cost.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Cost> findCostEntities(ModuleClass moduleObject, CostTypeEnum type, Date start, Date end) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT c FROM Cost c";
        if (moduleObject != null || type != null || start != null || end != null) {
            queryString += " WHERE";
        }
        if (moduleObject != null) {
            queryString += " c.moduleObject = :moduleObject";
            d = true;
        }
        if (type != null) {
            if (d) {
                queryString += " AND";
            }
            queryString += " c.type = :type";
            a = true;
        }
        if (end != null) {
            if (a || d) {
                queryString += " AND";
            }
            queryString += " c.costDate BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            if (a || d) {
                queryString += " AND";
            }
            queryString += " c.costDate = :date1";
            b = true;
        }
        queryString += " ORDER BY c.costDate ASC";
        try {
            TypedQuery<Cost> query = em.createQuery(queryString, Cost.class);
            if (b) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (c) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("type", type);
            }
            if (d) {
                query.setParameter("moduleObject", moduleObject);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Cost> findCostEntities(ModuleClass moduleObject) {
        EntityManager em = getEntityManager();
        String queryString = "SELECT c FROM Cost c";
        if (moduleObject != null) {
            queryString += " WHERE c.moduleObject = :moduleObject";
        }
        queryString += " ORDER BY c.costDate ASC";
        try {
            TypedQuery<Cost> query = em.createQuery(queryString, Cost.class);
            if (moduleObject != null) {
                query.setParameter("moduleObject", moduleObject);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Cost> findCostEntitiesForSelectedFarm(Farm selectedFarm) {
        EntityManager em = getEntityManager();
        String queryString = "SELECT c FROM Cost c WHERE c.moduleObject.lot.farm = :farm ORDER BY c.costDate ASC";
        try {
            TypedQuery<Cost> query = em.createQuery(queryString, Cost.class);
            query.setParameter("farm", selectedFarm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
