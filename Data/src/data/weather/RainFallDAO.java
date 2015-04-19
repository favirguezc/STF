/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.weather;

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
import model.weather.RainFall;

/**
 *
 * @author fredy
 */
public class RainFallDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public RainFallDAO(EntityManagerFactory emf) {
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
     * @param controlDeRainFalls
     */
    public void create(RainFall controlDeRainFalls) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(controlDeRainFalls);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param controlDeRainFalls
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(RainFall controlDeRainFalls) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            controlDeRainFalls = em.merge(controlDeRainFalls);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = controlDeRainFalls.getId();
                if (findRainFall(id) == null) {
                    throw new NonexistentEntityException("The controlDeRainFalls with id " + id + " no longer exists.");
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
            RainFall controlDeRainFalls;
            try {
                controlDeRainFalls = em.getReference(RainFall.class, id);
                controlDeRainFalls.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The controlDeRainFalls with id " + id + " no longer exists.", enfe);
            }
            em.remove(controlDeRainFalls);
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
    public List<RainFall> findRainFallEntities() {
        return findRainFallEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<RainFall> findRainFallEntities(int maxResults, int firstResult) {
        return findRainFallEntities(false, maxResults, firstResult);
    }

    private List<RainFall> findRainFallEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RainFall.class));
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
    public RainFall findRainFall(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RainFall.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     *
     * @param date1
     * @return
     */
    public List<RainFall> findRainFallEntities(Date date1) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<RainFall> query = em.createQuery("SELECT t FROM RainFall t WHERE t.rainDate = :date1", RainFall.class);
            query.setParameter("date1", date1, TemporalType.DATE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     *
     * @param esteMes
     * @param siguienteMes
     * @return
     */
    public List<RainFall> findRainFallEntities(Date esteMes, Date siguienteMes) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<RainFall> query = em.createQuery("SELECT t FROM RainFall t WHERE t.rainDate BETWEEN :date1 AND :date2", RainFall.class);
            query.setParameter("date1", esteMes, TemporalType.DATE);
            query.setParameter("date2", siguienteMes, TemporalType.DATE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getRainFallCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RainFall> rt = cq.from(RainFall.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
