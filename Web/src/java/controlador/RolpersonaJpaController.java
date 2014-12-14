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
import modelo.Rol;
import modelo.Rolpersona;

/**
 *
 * @author fredy
 */
public class RolpersonaJpaController implements Serializable {

    public RolpersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rolpersona rolpersona) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona personaId = rolpersona.getPersonaId();
            if (personaId != null) {
                personaId = em.getReference(personaId.getClass(), personaId.getId());
                rolpersona.setPersonaId(personaId);
            }
            Rol rolId = rolpersona.getRolId();
            if (rolId != null) {
                rolId = em.getReference(rolId.getClass(), rolId.getId());
                rolpersona.setRolId(rolId);
            }
            em.persist(rolpersona);
            if (personaId != null) {
                personaId.getRolpersonaList().add(rolpersona);
                personaId = em.merge(personaId);
            }
            if (rolId != null) {
                rolId.getRolpersonaList().add(rolpersona);
                rolId = em.merge(rolId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRolpersona(rolpersona.getId()) != null) {
                throw new PreexistingEntityException("Rolpersona " + rolpersona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rolpersona rolpersona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolpersona persistentRolpersona = em.find(Rolpersona.class, rolpersona.getId());
            Persona personaIdOld = persistentRolpersona.getPersonaId();
            Persona personaIdNew = rolpersona.getPersonaId();
            Rol rolIdOld = persistentRolpersona.getRolId();
            Rol rolIdNew = rolpersona.getRolId();
            if (personaIdNew != null) {
                personaIdNew = em.getReference(personaIdNew.getClass(), personaIdNew.getId());
                rolpersona.setPersonaId(personaIdNew);
            }
            if (rolIdNew != null) {
                rolIdNew = em.getReference(rolIdNew.getClass(), rolIdNew.getId());
                rolpersona.setRolId(rolIdNew);
            }
            rolpersona = em.merge(rolpersona);
            if (personaIdOld != null && !personaIdOld.equals(personaIdNew)) {
                personaIdOld.getRolpersonaList().remove(rolpersona);
                personaIdOld = em.merge(personaIdOld);
            }
            if (personaIdNew != null && !personaIdNew.equals(personaIdOld)) {
                personaIdNew.getRolpersonaList().add(rolpersona);
                personaIdNew = em.merge(personaIdNew);
            }
            if (rolIdOld != null && !rolIdOld.equals(rolIdNew)) {
                rolIdOld.getRolpersonaList().remove(rolpersona);
                rolIdOld = em.merge(rolIdOld);
            }
            if (rolIdNew != null && !rolIdNew.equals(rolIdOld)) {
                rolIdNew.getRolpersonaList().add(rolpersona);
                rolIdNew = em.merge(rolIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = rolpersona.getId();
                if (findRolpersona(id) == null) {
                    throw new NonexistentEntityException("The rolpersona with id " + id + " no longer exists.");
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
            Rolpersona rolpersona;
            try {
                rolpersona = em.getReference(Rolpersona.class, id);
                rolpersona.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolpersona with id " + id + " no longer exists.", enfe);
            }
            Persona personaId = rolpersona.getPersonaId();
            if (personaId != null) {
                personaId.getRolpersonaList().remove(rolpersona);
                personaId = em.merge(personaId);
            }
            Rol rolId = rolpersona.getRolId();
            if (rolId != null) {
                rolId.getRolpersonaList().remove(rolpersona);
                rolId = em.merge(rolId);
            }
            em.remove(rolpersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rolpersona> findRolpersonaEntities() {
        return findRolpersonaEntities(true, -1, -1);
    }

    public List<Rolpersona> findRolpersonaEntities(int maxResults, int firstResult) {
        return findRolpersonaEntities(false, maxResults, firstResult);
    }

    private List<Rolpersona> findRolpersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rolpersona.class));
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

    public Rolpersona findRolpersona(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rolpersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolpersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rolpersona> rt = cq.from(Rolpersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
