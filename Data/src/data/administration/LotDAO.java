/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.administration;

import data.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.administration.Farm;
import model.administration.Lot;

/**
 *
 * @author fredy
 */
public class LotDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public LotDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    /**
     *
     * @return
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     *
     * @param lot
     */
    public void create(Lot lot) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(lot);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param lot
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Lot lot) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            lot = em.merge(lot);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = lot.getId();
                if (findLot(id) == null) {
                    throw new NonexistentEntityException("The lot with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param id
     * @throws NonexistentEntityException
     */
    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lot lot;
            try {
                lot = em.getReference(Lot.class, id);
                lot.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lot with id " + id + " no longer exists.", enfe);
            }
            em.remove(lot);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @return
     */
    public List<Lot> findLotEntities() {
        return findLotEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Lot> findLotEntities(int maxResults, int firstResult) {
        return findLotEntities(false, maxResults, firstResult);
    }

    private List<Lot> findLotEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lot.class));
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

    /**
     *
     * @param id
     * @return
     */
    public Lot findLot(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lot.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param name
     * @param farm
     * @return
     * @throws Exception
     */
    public Lot findLot(String name,Farm farm) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Lot> query = em.createQuery("SELECT l FROM Lot l WHERE l.name = :name AND l.farm = :farm", Lot.class);
            query.setParameter("name", name);
            query.setParameter("farm", farm);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getLotCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lot> rt = cq.from(Lot.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Lot> findLotEntitiesForSelectedFarm(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Lot> query = em.createQuery("SELECT l FROM Lot l WHERE l.farm = :farm", Lot.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
