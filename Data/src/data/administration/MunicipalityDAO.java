/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.administration;

import data.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.administration.Department;
import model.administration.Municipality;

/**
 *
 * @author fredy
 */
public class MunicipalityDAO implements Serializable {

    public MunicipalityDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipality municipality) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(municipality);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipality municipality) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            municipality = em.merge(municipality);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = municipality.getId();
                if (findMunicipality(id) == null) {
                    throw new NonexistentEntityException("The municipality with id " + id + " no longer exists.");
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
            Municipality municipality;
            try {
                municipality = em.getReference(Municipality.class, id);
                municipality.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipality with id " + id + " no longer exists.", enfe);
            }
            em.remove(municipality);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipality> findMunicipalityEntities() {
        return findMunicipalityEntities(true, -1, -1);
    }

    public List<Municipality> findMunicipalityEntities(int maxResults, int firstResult) {
        return findMunicipalityEntities(false, maxResults, firstResult);
    }

    private List<Municipality> findMunicipalityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipality.class));
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

    public List<Municipality> findMunicipalityEntities(Department department) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Municipality> query = em.createQuery("SELECT m FROM Municipality m WHERE m.department = :department", Municipality.class);
            query.setParameter("department", department);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Municipality findMunicipality(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipality.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipalityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipality> rt = cq.from(Municipality.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
