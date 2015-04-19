/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.administracion;

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
import model.administration.Cultivation;

/**
 *
 * @author fredy
 */
public class CultivoDAO implements Serializable {

    public CultivoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cultivation cultivo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cultivo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCultivo(cultivo.getId()) != null) {
                throw new PreexistingEntityException("Cultivo " + cultivo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cultivation cultivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cultivo = em.merge(cultivo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cultivo.getId();
                if (findCultivo(id) == null) {
                    throw new NonexistentEntityException("The cultivo with id " + id + " no longer exists.");
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
            Cultivation cultivo;
            try {
                cultivo = em.getReference(Cultivation.class, id);
                cultivo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cultivo with id " + id + " no longer exists.", enfe);
            }
            em.remove(cultivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cultivation> findCultivoEntities() {
        return findCultivoEntities(true, -1, -1);
    }

    public List<Cultivation> findCultivoEntities(int maxResults, int firstResult) {
        return findCultivoEntities(false, maxResults, firstResult);
    }

    private List<Cultivation> findCultivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cultivation.class));
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

    public Cultivation findCultivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cultivation.class, id);
        } finally {
            em.close();
        }
    }

    public int getCultivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cultivation> rt = cq.from(Cultivation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
