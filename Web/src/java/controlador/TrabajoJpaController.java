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
import modelo.Labor;
import modelo.Trabajo;

/**
 *
 * @author fredy
 */
public class TrabajoJpaController implements Serializable {

    public TrabajoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trabajo trabajo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona asistenteId = trabajo.getAsistenteId();
            if (asistenteId != null) {
                asistenteId = em.getReference(asistenteId.getClass(), asistenteId.getId());
                trabajo.setAsistenteId(asistenteId);
            }
            Labor laborId = trabajo.getLaborId();
            if (laborId != null) {
                laborId = em.getReference(laborId.getClass(), laborId.getId());
                trabajo.setLaborId(laborId);
            }
            Persona operarioId = trabajo.getOperarioId();
            if (operarioId != null) {
                operarioId = em.getReference(operarioId.getClass(), operarioId.getId());
                trabajo.setOperarioId(operarioId);
            }
            Persona productorId = trabajo.getProductorId();
            if (productorId != null) {
                productorId = em.getReference(productorId.getClass(), productorId.getId());
                trabajo.setProductorId(productorId);
            }
            em.persist(trabajo);
            if (asistenteId != null) {
                asistenteId.getTrabajoList().add(trabajo);
                asistenteId = em.merge(asistenteId);
            }
            if (laborId != null) {
                laborId.getTrabajoList().add(trabajo);
                laborId = em.merge(laborId);
            }
            if (operarioId != null) {
                operarioId.getTrabajoList().add(trabajo);
                operarioId = em.merge(operarioId);
            }
            if (productorId != null) {
                productorId.getTrabajoList().add(trabajo);
                productorId = em.merge(productorId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTrabajo(trabajo.getId()) != null) {
                throw new PreexistingEntityException("Trabajo " + trabajo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Trabajo trabajo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trabajo persistentTrabajo = em.find(Trabajo.class, trabajo.getId());
            Persona asistenteIdOld = persistentTrabajo.getAsistenteId();
            Persona asistenteIdNew = trabajo.getAsistenteId();
            Labor laborIdOld = persistentTrabajo.getLaborId();
            Labor laborIdNew = trabajo.getLaborId();
            Persona operarioIdOld = persistentTrabajo.getOperarioId();
            Persona operarioIdNew = trabajo.getOperarioId();
            Persona productorIdOld = persistentTrabajo.getProductorId();
            Persona productorIdNew = trabajo.getProductorId();
            if (asistenteIdNew != null) {
                asistenteIdNew = em.getReference(asistenteIdNew.getClass(), asistenteIdNew.getId());
                trabajo.setAsistenteId(asistenteIdNew);
            }
            if (laborIdNew != null) {
                laborIdNew = em.getReference(laborIdNew.getClass(), laborIdNew.getId());
                trabajo.setLaborId(laborIdNew);
            }
            if (operarioIdNew != null) {
                operarioIdNew = em.getReference(operarioIdNew.getClass(), operarioIdNew.getId());
                trabajo.setOperarioId(operarioIdNew);
            }
            if (productorIdNew != null) {
                productorIdNew = em.getReference(productorIdNew.getClass(), productorIdNew.getId());
                trabajo.setProductorId(productorIdNew);
            }
            trabajo = em.merge(trabajo);
            if (asistenteIdOld != null && !asistenteIdOld.equals(asistenteIdNew)) {
                asistenteIdOld.getTrabajoList().remove(trabajo);
                asistenteIdOld = em.merge(asistenteIdOld);
            }
            if (asistenteIdNew != null && !asistenteIdNew.equals(asistenteIdOld)) {
                asistenteIdNew.getTrabajoList().add(trabajo);
                asistenteIdNew = em.merge(asistenteIdNew);
            }
            if (laborIdOld != null && !laborIdOld.equals(laborIdNew)) {
                laborIdOld.getTrabajoList().remove(trabajo);
                laborIdOld = em.merge(laborIdOld);
            }
            if (laborIdNew != null && !laborIdNew.equals(laborIdOld)) {
                laborIdNew.getTrabajoList().add(trabajo);
                laborIdNew = em.merge(laborIdNew);
            }
            if (operarioIdOld != null && !operarioIdOld.equals(operarioIdNew)) {
                operarioIdOld.getTrabajoList().remove(trabajo);
                operarioIdOld = em.merge(operarioIdOld);
            }
            if (operarioIdNew != null && !operarioIdNew.equals(operarioIdOld)) {
                operarioIdNew.getTrabajoList().add(trabajo);
                operarioIdNew = em.merge(operarioIdNew);
            }
            if (productorIdOld != null && !productorIdOld.equals(productorIdNew)) {
                productorIdOld.getTrabajoList().remove(trabajo);
                productorIdOld = em.merge(productorIdOld);
            }
            if (productorIdNew != null && !productorIdNew.equals(productorIdOld)) {
                productorIdNew.getTrabajoList().add(trabajo);
                productorIdNew = em.merge(productorIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = trabajo.getId();
                if (findTrabajo(id) == null) {
                    throw new NonexistentEntityException("The trabajo with id " + id + " no longer exists.");
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
            Trabajo trabajo;
            try {
                trabajo = em.getReference(Trabajo.class, id);
                trabajo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trabajo with id " + id + " no longer exists.", enfe);
            }
            Persona asistenteId = trabajo.getAsistenteId();
            if (asistenteId != null) {
                asistenteId.getTrabajoList().remove(trabajo);
                asistenteId = em.merge(asistenteId);
            }
            Labor laborId = trabajo.getLaborId();
            if (laborId != null) {
                laborId.getTrabajoList().remove(trabajo);
                laborId = em.merge(laborId);
            }
            Persona operarioId = trabajo.getOperarioId();
            if (operarioId != null) {
                operarioId.getTrabajoList().remove(trabajo);
                operarioId = em.merge(operarioId);
            }
            Persona productorId = trabajo.getProductorId();
            if (productorId != null) {
                productorId.getTrabajoList().remove(trabajo);
                productorId = em.merge(productorId);
            }
            em.remove(trabajo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Trabajo> findTrabajoEntities() {
        return findTrabajoEntities(true, -1, -1);
    }

    public List<Trabajo> findTrabajoEntities(int maxResults, int firstResult) {
        return findTrabajoEntities(false, maxResults, firstResult);
    }

    private List<Trabajo> findTrabajoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trabajo.class));
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

    public Trabajo findTrabajo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trabajo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrabajoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trabajo> rt = cq.from(Trabajo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
