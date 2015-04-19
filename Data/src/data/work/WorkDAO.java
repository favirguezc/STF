/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.work;

import data.exceptions.NonexistentEntityException;
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
public class WorkDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public WorkDAO(EntityManagerFactory emf) {
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
     * @param work
     */
    public void create(Work work) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(work);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param work
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Work work) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            work = em.merge(work);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = work.getId();
                if (findWork(id) == null) {
                    throw new NonexistentEntityException("The work with id " + id + " no longer exists.");
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
            Work work;
            try {
                work = em.getReference(Work.class, id);
                work.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The work with id " + id + " no longer exists.", enfe);
            }
            em.remove(work);
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
    public List<Work> findWorkEntities(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Work> query = em.createQuery("SELECT a FROM Work a WHERE a.cultivation.moduleObject.lot.farm = :farm", Work.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Work> findWorkEntities() {
        return findWorkEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Work> findWorkEntities(int maxResults, int firstResult) {
        return findWorkEntities(false, maxResults, firstResult);
    }

    private List<Work> findWorkEntities(boolean all, int maxResults, int firstResult) {
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
    public Work findWork(long id) {
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
    public int getWorkCount() {
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
     * @param lot
     * @param start
     * @param end
     * @return
     */
    public List<Work> findWorkEntities(Lot lot, Date start, Date end) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Work> query = em.createQuery("SELECT t FROM Work t WHERE t.cultivation.moduleObject.lot = :lot AND t.workDate BETWEEN :start AND :end", Work.class);
            query.setParameter("start", start, TemporalType.DATE);
            query.setParameter("end", end, TemporalType.DATE);
            query.setParameter("lot", lot);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Work> findWorkEntities(Farm farm, Person trabajador, Date start, Date end) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Work> query = em.createQuery("SELECT t FROM Work t WHERE t.worker = :worker AND t.cultivation.moduleObject.lot.farm = :farm AND t.workDate BETWEEN :start AND :end", Work.class);
            query.setParameter("start", start, TemporalType.DATE);
            query.setParameter("end", end, TemporalType.DATE);
            query.setParameter("worker", trabajador);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
