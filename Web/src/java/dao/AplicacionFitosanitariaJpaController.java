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
import modelo.produccion.AplicacionFitosanitaria;

/**
 *
 * @author fredy
 */
public class AplicacionFitosanitariaJpaController implements Serializable {

    public AplicacionFitosanitariaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AplicacionFitosanitaria aplicacionFitosanitaria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(aplicacionFitosanitaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AplicacionFitosanitaria aplicacionFitosanitaria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            aplicacionFitosanitaria = em.merge(aplicacionFitosanitaria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = aplicacionFitosanitaria.getId();
                if (findAplicacionFitosanitaria(id) == null) {
                    throw new NonexistentEntityException("The aplicacionFitosanitaria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AplicacionFitosanitaria aplicacionFitosanitaria;
            try {
                aplicacionFitosanitaria = em.getReference(AplicacionFitosanitaria.class, id);
                aplicacionFitosanitaria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aplicacionFitosanitaria with id " + id + " no longer exists.", enfe);
            }
            em.remove(aplicacionFitosanitaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AplicacionFitosanitaria> findAplicacionFitosanitariaEntities() {
        return findAplicacionFitosanitariaEntities(true, -1, -1);
    }

    public List<AplicacionFitosanitaria> findAplicacionFitosanitariaEntities(int maxResults, int firstResult) {
        return findAplicacionFitosanitariaEntities(false, maxResults, firstResult);
    }

    private List<AplicacionFitosanitaria> findAplicacionFitosanitariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AplicacionFitosanitaria.class));
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

    public AplicacionFitosanitaria findAplicacionFitosanitaria(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AplicacionFitosanitaria.class, id);
        } finally {
            em.close();
        }
    }

    public int getAplicacionFitosanitariaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AplicacionFitosanitaria> rt = cq.from(AplicacionFitosanitaria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
