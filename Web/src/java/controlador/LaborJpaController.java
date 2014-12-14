/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Trabajo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Labor;

/**
 *
 * @author fredy
 */
public class LaborJpaController implements Serializable {

    public LaborJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Labor labor) throws PreexistingEntityException, Exception {
        if (labor.getTrabajoList() == null) {
            labor.setTrabajoList(new ArrayList<Trabajo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Trabajo> attachedTrabajoList = new ArrayList<Trabajo>();
            for (Trabajo trabajoListTrabajoToAttach : labor.getTrabajoList()) {
                trabajoListTrabajoToAttach = em.getReference(trabajoListTrabajoToAttach.getClass(), trabajoListTrabajoToAttach.getId());
                attachedTrabajoList.add(trabajoListTrabajoToAttach);
            }
            labor.setTrabajoList(attachedTrabajoList);
            em.persist(labor);
            for (Trabajo trabajoListTrabajo : labor.getTrabajoList()) {
                Labor oldLaborIdOfTrabajoListTrabajo = trabajoListTrabajo.getLaborId();
                trabajoListTrabajo.setLaborId(labor);
                trabajoListTrabajo = em.merge(trabajoListTrabajo);
                if (oldLaborIdOfTrabajoListTrabajo != null) {
                    oldLaborIdOfTrabajoListTrabajo.getTrabajoList().remove(trabajoListTrabajo);
                    oldLaborIdOfTrabajoListTrabajo = em.merge(oldLaborIdOfTrabajoListTrabajo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLabor(labor.getId()) != null) {
                throw new PreexistingEntityException("Labor " + labor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Labor labor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Labor persistentLabor = em.find(Labor.class, labor.getId());
            List<Trabajo> trabajoListOld = persistentLabor.getTrabajoList();
            List<Trabajo> trabajoListNew = labor.getTrabajoList();
            List<Trabajo> attachedTrabajoListNew = new ArrayList<Trabajo>();
            for (Trabajo trabajoListNewTrabajoToAttach : trabajoListNew) {
                trabajoListNewTrabajoToAttach = em.getReference(trabajoListNewTrabajoToAttach.getClass(), trabajoListNewTrabajoToAttach.getId());
                attachedTrabajoListNew.add(trabajoListNewTrabajoToAttach);
            }
            trabajoListNew = attachedTrabajoListNew;
            labor.setTrabajoList(trabajoListNew);
            labor = em.merge(labor);
            for (Trabajo trabajoListOldTrabajo : trabajoListOld) {
                if (!trabajoListNew.contains(trabajoListOldTrabajo)) {
                    trabajoListOldTrabajo.setLaborId(null);
                    trabajoListOldTrabajo = em.merge(trabajoListOldTrabajo);
                }
            }
            for (Trabajo trabajoListNewTrabajo : trabajoListNew) {
                if (!trabajoListOld.contains(trabajoListNewTrabajo)) {
                    Labor oldLaborIdOfTrabajoListNewTrabajo = trabajoListNewTrabajo.getLaborId();
                    trabajoListNewTrabajo.setLaborId(labor);
                    trabajoListNewTrabajo = em.merge(trabajoListNewTrabajo);
                    if (oldLaborIdOfTrabajoListNewTrabajo != null && !oldLaborIdOfTrabajoListNewTrabajo.equals(labor)) {
                        oldLaborIdOfTrabajoListNewTrabajo.getTrabajoList().remove(trabajoListNewTrabajo);
                        oldLaborIdOfTrabajoListNewTrabajo = em.merge(oldLaborIdOfTrabajoListNewTrabajo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = labor.getId();
                if (findLabor(id) == null) {
                    throw new NonexistentEntityException("The labor with id " + id + " no longer exists.");
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
            Labor labor;
            try {
                labor = em.getReference(Labor.class, id);
                labor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The labor with id " + id + " no longer exists.", enfe);
            }
            List<Trabajo> trabajoList = labor.getTrabajoList();
            for (Trabajo trabajoListTrabajo : trabajoList) {
                trabajoListTrabajo.setLaborId(null);
                trabajoListTrabajo = em.merge(trabajoListTrabajo);
            }
            em.remove(labor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Labor> findLaborEntities() {
        return findLaborEntities(true, -1, -1);
    }

    public List<Labor> findLaborEntities(int maxResults, int firstResult) {
        return findLaborEntities(false, maxResults, firstResult);
    }

    private List<Labor> findLaborEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Labor.class));
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

    public Labor findLabor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Labor.class, id);
        } finally {
            em.close();
        }
    }

    public int getLaborCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Labor> rt = cq.from(Labor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
