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
import modelo.Monitoreodeplagas;

/**
 *
 * @author fredy
 */
public class MonitoreodeplagasJpaController implements Serializable {

    public MonitoreodeplagasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Monitoreodeplagas monitoreodeplagas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(monitoreodeplagas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMonitoreodeplagas(monitoreodeplagas.getId()) != null) {
                throw new PreexistingEntityException("Monitoreodeplagas " + monitoreodeplagas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Monitoreodeplagas monitoreodeplagas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            monitoreodeplagas = em.merge(monitoreodeplagas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = monitoreodeplagas.getId();
                if (findMonitoreodeplagas(id) == null) {
                    throw new NonexistentEntityException("The monitoreodeplagas with id " + id + " no longer exists.");
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
            Monitoreodeplagas monitoreodeplagas;
            try {
                monitoreodeplagas = em.getReference(Monitoreodeplagas.class, id);
                monitoreodeplagas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The monitoreodeplagas with id " + id + " no longer exists.", enfe);
            }
            em.remove(monitoreodeplagas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Monitoreodeplagas> findMonitoreodeplagasEntities() {
        return findMonitoreodeplagasEntities(true, -1, -1);
    }

    public List<Monitoreodeplagas> findMonitoreodeplagasEntities(int maxResults, int firstResult) {
        return findMonitoreodeplagasEntities(false, maxResults, firstResult);
    }

    private List<Monitoreodeplagas> findMonitoreodeplagasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Monitoreodeplagas.class));
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

    public Monitoreodeplagas findMonitoreodeplagas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Monitoreodeplagas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonitoreodeplagasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Monitoreodeplagas> rt = cq.from(Monitoreodeplagas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
