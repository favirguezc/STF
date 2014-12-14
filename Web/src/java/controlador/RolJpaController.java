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
import modelo.Rolpersona;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Rol;

/**
 *
 * @author fredy
 */
public class RolJpaController implements Serializable {

    public RolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rol rol) throws PreexistingEntityException, Exception {
        if (rol.getRolpersonaList() == null) {
            rol.setRolpersonaList(new ArrayList<Rolpersona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Rolpersona> attachedRolpersonaList = new ArrayList<Rolpersona>();
            for (Rolpersona rolpersonaListRolpersonaToAttach : rol.getRolpersonaList()) {
                rolpersonaListRolpersonaToAttach = em.getReference(rolpersonaListRolpersonaToAttach.getClass(), rolpersonaListRolpersonaToAttach.getId());
                attachedRolpersonaList.add(rolpersonaListRolpersonaToAttach);
            }
            rol.setRolpersonaList(attachedRolpersonaList);
            em.persist(rol);
            for (Rolpersona rolpersonaListRolpersona : rol.getRolpersonaList()) {
                Rol oldRolIdOfRolpersonaListRolpersona = rolpersonaListRolpersona.getRolId();
                rolpersonaListRolpersona.setRolId(rol);
                rolpersonaListRolpersona = em.merge(rolpersonaListRolpersona);
                if (oldRolIdOfRolpersonaListRolpersona != null) {
                    oldRolIdOfRolpersonaListRolpersona.getRolpersonaList().remove(rolpersonaListRolpersona);
                    oldRolIdOfRolpersonaListRolpersona = em.merge(oldRolIdOfRolpersonaListRolpersona);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRol(rol.getId()) != null) {
                throw new PreexistingEntityException("Rol " + rol + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rol rol) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getId());
            List<Rolpersona> rolpersonaListOld = persistentRol.getRolpersonaList();
            List<Rolpersona> rolpersonaListNew = rol.getRolpersonaList();
            List<Rolpersona> attachedRolpersonaListNew = new ArrayList<Rolpersona>();
            for (Rolpersona rolpersonaListNewRolpersonaToAttach : rolpersonaListNew) {
                rolpersonaListNewRolpersonaToAttach = em.getReference(rolpersonaListNewRolpersonaToAttach.getClass(), rolpersonaListNewRolpersonaToAttach.getId());
                attachedRolpersonaListNew.add(rolpersonaListNewRolpersonaToAttach);
            }
            rolpersonaListNew = attachedRolpersonaListNew;
            rol.setRolpersonaList(rolpersonaListNew);
            rol = em.merge(rol);
            for (Rolpersona rolpersonaListOldRolpersona : rolpersonaListOld) {
                if (!rolpersonaListNew.contains(rolpersonaListOldRolpersona)) {
                    rolpersonaListOldRolpersona.setRolId(null);
                    rolpersonaListOldRolpersona = em.merge(rolpersonaListOldRolpersona);
                }
            }
            for (Rolpersona rolpersonaListNewRolpersona : rolpersonaListNew) {
                if (!rolpersonaListOld.contains(rolpersonaListNewRolpersona)) {
                    Rol oldRolIdOfRolpersonaListNewRolpersona = rolpersonaListNewRolpersona.getRolId();
                    rolpersonaListNewRolpersona.setRolId(rol);
                    rolpersonaListNewRolpersona = em.merge(rolpersonaListNewRolpersona);
                    if (oldRolIdOfRolpersonaListNewRolpersona != null && !oldRolIdOfRolpersonaListNewRolpersona.equals(rol)) {
                        oldRolIdOfRolpersonaListNewRolpersona.getRolpersonaList().remove(rolpersonaListNewRolpersona);
                        oldRolIdOfRolpersonaListNewRolpersona = em.merge(oldRolIdOfRolpersonaListNewRolpersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = rol.getId();
                if (findRol(id) == null) {
                    throw new NonexistentEntityException("The rol with id " + id + " no longer exists.");
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
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rol with id " + id + " no longer exists.", enfe);
            }
            List<Rolpersona> rolpersonaList = rol.getRolpersonaList();
            for (Rolpersona rolpersonaListRolpersona : rolpersonaList) {
                rolpersonaListRolpersona.setRolId(null);
                rolpersonaListRolpersona = em.merge(rolpersonaListRolpersona);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rol> findRolEntities() {
        return findRolEntities(true, -1, -1);
    }

    public List<Rol> findRolEntities(int maxResults, int firstResult) {
        return findRolEntities(false, maxResults, firstResult);
    }

    private List<Rol> findRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rol.class));
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

    public Rol findRol(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rol> rt = cq.from(Rol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
