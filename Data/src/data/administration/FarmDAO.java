/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.administration;

import data.exceptions.NonexistentEntityException;
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
public class FarmDAO implements Serializable {

    public FarmDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Farm farm) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(farm);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Farm farm) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            farm = em.merge(farm);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = farm.getId();
                if (findFarm(id) == null) {
                    throw new NonexistentEntityException("The farm with id " + id + " no longer exists.");
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
            Farm farm;
            try {
                farm = em.getReference(Farm.class, id);
                farm.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The farm with id " + id + " no longer exists.", enfe);
            }
            em.remove(farm);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Farm> findFarmEntitiesForCurrentUser(Person user) {
        EntityManager em = getEntityManager();
        try {
            if (user.isSystemAdmin()) {
                return findFarmEntities();
            } else {
                TypedQuery<Farm> query = em.createQuery("SELECT f FROM Farm f WHERE f.owner = :owner", Farm.class);
                query.setParameter("owner", user);
                List<Farm> resultList = query.getResultList();
                if (resultList == null) {
                    resultList = new LinkedList<>();
                }
                List<Farm> findFarmEntities = new ContractDAO(emf).findFarmEntities(user);
                if (findFarmEntities != null && !findFarmEntities.isEmpty()) {
                    resultList.addAll(findFarmEntities);
                }
                return resultList;
            }
        } finally {
            em.close();
        }
    }

    public List<Farm> findFarmEntities() {
        return findFarmEntities(true, -1, -1);
    }

    public List<Farm> findFarmEntities(int maxResults, int firstResult) {
        return findFarmEntities(false, maxResults, firstResult);
    }

    private List<Farm> findFarmEntities(boolean all, int maxResults, int firstResult) {
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

    public Farm findFarm(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Farm.class, id);
        } finally {
            em.close();
        }
    }

    public int getFarmCount() {
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
