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
import model.administration.Farm;
import model.administration.Lot;
import model.administration.ModuleClass;
import model.crop.Classification;
import model.crop.ClassificationTypeEnum;

/**
 *
 * @author fredy
 */
public class ClassificationDAO implements Serializable {

    public ClassificationDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Classification classification) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(classification);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Classification classification) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            classification = em.merge(classification);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = classification.getId();
                if (findClassification(id) == null) {
                    throw new NonexistentEntityException("The classification with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Classification classification;
            try {
                classification = em.getReference(Classification.class, id);
                classification.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The classification with id " + id + " no longer exists.", enfe);
            }
            em.remove(classification);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Classification> findClassificationEntities(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Classification> query = em.createQuery("SELECT c FROM Classification c WHERE c.cultivation.moduleObject.lot.farm = :farm", Classification.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Classification> findClassificationEntities() {
        return findClassificationEntities(true, -1, -1);
    }

    public List<Classification> findClassificationEntities(int maxResults, int firstResult) {
        return findClassificationEntities(false, maxResults, firstResult);
    }

    private List<Classification> findClassificationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Classification.class));
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

    public Classification findClassification(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Classification.class, id);
        } finally {
            em.close();
        }
    }

    public int getClassificationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Classification> rt = cq.from(Classification.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Classification> findClassificationEntities(ModuleClass module, Date start, Date end, ClassificationTypeEnum type) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT t FROM Classification t";
        if (module != null || start != null || end != null || type != null) {
            queryString += " WHERE";
        }
        if (module != null) {
            queryString += " t.cultivation.moduleObject = :module ";
            a = true;
        }
        if (end != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.classificationDate BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.classificationDate = :date1";
            b = true;
        }
        if (type != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " t.type = :type";
            d = true;
        }
        try {
            TypedQuery<Classification> query = em.createQuery(queryString, Classification.class);
            if (a) {
                query.setParameter("module", module);
            }
            if (b) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (c) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (d) {
                query.setParameter("type", type);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Classification> findClassificationEntities(Lot lot, Date start, Date end, ClassificationTypeEnum type) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT t FROM Classification t";
        if (lot != null || start != null || end != null || type != null) {
            queryString += " WHERE";
        }
        if (lot != null) {
            queryString += " t.cultivation.moduleObject.lot = :lot ";
            a = true;
        }
        if (end != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.classificationDate BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.classificationDate = :date1";
            b = true;
        }
        if (type != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " t.type = :type";
            d = true;
        }
        try {
            TypedQuery<Classification> query = em.createQuery(queryString, Classification.class);
            if (a) {
                query.setParameter("lot", lot);
            }
            if (b) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (c) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (d) {
                query.setParameter("type", type);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Classification> findClassificationEntities(Farm farm, Date start, Date end, ClassificationTypeEnum type) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT t FROM Classification t";
        if (farm != null || start != null || end != null || type != null) {
            queryString += " WHERE";
        }
        if (farm != null) {
            queryString += " t.cultivation.moduleObject.lot.farm = :farm ";
            a = true;
        }
        if (end != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.classificationDate BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.classificationDate = :date1";
            b = true;
        }
        if (type != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " t.type = :type";
            d = true;
        }
        try {
            TypedQuery<Classification> query = em.createQuery(queryString, Classification.class);
            if (a) {
                query.setParameter("farm", farm);
            }
            if (b) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (c) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (d) {
                query.setParameter("type", type);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
