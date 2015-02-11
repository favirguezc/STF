/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.monitoreo;

import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.produccion.monitoreo.TrampaDeInsectos;

/**
 *
 * @author fredy
 */
public class TrampaDeInsectosDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public TrampaDeInsectosDAO(EntityManagerFactory emf) {
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
     * @param trampaDeInsectos
     */
    public void create(TrampaDeInsectos trampaDeInsectos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(trampaDeInsectos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param trampaDeInsectos
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(TrampaDeInsectos trampaDeInsectos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            trampaDeInsectos = em.merge(trampaDeInsectos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = trampaDeInsectos.getId();
                if (findTrampaDeInsectos(id) == null) {
                    throw new NonexistentEntityException("The trampaDeInsectos with id " + id + " no longer exists.");
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
            TrampaDeInsectos trampaDeInsectos;
            try {
                trampaDeInsectos = em.getReference(TrampaDeInsectos.class, id);
                trampaDeInsectos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trampaDeInsectos with id " + id + " no longer exists.", enfe);
            }
            em.remove(trampaDeInsectos);
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
    public List<TrampaDeInsectos> findTrampaDeInsectosEntities() {
        return findTrampaDeInsectosEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<TrampaDeInsectos> findTrampaDeInsectosEntities(int maxResults, int firstResult) {
        return findTrampaDeInsectosEntities(false, maxResults, firstResult);
    }

    private List<TrampaDeInsectos> findTrampaDeInsectosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TrampaDeInsectos.class));
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
    public TrampaDeInsectos findTrampaDeInsectos(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TrampaDeInsectos.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getTrampaDeInsectosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TrampaDeInsectos> rt = cq.from(TrampaDeInsectos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param fecha1
     * @param fecha2
     * @return
     */
    public List<TrampaDeInsectos> findTrampaDeInsectosEntities(Date fecha1, Date fecha2) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<TrampaDeInsectos> query = em.createQuery("SELECT t FROM TrampaDeInsectos t WHERE t.fecha BETWEEN :fecha1 AND :fecha2", TrampaDeInsectos.class
            );
            query.setParameter(
                    "fecha1", fecha1, TemporalType.DATE);
            query.setParameter(
                    "fecha2", fecha2, TemporalType.DATE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
