/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.labores;

import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.produccion.labores.Labor;

/**
 *
 * @author fredy
 */
public class LaborDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public LaborDAO(EntityManagerFactory emf) {
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
     * @param labor
     */
    public void create(Labor labor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(labor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param labor
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Labor labor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            labor = em.merge(labor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = labor.getId();
                if (findLabor(id) == null) {
                    throw new NonexistentEntityException("The labor with id " + id + " no longer exists.");
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
            Labor labor;
            try {
                labor = em.getReference(Labor.class, id);
                labor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The labor with id " + id + " no longer exists.", enfe);
            }
            em.remove(labor);
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
    public List<Labor> findLaborEntities() {
        return findLaborEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Labor> findLaborEntities(int maxResults, int firstResult) {
        return findLaborEntities(false, maxResults, firstResult);
    }

    private List<Labor> findLaborEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Labor.class));
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
    public Labor findLabor(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Labor.class, id);
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
    public Labor findLabor(String name) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Labor> query = em.createQuery("SELECT t FROM Labor t WHERE t.nombre = :nombre", Labor.class);
            query.setParameter("nombre", name);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getLaborCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Labor> rt = cq.from(Labor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}