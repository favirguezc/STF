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
import model.administration.Farm;
import model.administration.Lot;
import model.administration.ModuleClass;

/**
 *
 * @author fredy
 */
public class ModuleDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public ModuleDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    /**
     *
     * @return
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     *
     * @param module
     */
    public void create(ModuleClass module) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(module);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param module
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(ModuleClass module) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            module = em.merge(module);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = module.getId();
                if (findModule(id) == null) {
                    throw new NonexistentEntityException("The module with id " + id + " no longer exists.");
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
    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ModuleClass module;
            try {
                module = em.getReference(ModuleClass.class, id);
                module.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The module with id " + id + " no longer exists.", enfe);
            }
            em.remove(module);
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
    public List<ModuleClass> findModuleEntities() {
        return findModuleEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<ModuleClass> findModuleEntities(int maxResults, int firstResult) {
        return findModuleEntities(false, maxResults, firstResult);
    }

    private List<ModuleClass> findModuleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ModuleClass.class));
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
     * @param id
     * @return
     */
    public ModuleClass findModule(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ModuleClass.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param name
     * @param lot
     * @return
     * @throws Exception
     */
    public ModuleClass findModule(String name, Lot lot) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ModuleClass> query = em.createQuery("SELECT m FROM ModuleClass m WHERE m.name = :name AND m.lot = :lot", ModuleClass.class);
            query.setParameter("name", name);
            query.setParameter("lot", lot);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param lot
     * @return
     */
    public List<ModuleClass> findModuleEntities(Lot lot) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ModuleClass> query = em.createQuery("SELECT m FROM ModuleClass m  WHERE m.lot = :lot", ModuleClass.class);
            query.setParameter("lot", lot);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getModuleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ModuleClass> rt = cq.from(ModuleClass.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<ModuleClass> findModuleEntitiesForSelectedFarm(Farm selectedFarm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ModuleClass> query = em.createQuery("SELECT m FROM ModuleClass m WHERE m.lot.farm = :farm", ModuleClass.class);
            query.setParameter("farm", selectedFarm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
