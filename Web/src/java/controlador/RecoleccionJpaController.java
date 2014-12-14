/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Persona;
import modelo.Recoleccion;

/**
 *
 * @author fredy
 */
public class RecoleccionJpaController implements Serializable {

    public RecoleccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recoleccion recoleccion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona recolectorId = recoleccion.getRecolectorId();
            if (recolectorId != null) {
                recolectorId = em.getReference(recolectorId.getClass(), recolectorId.getId());
                recoleccion.setRecolectorId(recolectorId);
            }
            em.persist(recoleccion);
            if (recolectorId != null) {
                recolectorId.getRecoleccionList().add(recoleccion);
                recolectorId = em.merge(recolectorId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRecoleccion(recoleccion.getId()) != null) {
                throw new PreexistingEntityException("Recoleccion " + recoleccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recoleccion recoleccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recoleccion persistentRecoleccion = em.find(Recoleccion.class, recoleccion.getId());
            Persona recolectorIdOld = persistentRecoleccion.getRecolectorId();
            Persona recolectorIdNew = recoleccion.getRecolectorId();
            if (recolectorIdNew != null) {
                recolectorIdNew = em.getReference(recolectorIdNew.getClass(), recolectorIdNew.getId());
                recoleccion.setRecolectorId(recolectorIdNew);
            }
            recoleccion = em.merge(recoleccion);
            if (recolectorIdOld != null && !recolectorIdOld.equals(recolectorIdNew)) {
                recolectorIdOld.getRecoleccionList().remove(recoleccion);
                recolectorIdOld = em.merge(recolectorIdOld);
            }
            if (recolectorIdNew != null && !recolectorIdNew.equals(recolectorIdOld)) {
                recolectorIdNew.getRecoleccionList().add(recoleccion);
                recolectorIdNew = em.merge(recolectorIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = recoleccion.getId();
                if (findRecoleccion(id) == null) {
                    throw new NonexistentEntityException("The recoleccion with id " + id + " no longer exists.");
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
            Recoleccion recoleccion;
            try {
                recoleccion = em.getReference(Recoleccion.class, id);
                recoleccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recoleccion with id " + id + " no longer exists.", enfe);
            }
            Persona recolectorId = recoleccion.getRecolectorId();
            if (recolectorId != null) {
                recolectorId.getRecoleccionList().remove(recoleccion);
                recolectorId = em.merge(recolectorId);
            }
            em.remove(recoleccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Recoleccion> findRecoleccionEntities() {
        return findRecoleccionEntities(true, -1, -1);
    }

    public List<Recoleccion> findRecoleccionEntities(int maxResults, int firstResult) {
        return findRecoleccionEntities(false, maxResults, firstResult);
    }

    private List<Recoleccion> findRecoleccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Recoleccion.class));
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

    public Recoleccion findRecoleccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recoleccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecoleccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Recoleccion> rt = cq.from(Recoleccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
