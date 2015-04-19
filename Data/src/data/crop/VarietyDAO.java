/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.crop;

import data.exceptions.NonexistentEntityException;
import data.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.crop.Variety;

/**
 *
 * @author fredy
 */
public class VarietyDAO implements Serializable {

    public VarietyDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Variety variety) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(variety);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVariety(variety.getId()) != null) {
                throw new PreexistingEntityException("Variety " + variety + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Variety variety) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            variety = em.merge(variety);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = variety.getId();
                if (findVariety(id) == null) {
                    throw new NonexistentEntityException("The variety with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Variety variety;
            try {
                variety = em.getReference(Variety.class, id);
                variety.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The variety with id " + id + " no longer exists.", enfe);
            }
            em.remove(variety);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Variety> findVarietyEntities() {
        return findVarietyEntities(true, -1, -1);
    }

    public List<Variety> findVarietyEntities(int maxResults, int firstResult) {
        return findVarietyEntities(false, maxResults, firstResult);
    }

    private List<Variety> findVarietyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Variety.class));
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

    public Variety findVariety(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Variety.class, id);
        } finally {
            em.close();
        }
    }

    public int getVarietyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Variety> rt = cq.from(Variety.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
