/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.finances.payroll;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import data.exceptions.NonexistentEntityException;
import java.util.Date;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import model.finances.payroll.Payroll;
import model.administration.Farm;
import model.administration.Person;

/**
 *
 * @author John Fredy
 */
public class PayrollDAO implements Serializable {

    public PayrollDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Payroll payroll) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(payroll);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Payroll payroll) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            payroll = em.merge(payroll);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = payroll.getId();
                if (findPayroll(id) == null) {
                    throw new NonexistentEntityException("The payroll with id " + id + " no longer exists.");
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
            Payroll payroll;
            try {
                payroll = em.getReference(Payroll.class, id);
                payroll.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The payroll with id " + id + " no longer exists.", enfe);
            }
            em.remove(payroll);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Payroll> findPayrollEntities() {
        return findPayrollEntities(true, -1, -1);
    }

    public List<Payroll> findPayrollEntities(int maxResults, int firstResult) {
        return findPayrollEntities(false, maxResults, firstResult);
    }

    private List<Payroll> findPayrollEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Payroll.class));
            Query q = em.createQuery(cq);
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Payroll.class).get("dateUntil")));
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Payroll findPayroll(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Payroll.class, id);
        } finally {
            em.close();
        }
    }

    public int getPayrollCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Payroll> rt = cq.from(Payroll.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Payroll> findPayrollEntities(Farm farm, Person worker, Date start, Date end) {
        EntityManager em = getEntityManager();
        boolean a, b, c;
        a = b = c = false;
        String queryString = "SELECT p FROM Payroll p WHERE p.farm = :farm";
        if (worker != null) {
            queryString += "AND p.worker = :worker";
            a = true;
        }
        if (end != null) {
            queryString += " AND p.dateUntil BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            queryString += " AND p.dateUntil = :date1";
            b = true;
        }
        queryString += " ORDER BY p.dateUntil ASC";
        try {
            TypedQuery<Payroll> query = em.createQuery(queryString, Payroll.class);
            query.setParameter("farm", farm);
            if (b) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (c) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("worker", worker);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Payroll> findPayrollEntitiesForSelectedFarm(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Payroll> query = em.createQuery("SELECT p FROM Payroll p WHERE p.farm = :farm  ORDER BY p.dateUntil ASC", Payroll.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
