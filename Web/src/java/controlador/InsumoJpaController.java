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
import modelo.Aplicacionfitosanitaria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Insumo;

/**
 *
 * @author fredy
 */
public class InsumoJpaController implements Serializable {

    public InsumoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Insumo insumo) throws PreexistingEntityException, Exception {
        if (insumo.getAplicacionfitosanitariaList() == null) {
            insumo.setAplicacionfitosanitariaList(new ArrayList<Aplicacionfitosanitaria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Aplicacionfitosanitaria> attachedAplicacionfitosanitariaList = new ArrayList<Aplicacionfitosanitaria>();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListAplicacionfitosanitariaToAttach : insumo.getAplicacionfitosanitariaList()) {
                aplicacionfitosanitariaListAplicacionfitosanitariaToAttach = em.getReference(aplicacionfitosanitariaListAplicacionfitosanitariaToAttach.getClass(), aplicacionfitosanitariaListAplicacionfitosanitariaToAttach.getId());
                attachedAplicacionfitosanitariaList.add(aplicacionfitosanitariaListAplicacionfitosanitariaToAttach);
            }
            insumo.setAplicacionfitosanitariaList(attachedAplicacionfitosanitariaList);
            em.persist(insumo);
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListAplicacionfitosanitaria : insumo.getAplicacionfitosanitariaList()) {
                Insumo oldProductoIdOfAplicacionfitosanitariaListAplicacionfitosanitaria = aplicacionfitosanitariaListAplicacionfitosanitaria.getProductoId();
                aplicacionfitosanitariaListAplicacionfitosanitaria.setProductoId(insumo);
                aplicacionfitosanitariaListAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaListAplicacionfitosanitaria);
                if (oldProductoIdOfAplicacionfitosanitariaListAplicacionfitosanitaria != null) {
                    oldProductoIdOfAplicacionfitosanitariaListAplicacionfitosanitaria.getAplicacionfitosanitariaList().remove(aplicacionfitosanitariaListAplicacionfitosanitaria);
                    oldProductoIdOfAplicacionfitosanitariaListAplicacionfitosanitaria = em.merge(oldProductoIdOfAplicacionfitosanitariaListAplicacionfitosanitaria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInsumo(insumo.getId()) != null) {
                throw new PreexistingEntityException("Insumo " + insumo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Insumo insumo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Insumo persistentInsumo = em.find(Insumo.class, insumo.getId());
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaListOld = persistentInsumo.getAplicacionfitosanitariaList();
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaListNew = insumo.getAplicacionfitosanitariaList();
            List<Aplicacionfitosanitaria> attachedAplicacionfitosanitariaListNew = new ArrayList<Aplicacionfitosanitaria>();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListNewAplicacionfitosanitariaToAttach : aplicacionfitosanitariaListNew) {
                aplicacionfitosanitariaListNewAplicacionfitosanitariaToAttach = em.getReference(aplicacionfitosanitariaListNewAplicacionfitosanitariaToAttach.getClass(), aplicacionfitosanitariaListNewAplicacionfitosanitariaToAttach.getId());
                attachedAplicacionfitosanitariaListNew.add(aplicacionfitosanitariaListNewAplicacionfitosanitariaToAttach);
            }
            aplicacionfitosanitariaListNew = attachedAplicacionfitosanitariaListNew;
            insumo.setAplicacionfitosanitariaList(aplicacionfitosanitariaListNew);
            insumo = em.merge(insumo);
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListOldAplicacionfitosanitaria : aplicacionfitosanitariaListOld) {
                if (!aplicacionfitosanitariaListNew.contains(aplicacionfitosanitariaListOldAplicacionfitosanitaria)) {
                    aplicacionfitosanitariaListOldAplicacionfitosanitaria.setProductoId(null);
                    aplicacionfitosanitariaListOldAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaListOldAplicacionfitosanitaria);
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListNewAplicacionfitosanitaria : aplicacionfitosanitariaListNew) {
                if (!aplicacionfitosanitariaListOld.contains(aplicacionfitosanitariaListNewAplicacionfitosanitaria)) {
                    Insumo oldProductoIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria = aplicacionfitosanitariaListNewAplicacionfitosanitaria.getProductoId();
                    aplicacionfitosanitariaListNewAplicacionfitosanitaria.setProductoId(insumo);
                    aplicacionfitosanitariaListNewAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaListNewAplicacionfitosanitaria);
                    if (oldProductoIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria != null && !oldProductoIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria.equals(insumo)) {
                        oldProductoIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria.getAplicacionfitosanitariaList().remove(aplicacionfitosanitariaListNewAplicacionfitosanitaria);
                        oldProductoIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria = em.merge(oldProductoIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = insumo.getId();
                if (findInsumo(id) == null) {
                    throw new NonexistentEntityException("The insumo with id " + id + " no longer exists.");
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
            Insumo insumo;
            try {
                insumo = em.getReference(Insumo.class, id);
                insumo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The insumo with id " + id + " no longer exists.", enfe);
            }
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaList = insumo.getAplicacionfitosanitariaList();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListAplicacionfitosanitaria : aplicacionfitosanitariaList) {
                aplicacionfitosanitariaListAplicacionfitosanitaria.setProductoId(null);
                aplicacionfitosanitariaListAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaListAplicacionfitosanitaria);
            }
            em.remove(insumo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Insumo> findInsumoEntities() {
        return findInsumoEntities(true, -1, -1);
    }

    public List<Insumo> findInsumoEntities(int maxResults, int firstResult) {
        return findInsumoEntities(false, maxResults, firstResult);
    }

    private List<Insumo> findInsumoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Insumo.class));
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

    public Insumo findInsumo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Insumo.class, id);
        } finally {
            em.close();
        }
    }

    public int getInsumoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Insumo> rt = cq.from(Insumo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
