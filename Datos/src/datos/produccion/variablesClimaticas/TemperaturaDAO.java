/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.variablesClimaticas;

import datos.exceptions.NonexistentEntityException;
import datos.exceptions.PreexistingEntityException;
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
public class TemperaturaDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public TemperaturaDAO(EntityManagerFactory emf) {
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
     * @param temperatura
     * @throws PreexistingEntityException
     * @throws Exception
     */
    public void create(Temperature temperatura) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(temperatura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTemperatura(temperatura.getId()) != null) {
                throw new PreexistingEntityException("Temperatura " + temperatura + " already exists.", ex);
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
     * @param temperatura
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Temperature temperatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            temperatura = em.merge(temperatura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = temperatura.getId();
                if (findTemperatura(id) == null) {
                    throw new NonexistentEntityException("The temperatura with id " + id + " no longer exists.");
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
            Temperature temperatura;
            try {
                temperatura = em.getReference(Temperature.class, id);
                temperatura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The temperatura with id " + id + " no longer exists.", enfe);
            }
            em.remove(temperatura);
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
    public List<Temperature> findTemperaturaEntities() {
        return findTemperaturaEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Temperature> findTemperaturaEntities(int maxResults, int firstResult) {
        return findTemperaturaEntities(false, maxResults, firstResult);
    }

    private List<Temperature> findTemperaturaEntities(boolean all, int maxResults, int firstResult) {
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
    public Temperature findTemperatura(Long id) {
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
    public Temperature findTemperatura(Date date) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Temperature> query = em.createQuery("SELECT t FROM Temperatura t WHERE t.fecha = :fecha", Temperature.class);
            query.setParameter("fecha", date, TemporalType.DATE);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getTemperaturaCount() {
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
     * @param fecha1
     * @param fecha2
     * @param tipo
     * @return
     */
    public List<Temperature> findTemperaturaEntities(Date fecha1, Date fecha2, int tipo) {
        EntityManager em = getEntityManager();
        String stringQuery = "SELECT t FROM Temperatura t WHERE t.fecha BETWEEN :fecha1 AND :fecha2";
        if (tipo == 1) {
            stringQuery += " AND FUNC('HOUR',t.hora) IN (0,1,2,3,4,5,18,19,20,21,22,23)";
        }else if (tipo == 2) {
            stringQuery += " AND FUNC('HOUR',t.hora) NOT IN (0,1,2,3,4,5,18,19,20,21,22,23)";
        }
        try {
            TypedQuery<Temperature> query = em.createQuery(stringQuery, Temperature.class
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

    /**
     *
     * @param fecha1
     * @param hora
     * @return
     */
    public List<Temperature> findTemperaturaEntities(Date fecha1, int hora) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Temperature> query = em.createQuery("SELECT t FROM Temperatura t WHERE t.fecha = :fecha1 AND FUNC('HOUR',t.hora) = :hora", Temperature.class);
            query.setParameter("fecha1", fecha1, TemporalType.DATE);
            query.setParameter("hora", hora);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
