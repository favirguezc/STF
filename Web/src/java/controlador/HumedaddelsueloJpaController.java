/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Humedaddelsuelo;

/**
 *
 * @author fredy
 */
public class HumedaddelsueloJpaController implements Serializable {

    public HumedaddelsueloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Humedaddelsuelo humedaddelsuelo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(humedaddelsuelo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHumedaddelsuelo(humedaddelsuelo.getId()) != null) {
                throw new PreexistingEntityException("Humedaddelsuelo " + humedaddelsuelo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Humedaddelsuelo humedaddelsuelo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            humedaddelsuelo = em.merge(humedaddelsuelo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = humedaddelsuelo.getId();
                if (findHumedaddelsuelo(id) == null) {
                    throw new NonexistentEntityException("The humedaddelsuelo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Humedaddelsuelo humedaddelsuelo;
            try {
                humedaddelsuelo = em.getReference(Humedaddelsuelo.class, id);
                humedaddelsuelo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The humedaddelsuelo with id " + id + " no longer exists.", enfe);
            }
            em.remove(humedaddelsuelo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Humedaddelsuelo> findHumedaddelsueloEntities() {
        return findHumedaddelsueloEntities(true, -1, -1);
    }

    public List<Humedaddelsuelo> findHumedaddelsueloEntities(int maxResults, int firstResult) {
        return findHumedaddelsueloEntities(false, maxResults, firstResult);
    }

    private List<Humedaddelsuelo> findHumedaddelsueloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Humedaddelsuelo.class));
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

    public Humedaddelsuelo findHumedaddelsuelo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Humedaddelsuelo.class, id);
        } finally {
            em.close();
        }
    }

    public int getHumedaddelsueloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Humedaddelsuelo> rt = cq.from(Humedaddelsuelo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
