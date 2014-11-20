/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.produccion;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.produccion.LaborCultural;

/**
 *
 * @author fredy
 */
public class LaborCulturalDAO implements Serializable {

    public LaborCulturalDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LaborCultural laborCultural) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(laborCultural);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LaborCultural laborCultural) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            laborCultural = em.merge(laborCultural);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = laborCultural.getId();
                if (findLaborCultural(id) == null) {
                    throw new NonexistentEntityException("The laborCultural with id " + id + " no longer exists.");
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
            LaborCultural laborCultural;
            try {
                laborCultural = em.getReference(LaborCultural.class, id);
                laborCultural.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The laborCultural with id " + id + " no longer exists.", enfe);
            }
            em.remove(laborCultural);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LaborCultural> findLaborCulturalEntities() {
        return findLaborCulturalEntities(true, -1, -1);
    }

    public List<LaborCultural> findLaborCulturalEntities(int maxResults, int firstResult) {
        return findLaborCulturalEntities(false, maxResults, firstResult);
    }

    private List<LaborCultural> findLaborCulturalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LaborCultural.class));
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

    public LaborCultural findLaborCultural(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LaborCultural.class, id);
        } finally {
            em.close();
        }
    }

    public int getLaborCulturalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LaborCultural> rt = cq.from(LaborCultural.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
