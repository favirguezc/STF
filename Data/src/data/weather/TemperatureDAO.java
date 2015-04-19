/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.weather;

import data.exceptions.NonexistentEntityException;
import data.exceptions.PreexistingEntityException;
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
import model.weather.Temperature;

/**
 *
 * @author fredy
 */
public class TemperatureDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public TemperatureDAO(EntityManagerFactory emf) {
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
     * @param temperature
     * @throws PreexistingEntityException
     * @throws Exception
     */
    public void create(Temperature temperature) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(temperature);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTemperature(temperature.getId()) != null) {
                throw new PreexistingEntityException("Temperature " + temperature + " already exists.", ex);
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
     * @param temperature
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Temperature temperature) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            temperature = em.merge(temperature);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = temperature.getId();
                if (findTemperature(id) == null) {
                    throw new NonexistentEntityException("The temperature with id " + id + " no longer exists.");
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
            Temperature temperature;
            try {
                temperature = em.getReference(Temperature.class, id);
                temperature.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The temperature with id " + id + " no longer exists.", enfe);
            }
            em.remove(temperature);
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
    public List<Temperature> findTemperatureEntities() {
        return findTemperatureEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Temperature> findTemperatureEntities(int maxResults, int firstResult) {
        return findTemperatureEntities(false, maxResults, firstResult);
    }

    private List<Temperature> findTemperatureEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Temperature.class));
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
    public Temperature findTemperature(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Temperature.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param date
     * @return
     * @throws Exception
     */
    public Temperature findTemperature(Date date) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Temperature> query = em.createQuery("SELECT t FROM Temperature t WHERE t.measurementDate = :date", Temperature.class);
            query.setParameter("date", date, TemporalType.DATE);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getTemperatureCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Temperature> rt = cq.from(Temperature.class
            );
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param date1
     * @param date2
     * @param type
     * @return
     */
    public List<Temperature> findTemperatureEntities(Date date1, Date date2, int type) {
        EntityManager em = getEntityManager();
        String stringQuery = "SELECT t FROM Temperature t WHERE t.measurementDate BETWEEN :date1 AND :date2";
        if (type == 1) {
            stringQuery += " AND FUNC('HOUR',t.measurementTime) IN (0,1,2,3,4,5,18,19,20,21,22,23)";
        }else if (type == 2) {
            stringQuery += " AND FUNC('HOUR',t.measurementTime) NOT IN (0,1,2,3,4,5,18,19,20,21,22,23)";
        }
        try {
            TypedQuery<Temperature> query = em.createQuery(stringQuery, Temperature.class
            );
            query.setParameter(
                    "date1", date1, TemporalType.DATE);
            query.setParameter(
                    "date2", date2, TemporalType.DATE);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param date1
     * @param hour
     * @return
     */
    public List<Temperature> findTemperatureEntities(Date date1, int hour) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Temperature> query = em.createQuery("SELECT t FROM Temperature t WHERE t.measurementDate = :date1 AND FUNC('HOUR',t.measurementTime) = :hour", Temperature.class);
            query.setParameter("date1", date1, TemporalType.DATE);
            query.setParameter("hour", hour);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
