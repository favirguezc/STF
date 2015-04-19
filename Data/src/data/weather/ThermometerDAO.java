/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.weather;

import data.exceptions.NonexistentEntityException;
import data.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.weather.Thermometer;

/**
 *
 * @author fredy
 */
public class ThermometerDAO {

    private EntityManagerFactory emf = null;

    /**
     *
     * @param emf
     */
    public ThermometerDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     *
     * @return
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     *
     * @param thermometer
     * @throws PreexistingEntityException
     * @throws Exception
     */
    public void create(Thermometer thermometer) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(thermometer);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findThermometer(thermometer.getId()) != null) {
                throw new PreexistingEntityException("Thermometer " + thermometer + " already exists.", ex);
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
     * @param thermometer
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Thermometer thermometer) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            thermometer = em.merge(thermometer);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = thermometer.getId();
                if (findThermometer(id) == null) {
                    throw new NonexistentEntityException("The thermometer with id " + id + " no longer exists.");
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
            Thermometer thermometer;
            try {
                thermometer = em.getReference(Thermometer.class, id);
                thermometer.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The thermometer with id " + id + " no longer exists.", enfe);
            }
            em.remove(thermometer);
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
    public List<Thermometer> findThermometerEntities() {
        return findThermometerEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Thermometer> findThermometerEntities(int maxResults, int firstResult) {
        return findThermometerEntities(false, maxResults, firstResult);
    }

    private List<Thermometer> findThermometerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Thermometer.class));
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
     * @param serialNumber
     * @return
     */
    public Thermometer findThermometer(Long serialNumber) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Thermometer> query = em.createQuery("SELECT t FROM Thermometer t WHERE t.serialNumber = :nds", Thermometer.class);
            query.setParameter("nds",serialNumber);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getThermometerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Thermometer> rt = cq.from(Thermometer.class
            );
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
