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
import modelo.variablesClimaticas.HumedadDelSuelo;

/**
 *
 * @author fredy
 */
public class HumedadDelSueloJpaController implements Serializable {

    public HumedadDelSueloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HumedadDelSuelo humedadDelSuelo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(humedadDelSuelo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HumedadDelSuelo humedadDelSuelo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            humedadDelSuelo = em.merge(humedadDelSuelo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = humedadDelSuelo.getId();
                if (findHumedadDelSuelo(id) == null) {
                    throw new NonexistentEntityException("The humedadDelSuelo with id " + id + " no longer exists.");
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
            HumedadDelSuelo humedadDelSuelo;
            try {
                humedadDelSuelo = em.getReference(HumedadDelSuelo.class, id);
                humedadDelSuelo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The humedadDelSuelo with id " + id + " no longer exists.", enfe);
            }
            em.remove(humedadDelSuelo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HumedadDelSuelo> findHumedadDelSueloEntities() {
        return findHumedadDelSueloEntities(true, -1, -1);
    }

    public List<HumedadDelSuelo> findHumedadDelSueloEntities(int maxResults, int firstResult) {
        return findHumedadDelSueloEntities(false, maxResults, firstResult);
    }

    private List<HumedadDelSuelo> findHumedadDelSueloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HumedadDelSuelo.class));
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

    public HumedadDelSuelo findHumedadDelSuelo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HumedadDelSuelo.class, id);
        } finally {
            em.close();
        }
    }

    public int getHumedadDelSueloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HumedadDelSuelo> rt = cq.from(HumedadDelSuelo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
