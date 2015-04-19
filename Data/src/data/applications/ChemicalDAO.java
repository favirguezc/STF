/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.applications;

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
import model.applications.Chemical;

/**
 *
 * @author fredy
 */
public class ChemicalDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public ChemicalDAO(EntityManagerFactory emf) {
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
     * @param chemical
     */
    public void create(Chemical chemical) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(chemical);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param chemical
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Chemical chemical) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            chemical = em.merge(chemical);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = chemical.getId();
                if (findChemical(id) == null) {
                    throw new NonexistentEntityException("The chemical with id " + id + " no longer exists.");
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
            Chemical chemical;
            try {
                chemical = em.getReference(Chemical.class, id);
                chemical.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chemical with id " + id + " no longer exists.", enfe);
            }
            em.remove(chemical);
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
    public List<Chemical> findChemicalEntities() {
        return findChemicalEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Chemical> findChemicalEntities(int maxResults, int firstResult) {
        return findChemicalEntities(false, maxResults, firstResult);
    }

    private List<Chemical> findChemicalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Chemical.class));
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
    public Chemical findChemical(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Chemical.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getChemicalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Chemical> rt = cq.from(Chemical.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Chemical findChemical(String name)throws Exception{
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Chemical> query = em.createQuery("SELECT t FROM Chemical t WHERE t.name = :name", Chemical.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
}
