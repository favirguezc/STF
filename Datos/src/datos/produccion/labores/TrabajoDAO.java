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
import model.administration.Farm;
import model.administration.Lot;
import model.administration.Person;
import model.work.Work;

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
    public void create(Work trabajo) {
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
    public void edit(Work trabajo) throws NonexistentEntityException, Exception {
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
            Work trabajo;
            try {
                trabajo = em.getReference(Work.class, id);
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
     * @param farm
     * @return
     */
    public List<Work> findTrabajoEntities(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Work> query = em.createQuery("SELECT a FROM Trabajo a WHERE a.modulo.lote.finca = :finca", Work.class);
            query.setParameter("finca", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Work> findTrabajoEntities() {
        return findTrabajoEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Work> findTrabajoEntities(int maxResults, int firstResult) {
        return findTrabajoEntities(false, maxResults, firstResult);
    }

    private List<Work> findTrabajoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Work.class));
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
    public Work findTrabajo(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Work.class, id);
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
            Root<Work> rt = cq.from(Work.class);
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
    public List<Work> findTrabajoEntities(Lot lote, Date inicio, Date fin) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Work> query = em.createQuery("SELECT t FROM Trabajo t WHERE t.modulo.lote = :lote AND t.fecha BETWEEN :inicio AND :fin", Work.class);
            query.setParameter("inicio", inicio, TemporalType.DATE);
            query.setParameter("fin", fin, TemporalType.DATE);
            query.setParameter("lote", lote);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Work> findTrabajoEntities(Farm finca, Person trabajador, Date inicio, Date fin) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Work> query = em.createQuery("SELECT t FROM Trabajo t WHERE t.operario = :operario AND t.modulo.lote.finca = :finca AND t.fecha BETWEEN :inicio AND :fin", Work.class);
            query.setParameter("inicio", inicio, TemporalType.DATE);
            query.setParameter("fin", fin, TemporalType.DATE);
            query.setParameter("operario", trabajador);
            query.setParameter("finca", finca);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
