/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.administracion;

import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.administration.Farm;
import model.administration.Person;

/**
 *
 * @author fredy
 */
public class FincaDAO implements Serializable {

    public FincaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Farm finca) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(finca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Farm finca) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            finca = em.merge(finca);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = finca.getId();
                if (findFinca(id) == null) {
                    throw new NonexistentEntityException("The finca with id " + id + " no longer exists.");
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
            Farm finca;
            try {
                finca = em.getReference(Farm.class, id);
                finca.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The finca with id " + id + " no longer exists.", enfe);
            }
            em.remove(finca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Farm> findFincaEntitiesForCurrentUser(Person user) {
        EntityManager em = getEntityManager();
        try {
            if (user.isSystemAdmin()) {
                return findFincaEntities();
            } else {
                TypedQuery<Farm> query = em.createQuery("SELECT f FROM Finca f WHERE f.propietario = :persona", Farm.class);
                query.setParameter("persona", user);
                List<Farm> resultList = query.getResultList();
                if (resultList == null) {
                    resultList = new LinkedList<>();
                }
                List<Farm> findFincaEntities = new ContratoDAO(emf).findFincaEntities(user);
                if (findFincaEntities != null && !findFincaEntities.isEmpty()) {
                    resultList.addAll(findFincaEntities);
                }
                return resultList;
            }
        } finally {
            em.close();
        }
    }

    public List<Farm> findFincaEntities() {
        return findFincaEntities(true, -1, -1);
    }

    public List<Farm> findFincaEntities(int maxResults, int firstResult) {
        return findFincaEntities(false, maxResults, firstResult);
    }

    private List<Farm> findFincaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Farm.class));
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

    public Farm findFinca(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Farm.class, id);
        } finally {
            em.close();
        }
    }

    public int getFincaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Farm> rt = cq.from(Farm.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
