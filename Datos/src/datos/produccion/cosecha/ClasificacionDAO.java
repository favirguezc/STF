/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.cosecha;

import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.produccion.cosecha.Clasificacion;

/**
 *
 * @author fredy
 */
public class ClasificacionDAO implements Serializable {

    public ClasificacionDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clasificacion clasificacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(clasificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clasificacion clasificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clasificacion = em.merge(clasificacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = clasificacion.getId();
                if (findClasificacion(id) == null) {
                    throw new NonexistentEntityException("The clasificacion with id " + id + " no longer exists.");
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
            Clasificacion clasificacion;
            try {
                clasificacion = em.getReference(Clasificacion.class, id);
                clasificacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clasificacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(clasificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clasificacion> findClasificacionEntities() {
        return findClasificacionEntities(true, -1, -1);
    }

    public List<Clasificacion> findClasificacionEntities(int maxResults, int firstResult) {
        return findClasificacionEntities(false, maxResults, firstResult);
    }

    private List<Clasificacion> findClasificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clasificacion.class));
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

    public Clasificacion findClasificacion(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clasificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getClasificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clasificacion> rt = cq.from(Clasificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
