/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.produccion.MonitoreoDePlagas;

/**
 *
 * @author fredy
 */
public class MonitoreoDePlagasJpaController implements Serializable {

    public MonitoreoDePlagasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MonitoreoDePlagas monitoreoDePlagas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(monitoreoDePlagas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MonitoreoDePlagas monitoreoDePlagas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            monitoreoDePlagas = em.merge(monitoreoDePlagas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = monitoreoDePlagas.getId();
                if (findMonitoreoDePlagas(id) == null) {
                    throw new NonexistentEntityException("The monitoreoDePlagas with id " + id + " no longer exists.");
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
            MonitoreoDePlagas monitoreoDePlagas;
            try {
                monitoreoDePlagas = em.getReference(MonitoreoDePlagas.class, id);
                monitoreoDePlagas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The monitoreoDePlagas with id " + id + " no longer exists.", enfe);
            }
            em.remove(monitoreoDePlagas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MonitoreoDePlagas> findMonitoreoDePlagasEntities() {
        return findMonitoreoDePlagasEntities(true, -1, -1);
    }

    public List<MonitoreoDePlagas> findMonitoreoDePlagasEntities(int maxResults, int firstResult) {
        return findMonitoreoDePlagasEntities(false, maxResults, firstResult);
    }

    private List<MonitoreoDePlagas> findMonitoreoDePlagasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MonitoreoDePlagas.class));
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

    public MonitoreoDePlagas findMonitoreoDePlagas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MonitoreoDePlagas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonitoreoDePlagasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MonitoreoDePlagas> rt = cq.from(MonitoreoDePlagas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
