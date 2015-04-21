/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.finanzas;

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
public class CajaDAO implements Serializable {
    
    public CajaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cash caja) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(caja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cash caja) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            caja = em.merge(caja);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = caja.getId();
                if (findCaja(id) == null) {
                    throw new NonexistentEntityException("The caja with id " + id + " no longer exists.");
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
            Cash caja;
            try {
                caja = em.getReference(Cash.class, id);
                caja.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caja with id " + id + " no longer exists.", enfe);
            }
            em.remove(caja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cash> findCajaEntities() {
        return findCajaEntities(true, -1, -1);
    }

    public List<Cash> findCajaEntities(int maxResults, int firstResult) {
        return findCajaEntities(false, maxResults, firstResult);
    }

    private List<Cash> findCajaEntities(boolean all, int maxResults, int firstResult) {
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

    public Cash findCaja(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cash.class, id);
        } finally {
            em.close();
        }
    }
    
    public Cash findCaja(String name) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cash> query = em.createQuery("SELECT c FROM Caja c WHERE c.name = :name", Cash.class);
            query.setParameter("name", name);
            Cash caja = query.getSingleResult();
            return caja;
        } finally {
            em.close();
        }
    }

    public int getCajaCount() {
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
    
    public List<Cash> findCajaEntitiesForSelectedFarm(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cash> query = em.createQuery("SELECT c FROM Caja c WHERE c.farm = :farm", Cash.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
