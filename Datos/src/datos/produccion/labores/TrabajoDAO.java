/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.labores;

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
import modelo.produccion.administracion.Lote;
import modelo.produccion.labores.Trabajo;

/**
 *
 * @author fredy
 */
public class TrabajoDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public TrabajoDAO(EntityManagerFactory emf) {
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
     * @param trabajo
     */
    public void create(Trabajo trabajo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(trabajo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param trabajo
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Trabajo trabajo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            trabajo = em.merge(trabajo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = trabajo.getId();
                if (findTrabajo(id) == null) {
                    throw new NonexistentEntityException("The trabajo with id " + id + " no longer exists.");
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
            Trabajo trabajo;
            try {
                trabajo = em.getReference(Trabajo.class, id);
                trabajo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trabajo with id " + id + " no longer exists.", enfe);
            }
            em.remove(trabajo);
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
    public List<Trabajo> findTrabajoEntities() {
        return findTrabajoEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Trabajo> findTrabajoEntities(int maxResults, int firstResult) {
        return findTrabajoEntities(false, maxResults, firstResult);
    }

    private List<Trabajo> findTrabajoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trabajo.class));
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
    public Trabajo findTrabajo(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trabajo.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getTrabajoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trabajo> rt = cq.from(Trabajo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param lote
     * @param inicio
     * @param fin
     * @return
     */
    public List<Trabajo> findTrabajoEntities(Lote lote, Date inicio, Date fin) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Trabajo> query = em.createQuery("SELECT t FROM Trabajo t WHERE t.modulo.lote = :lote AND t.fecha BETWEEN :inicio AND :fin", Trabajo.class);
            query.setParameter("inicio", inicio, TemporalType.DATE);
            query.setParameter("fin", fin, TemporalType.DATE);
            query.setParameter("lote", lote);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}