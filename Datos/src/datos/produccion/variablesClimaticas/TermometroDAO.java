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
import modelo.produccion.variablesClimaticas.Termometro;

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
    public void create(Termometro termometro) throws PreexistingEntityException, Exception {
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
    public void edit(Termometro termometro) throws NonexistentEntityException, Exception {
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
            Termometro termometro;
            try {
                termometro = em.getReference(Termometro.class, id);
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
    public List<Termometro> findTermometroEntities() {
        return findTermometroEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Termometro> findTermometroEntities(int maxResults, int firstResult) {
        return findTermometroEntities(false, maxResults, firstResult);
    }

    private List<Termometro> findTermometroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Termometro.class));
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
    public Termometro findTermometro(Long nds) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Termometro> query = em.createQuery("SELECT t FROM Termometro t WHERE t.numeroDeSerie = :nds", Termometro.class);
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
            Root<Termometro> rt = cq.from(Termometro.class
            );
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
