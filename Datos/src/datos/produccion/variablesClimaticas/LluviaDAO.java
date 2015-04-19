/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.variablesClimaticas;

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
import model.weather.RainFall;

/**
 *
 * @author fredy
 */
public class LluviaDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public LluviaDAO(EntityManagerFactory emf) {
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
     * @param controlDeLluvias
     */
    public void create(RainFall controlDeLluvias) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(controlDeLluvias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param controlDeLluvias
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(RainFall controlDeLluvias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            controlDeLluvias = em.merge(controlDeLluvias);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = controlDeLluvias.getId();
                if (findLluvia(id) == null) {
                    throw new NonexistentEntityException("The controlDeLluvias with id " + id + " no longer exists.");
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
            RainFall controlDeLluvias;
            try {
                controlDeLluvias = em.getReference(RainFall.class, id);
                controlDeLluvias.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The controlDeLluvias with id " + id + " no longer exists.", enfe);
            }
            em.remove(controlDeLluvias);
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
    public List<RainFall> findLluviaEntities() {
        return findLluviaEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<RainFall> findLluviaEntities(int maxResults, int firstResult) {
        return findLluviaEntities(false, maxResults, firstResult);
    }

    private List<RainFall> findLluviaEntities(boolean all, int maxResults, int firstResult) {
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
    public RainFall findLluvia(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RainFall.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     *
     * @param fecha1
     * @return
     */
    public List<RainFall> findLluviaEntities(Date fecha1) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<RainFall> query = em.createQuery("SELECT t FROM Lluvia t WHERE t.fecha = :fecha1", RainFall.class);
            query.setParameter("fecha1", fecha1, TemporalType.DATE);
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
    public List<RainFall> findLluviaEntities(Date esteMes, Date siguienteMes) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<RainFall> query = em.createQuery("SELECT t FROM Lluvia t WHERE t.fecha BETWEEN :fecha1 AND :fecha2", RainFall.class);
            query.setParameter("fecha1", esteMes, TemporalType.DATE);
            query.setParameter("fecha2", siguienteMes, TemporalType.DATE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getLluviaCount() {
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
