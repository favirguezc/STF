/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.cosecha;

import datos.exceptions.NonexistentEntityException;
import datos.exceptions.PreexistingEntityException;
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
public class VariedadDAO implements Serializable {

    public VariedadDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Variety variedad) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(variedad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVariedad(variedad.getId()) != null) {
                throw new PreexistingEntityException("Variedad " + variedad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Variety variedad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            variedad = em.merge(variedad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = variedad.getId();
                if (findVariedad(id) == null) {
                    throw new NonexistentEntityException("The variedad with id " + id + " no longer exists.");
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
            Variety variedad;
            try {
                variedad = em.getReference(Variety.class, id);
                variedad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The variedad with id " + id + " no longer exists.", enfe);
            }
            em.remove(variedad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Variety> findVariedadEntities() {
        return findVariedadEntities(true, -1, -1);
    }

    public List<Variety> findVariedadEntities(int maxResults, int firstResult) {
        return findVariedadEntities(false, maxResults, firstResult);
    }

    private List<Variety> findVariedadEntities(boolean all, int maxResults, int firstResult) {
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

    public Variety findVariedad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Variety.class, id);
        } finally {
            em.close();
        }
    }

    public int getVariedadCount() {
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
