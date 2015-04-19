/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.variablesClimaticas;

import datos.exceptions.NonexistentEntityException;
import datos.exceptions.PreexistingEntityException;
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
public class TermometroDAO {

    private EntityManagerFactory emf = null;

    /**
     *
     * @param emf
     */
    public TermometroDAO(EntityManagerFactory emf) {
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
     * @param termometro
     * @throws PreexistingEntityException
     * @throws Exception
     */
    public void create(Thermometer termometro) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(termometro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTermometro(termometro.getId()) != null) {
                throw new PreexistingEntityException("Termometro " + termometro + " already exists.", ex);
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
     * @param termometro
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Thermometer termometro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            termometro = em.merge(termometro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = termometro.getId();
                if (findTermometro(id) == null) {
                    throw new NonexistentEntityException("The termometro with id " + id + " no longer exists.");
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
            Thermometer termometro;
            try {
                termometro = em.getReference(Thermometer.class, id);
                termometro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The termometro with id " + id + " no longer exists.", enfe);
            }
            em.remove(termometro);
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
    public List<Thermometer> findTermometroEntities() {
        return findTermometroEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Thermometer> findTermometroEntities(int maxResults, int firstResult) {
        return findTermometroEntities(false, maxResults, firstResult);
    }

    private List<Thermometer> findTermometroEntities(boolean all, int maxResults, int firstResult) {
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
     * @param nds
     * @return
     */
    public Thermometer findTermometro(Long nds) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Thermometer> query = em.createQuery("SELECT t FROM Termometro t WHERE t.numeroDeSerie = :nds", Thermometer.class);
            query.setParameter("nds",nds);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getTermometroCount() {
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
