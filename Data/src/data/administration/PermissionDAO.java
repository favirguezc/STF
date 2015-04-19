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
import model.administration.ModuleClass;
import model.administration.PageEnum;
import model.administration.Permission;
import model.administration.RoleEnum;
import model.util.Action;

/**
 *
 * @author fredy
 */
public class PermissionDAO implements Serializable {

    public PermissionDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Permission permission) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(permission);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Permission permission) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            permission = em.merge(permission);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = permission.getId();
                if (findPermission(id) == null) {
                    throw new NonexistentEntityException("The permission with id " + id + " no longer exists.");
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
            Permission permission;
            try {
                permission = em.getReference(Permission.class, id);
                permission.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permission with id " + id + " no longer exists.", enfe);
            }
            em.remove(permission);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Permission> findPermissionEntities() {
        return findPermissionEntities(true, -1, -1);
    }

    public List<Permission> findPermissionEntities(int maxResults, int firstResult) {
        return findPermissionEntities(false, maxResults, firstResult);
    }

    private List<Permission> findPermissionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Permission.class));
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

    public Permission findPermission(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Permission.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermissionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Permission> rt = cq.from(Permission.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public boolean findPermission(RoleEnum rol, PageEnum pagina,Action accion) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ModuleClass> query = em.createQuery("SELECT p FROM Permission p WHERE p.pageEnum = :pageEnum AND p.roleEnum = :roleEnum AND p.actionEnum = :actionEnum", ModuleClass.class);
            query.setParameter("roleEnum", rol);
            query.setParameter("pageEnum", pagina);
            query.setParameter("actionEnum", accion);
            query.getSingleResult();
            return true;
        }catch(Exception e){
            return false;
        } finally {
            em.close();
        }
    }

}
