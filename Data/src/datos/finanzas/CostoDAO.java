/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.finanzas;

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
public class CostoDAO implements Serializable {

    public CostoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cost costo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(costo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCosto(costo.getId()) != null) {
                throw new PreexistingEntityException("Costo " + costo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cost costo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            costo = em.merge(costo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = costo.getId();
                if (findCosto(id) == null) {
                    throw new NonexistentEntityException("The costo with id " + id + " no longer exists.");
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
            Cost costo;
            try {
                costo = em.getReference(Cost.class, id);
                costo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The costo with id " + id + " no longer exists.", enfe);
            }
            em.remove(costo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cost> findCostoEntities() {
        return findCostoEntities(true, -1, -1);
    }

    public List<Cost> findCostoEntities(int maxResults, int firstResult) {
        return findCostoEntities(false, maxResults, firstResult);
    }

    private List<Cost> findCostoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cost.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Cost.class).get("date")));
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

    public Cost findCosto(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cost.class, id);
        } finally {
            em.close();
        }
    }

    public int getCostoCount() {
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
    
    public List<Cost> findCostoEntities(ModuleClass modulo, CostTypeEnum type, Date start, Date end) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT c FROM Costo c";
        if (modulo != null || type != null || start != null || end != null) {
            queryString += " WHERE";
        }
        if (modulo != null) {
            queryString += " c.modulo = :modulo";
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
            queryString += " c.date BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            if (a || d) {
                queryString += " AND";
            }
            queryString += " c.date = :date1";
            b = true;
        }
        queryString += " ORDER BY c.date ASC";
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
                query.setParameter("modulo", modulo);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Cost> findCostoEntities(ModuleClass modulo) {
        EntityManager em = getEntityManager();
        String queryString = "SELECT c FROM Costo c";
        if (modulo != null) {
            queryString += " WHERE c.modulo = :modulo";
        }
        queryString += " ORDER BY c.date ASC";
        try {
            TypedQuery<Cost> query = em.createQuery(queryString, Cost.class);
            if (modulo != null) {
                query.setParameter("modulo", modulo);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Cost> findCostoEntitiesForSelectedFarm(Farm selectedFarm) {
        EntityManager em = getEntityManager();
        String queryString = "SELECT c FROM Costo c WHERE c.modulo.lot.farm = :farm ORDER BY c.date ASC";
        try {
            TypedQuery<Cost> query = em.createQuery(queryString, Cost.class);
            query.setParameter("farm", selectedFarm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
