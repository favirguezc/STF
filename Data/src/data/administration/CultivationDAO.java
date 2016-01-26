/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.administration;

import data.exceptions.NonexistentEntityException;
import data.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.administration.Cultivation;
import model.administration.ModuleClass;

/**
 *
 * @author fredy
 */
public class CultivationDAO implements Serializable {

    public CultivationDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cultivation cultivation) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        Cultivation old = findActiveCultivationEntity(cultivation.getModuleObject());
        if (old != null) {
            old.setActive(false);
            edit(old);
        }
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cultivation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCultivation(cultivation.getId()) != null) {
                throw new PreexistingEntityException("Cultivation " + cultivation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cultivation cultivation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cultivation = em.merge(cultivation);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cultivation.getId();
                if (findCultivation(id) == null) {
                    throw new NonexistentEntityException("The cultivation with id " + id + " no longer exists.");
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
            Cultivation cultivation;
            try {
                cultivation = em.getReference(Cultivation.class, id);
                cultivation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cultivation with id " + id + " no longer exists.", enfe);
            }
            em.remove(cultivation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cultivation> findCultivationEntities() {
        return CultivationDAO.this.findActiveCultivationEntities(true, -1, -1);
    }

    public List<Cultivation> findCultivationEntities(int maxResults, int firstResult) {
        return CultivationDAO.this.findActiveCultivationEntities(false, maxResults, firstResult);
    }

    private List<Cultivation> findActiveCultivationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cultivation.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<Cultivation> resultList = q.getResultList();
            
            for (Cultivation c : new ArrayList<Cultivation>(resultList)) {
                if (!c.isActive()) {
                    resultList.remove(c);
                }
            }
            return resultList;
        } finally {
            em.close();
        }
    }

    public Cultivation findActiveCultivationEntity(ModuleClass module) {
        EntityManager em = getEntityManager();
        Cultivation cultivation = null;
        try {
            TypedQuery<Cultivation> query = em.createQuery("SELECT c FROM Cultivation c WHERE c.moduleObject = :module", Cultivation.class);
            query.setParameter("module", module);
            cultivation = query.getSingleResult();
        } catch(Exception e){}finally {
            em.close();
        }
        return cultivation;
    }

    public Cultivation findCultivation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cultivation.class, id);
        } finally {
            em.close();
        }
    }

    public int getCultivationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cultivation> rt = cq.from(Cultivation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
