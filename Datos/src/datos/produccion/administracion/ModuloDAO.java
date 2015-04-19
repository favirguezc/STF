/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.administracion;

import datos.exceptions.NonexistentEntityException;
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
public class ModuloDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public ModuloDAO(EntityManagerFactory emf) {
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
     * @param modulo
     */
    public void create(ModuleClass modulo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(modulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param modulo
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(ModuleClass modulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            modulo = em.merge(modulo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = modulo.getId();
                if (findModulo(id) == null) {
                    throw new NonexistentEntityException("The modulo with id " + id + " no longer exists.");
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
            ModuleClass modulo;
            try {
                modulo = em.getReference(ModuleClass.class, id);
                modulo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modulo with id " + id + " no longer exists.", enfe);
            }
            em.remove(modulo);
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
    public List<ModuleClass> findModuloEntities() {
        return findModuloEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<ModuleClass> findModuloEntities(int maxResults, int firstResult) {
        return findModuloEntities(false, maxResults, firstResult);
    }

    private List<ModuleClass> findModuloEntities(boolean all, int maxResults, int firstResult) {
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
    public ModuleClass findModulo(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ModuleClass.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param nombre
     * @param lote
     * @return
     * @throws Exception
     */
    public ModuleClass findModulo(String nombre, Lot lote) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ModuleClass> query = em.createQuery("SELECT t FROM Modulo t WHERE t.nombre = :nombre AND t.lote = :lote", ModuleClass.class);
            query.setParameter("nombre", nombre);
            query.setParameter("lote", lote);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param lote
     * @return
     */
    public List<ModuleClass> findModuloEntities(Lot lote) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ModuleClass> query = em.createQuery("SELECT t FROM Modulo t WHERE t.lote = :lote", ModuleClass.class);
            query.setParameter("lote", lote);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getModuloCount() {
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

    public List<ModuleClass> findModuloEntitiesForSelectedFarm(Farm selectedFarm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ModuleClass> query = em.createQuery("SELECT m FROM Modulo m WHERE m.lote.finca = :finca", ModuleClass.class);
            query.setParameter("finca", selectedFarm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
