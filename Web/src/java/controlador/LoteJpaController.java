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
import modelo.Modulo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Lote;

/**
 *
 * @author fredy
 */
public class LoteJpaController implements Serializable {

    public LoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lote lote) throws PreexistingEntityException, Exception {
        if (lote.getModuloList() == null) {
            lote.setModuloList(new ArrayList<Modulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Modulo> attachedModuloList = new ArrayList<Modulo>();
            for (Modulo moduloListModuloToAttach : lote.getModuloList()) {
                moduloListModuloToAttach = em.getReference(moduloListModuloToAttach.getClass(), moduloListModuloToAttach.getId());
                attachedModuloList.add(moduloListModuloToAttach);
            }
            lote.setModuloList(attachedModuloList);
            em.persist(lote);
            for (Modulo moduloListModulo : lote.getModuloList()) {
                Lote oldLoteIdOfModuloListModulo = moduloListModulo.getLoteId();
                moduloListModulo.setLoteId(lote);
                moduloListModulo = em.merge(moduloListModulo);
                if (oldLoteIdOfModuloListModulo != null) {
                    oldLoteIdOfModuloListModulo.getModuloList().remove(moduloListModulo);
                    oldLoteIdOfModuloListModulo = em.merge(oldLoteIdOfModuloListModulo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLote(lote.getId()) != null) {
                throw new PreexistingEntityException("Lote " + lote + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lote lote) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lote persistentLote = em.find(Lote.class, lote.getId());
            List<Modulo> moduloListOld = persistentLote.getModuloList();
            List<Modulo> moduloListNew = lote.getModuloList();
            List<Modulo> attachedModuloListNew = new ArrayList<Modulo>();
            for (Modulo moduloListNewModuloToAttach : moduloListNew) {
                moduloListNewModuloToAttach = em.getReference(moduloListNewModuloToAttach.getClass(), moduloListNewModuloToAttach.getId());
                attachedModuloListNew.add(moduloListNewModuloToAttach);
            }
            moduloListNew = attachedModuloListNew;
            lote.setModuloList(moduloListNew);
            lote = em.merge(lote);
            for (Modulo moduloListOldModulo : moduloListOld) {
                if (!moduloListNew.contains(moduloListOldModulo)) {
                    moduloListOldModulo.setLoteId(null);
                    moduloListOldModulo = em.merge(moduloListOldModulo);
                }
            }
            for (Modulo moduloListNewModulo : moduloListNew) {
                if (!moduloListOld.contains(moduloListNewModulo)) {
                    Lote oldLoteIdOfModuloListNewModulo = moduloListNewModulo.getLoteId();
                    moduloListNewModulo.setLoteId(lote);
                    moduloListNewModulo = em.merge(moduloListNewModulo);
                    if (oldLoteIdOfModuloListNewModulo != null && !oldLoteIdOfModuloListNewModulo.equals(lote)) {
                        oldLoteIdOfModuloListNewModulo.getModuloList().remove(moduloListNewModulo);
                        oldLoteIdOfModuloListNewModulo = em.merge(oldLoteIdOfModuloListNewModulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = lote.getId();
                if (findLote(id) == null) {
                    throw new NonexistentEntityException("The lote with id " + id + " no longer exists.");
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
            Lote lote;
            try {
                lote = em.getReference(Lote.class, id);
                lote.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lote with id " + id + " no longer exists.", enfe);
            }
            List<Modulo> moduloList = lote.getModuloList();
            for (Modulo moduloListModulo : moduloList) {
                moduloListModulo.setLoteId(null);
                moduloListModulo = em.merge(moduloListModulo);
            }
            em.remove(lote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lote> findLoteEntities() {
        return findLoteEntities(true, -1, -1);
    }

    public List<Lote> findLoteEntities(int maxResults, int firstResult) {
        return findLoteEntities(false, maxResults, firstResult);
    }

    private List<Lote> findLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lote.class));
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

    public Lote findLote(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lote.class, id);
        } finally {
            em.close();
        }
    }

    public int getLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lote> rt = cq.from(Lote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
