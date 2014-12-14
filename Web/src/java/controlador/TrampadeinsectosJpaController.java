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
import modelo.Trampadeinsectos;

/**
 *
 * @author fredy
 */
public class TrampadeinsectosJpaController implements Serializable {

    public TrampadeinsectosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trampadeinsectos trampadeinsectos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona asistenteId = trampadeinsectos.getAsistenteId();
            if (asistenteId != null) {
                asistenteId = em.getReference(asistenteId.getClass(), asistenteId.getId());
                trampadeinsectos.setAsistenteId(asistenteId);
            }
            Persona productorId = trampadeinsectos.getProductorId();
            if (productorId != null) {
                productorId = em.getReference(productorId.getClass(), productorId.getId());
                trampadeinsectos.setProductorId(productorId);
            }
            em.persist(trampadeinsectos);
            if (asistenteId != null) {
                asistenteId.getTrampadeinsectosList().add(trampadeinsectos);
                asistenteId = em.merge(asistenteId);
            }
            if (productorId != null) {
                productorId.getTrampadeinsectosList().add(trampadeinsectos);
                productorId = em.merge(productorId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTrampadeinsectos(trampadeinsectos.getId()) != null) {
                throw new PreexistingEntityException("Trampadeinsectos " + trampadeinsectos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Trampadeinsectos trampadeinsectos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trampadeinsectos persistentTrampadeinsectos = em.find(Trampadeinsectos.class, trampadeinsectos.getId());
            Persona asistenteIdOld = persistentTrampadeinsectos.getAsistenteId();
            Persona asistenteIdNew = trampadeinsectos.getAsistenteId();
            Persona productorIdOld = persistentTrampadeinsectos.getProductorId();
            Persona productorIdNew = trampadeinsectos.getProductorId();
            if (asistenteIdNew != null) {
                asistenteIdNew = em.getReference(asistenteIdNew.getClass(), asistenteIdNew.getId());
                trampadeinsectos.setAsistenteId(asistenteIdNew);
            }
            if (productorIdNew != null) {
                productorIdNew = em.getReference(productorIdNew.getClass(), productorIdNew.getId());
                trampadeinsectos.setProductorId(productorIdNew);
            }
            trampadeinsectos = em.merge(trampadeinsectos);
            if (asistenteIdOld != null && !asistenteIdOld.equals(asistenteIdNew)) {
                asistenteIdOld.getTrampadeinsectosList().remove(trampadeinsectos);
                asistenteIdOld = em.merge(asistenteIdOld);
            }
            if (asistenteIdNew != null && !asistenteIdNew.equals(asistenteIdOld)) {
                asistenteIdNew.getTrampadeinsectosList().add(trampadeinsectos);
                asistenteIdNew = em.merge(asistenteIdNew);
            }
            if (productorIdOld != null && !productorIdOld.equals(productorIdNew)) {
                productorIdOld.getTrampadeinsectosList().remove(trampadeinsectos);
                productorIdOld = em.merge(productorIdOld);
            }
            if (productorIdNew != null && !productorIdNew.equals(productorIdOld)) {
                productorIdNew.getTrampadeinsectosList().add(trampadeinsectos);
                productorIdNew = em.merge(productorIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = trampadeinsectos.getId();
                if (findTrampadeinsectos(id) == null) {
                    throw new NonexistentEntityException("The trampadeinsectos with id " + id + " no longer exists.");
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
            Trampadeinsectos trampadeinsectos;
            try {
                trampadeinsectos = em.getReference(Trampadeinsectos.class, id);
                trampadeinsectos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trampadeinsectos with id " + id + " no longer exists.", enfe);
            }
            Persona asistenteId = trampadeinsectos.getAsistenteId();
            if (asistenteId != null) {
                asistenteId.getTrampadeinsectosList().remove(trampadeinsectos);
                asistenteId = em.merge(asistenteId);
            }
            Persona productorId = trampadeinsectos.getProductorId();
            if (productorId != null) {
                productorId.getTrampadeinsectosList().remove(trampadeinsectos);
                productorId = em.merge(productorId);
            }
            em.remove(trampadeinsectos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Trampadeinsectos> findTrampadeinsectosEntities() {
        return findTrampadeinsectosEntities(true, -1, -1);
    }

    public List<Trampadeinsectos> findTrampadeinsectosEntities(int maxResults, int firstResult) {
        return findTrampadeinsectosEntities(false, maxResults, firstResult);
    }

    private List<Trampadeinsectos> findTrampadeinsectosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trampadeinsectos.class));
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

    public Trampadeinsectos findTrampadeinsectos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trampadeinsectos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrampadeinsectosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trampadeinsectos> rt = cq.from(Trampadeinsectos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
