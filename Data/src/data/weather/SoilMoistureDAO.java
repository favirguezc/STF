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
import model.weather.SoilMoisture;

/**
 *
 * @author fredy
 */
public class SoilMoistureDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public SoilMoistureDAO(EntityManagerFactory emf) {
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
     * @param soilMoisture
     */
    public void create(SoilMoisture soilMoisture) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(soilMoisture);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param soilMoisture
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(SoilMoisture soilMoisture) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            soilMoisture = em.merge(soilMoisture);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = soilMoisture.getId();
                if (findSoilMoisture(id) == null) {
                    throw new NonexistentEntityException("The soilMoisture with id " + id + " no longer exists.");
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
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SoilMoisture soilMoisture;
            try {
                soilMoisture = em.getReference(SoilMoisture.class, id);
                soilMoisture.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The soilMoisture with id " + id + " no longer exists.", enfe);
            }
            em.remove(soilMoisture);
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
    public List<SoilMoisture> findSoilMoistureEntities() {
        return findSoilMoistureEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<SoilMoisture> findSoilMoistureEntities(int maxResults, int firstResult) {
        return findSoilMoistureEntities(false, maxResults, firstResult);
    }

    private List<SoilMoisture> findSoilMoistureEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SoilMoisture.class));
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
     * @param date
     * @return
     */
    public List<SoilMoisture> findSoilMoistureEntities(Date date) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<SoilMoisture> query = em.createQuery("SELECT t FROM SoilMoisture t WHERE t.measurementDate = :date1", SoilMoisture.class);
            query.setParameter("date1", date, TemporalType.DATE);
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
    public List<SoilMoisture> findSoilMoistureEntities(Date esteMes, Date siguienteMes) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<SoilMoisture> query = em.createQuery("SELECT h FROM SoilMoisture h WHERE h.measurementDate BETWEEN :date1 AND :date2", SoilMoisture.class);
            query.setParameter("date1", esteMes, TemporalType.DATE);
            query.setParameter("date2", siguienteMes, TemporalType.DATE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public SoilMoisture findSoilMoisture(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SoilMoisture.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getSoilMoistureCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SoilMoisture> rt = cq.from(SoilMoisture.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
