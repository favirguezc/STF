/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.crop;

import data.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.administration.Cultivation;
import model.administration.Farm;
import model.administration.Lot;
import model.administration.ModuleClass;
import model.administration.Person;
import model.crop.Crop;

/**
 *
 * @author fredy
 */
public class CropDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public CropDAO(EntityManagerFactory emf) {
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
     * @param crop
     */
    public void create(Crop crop) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(crop);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param crop
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Crop crop) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            crop = em.merge(crop);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = crop.getId();
                if (findCrop(id) == null) {
                    throw new NonexistentEntityException("The crop with id " + id + " no longer exists.");
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
            Crop crop;
            try {
                crop = em.getReference(Crop.class, id);
                crop.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crop with id " + id + " no longer exists.", enfe);
            }
            em.remove(crop);
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
    public List<Crop> findCropEntities() {
        return findCropEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Crop> findCropEntities(int maxResults, int firstResult) {
        return findCropEntities(false, maxResults, firstResult);
    }

    private List<Crop> findCropEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crop.class));
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
    public Crop findCrop(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crop.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getCropCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crop> rt = cq.from(Crop.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param worker
     * @param module
     * @param start
     * @param end
     * @return
     */
    public List<Crop> findCropEntities(Person worker, ModuleClass module, Date start, Date end) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT t FROM Crop t";
        if(worker != null || module != null || start != null || end != null){
            queryString += " WHERE";
        }
        if (worker != null) {
            queryString += " t.collector = :worker";
            a = true;
        }
        if (module != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.cultivation.moduleObject = :module ";
            b = true;
        }
        if (end != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " t.harvestDate BETWEEN :date1 AND :date2";
            c = d = true;
        } else if (start != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " t.harvestDate = :date1";
            c = true;
        }
        try {
            TypedQuery<Crop> query = em.createQuery(queryString, Crop.class);
            if (c) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (d) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("worker", worker);
            }
            if (b) {
                query.setParameter("module", module);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param worker
     * @param lot
     * @param start
     * @param end
     * @return
     */
    public List<Crop> findCropEntities(Person worker, Lot lot, Date start, Date end) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT t FROM Crop t";
        if(worker != null || lot != null || start != null || end != null){
            queryString += " WHERE";
        }
        if (worker != null) {
            queryString += " t.collector = :worker";
            a = true;
        }
        if (lot != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.cultivation.moduleObject.lot = :lot ";
            b = true;
        }
        if (end != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " t.harvestDate BETWEEN :date1 AND :date2";
            c = d = true;
        } else if (start != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " t.harvestDate = :date1";
            c = true;
        }
        try {
            TypedQuery<Crop> query = em.createQuery(queryString, Crop.class);
            if (c) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (d) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("worker", worker);
            }
            if (b) {
                query.setParameter("lot", lot);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Crop> findCropEntities(Person worker, Farm farm, Date start, Date end) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT t FROM Crop t";
        if(worker != null || farm != null || start != null || end != null){
            queryString += " WHERE";
        }
        if (worker != null) {
            queryString += " t.collector = :worker";
            a = true;
        }
        if (farm != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.cultivation.moduleObject.lot.farm = :farm ";
            b = true;
        }
        if (end != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " t.harvestDate BETWEEN :date1 AND :date2";
            c = d = true;
        } else if (start != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " t.harvestDate = :date1";
            c = true;
        }
        try {
            TypedQuery<Crop> query = em.createQuery(queryString, Crop.class);
            if (c) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (d) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("worker", worker);
            }
            if (b) {
                query.setParameter("farm", farm);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
