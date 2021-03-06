/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.finances.incomes;

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
import model.administration.Farm;
import model.administration.Person;
import model.finances.incomes.Payment;

/**
 *
 * @author John Fredy
 */
public class PaymentDAO implements Serializable {

    public PaymentDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Payment payment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(payment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Payment payment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            payment = em.merge(payment);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = payment.getId();
                if (findPayment(id) == null) {
                    throw new NonexistentEntityException("The payment with id " + id + " no longer exists.");
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
            Payment payment;
            try {
                payment = em.getReference(Payment.class, id);
                payment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The payment with id " + id + " no longer exists.", enfe);
            }
            em.remove(payment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Payment> findPaymentEntities() {
        return findPaymentEntities(true, -1, -1);
    }

    public List<Payment> findPaymentEntities(int maxResults, int firstResult) {
        return findPaymentEntities(false, maxResults, firstResult);
    }

    private List<Payment> findPaymentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Payment.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Payment.class).get("paymentDate")));
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

    public Payment findPayment(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Payment.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaymentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Payment> rt = cq.from(Payment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Payment> findPaymentEntitiesForSelectedFarm(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Payment> query = em.createQuery("SELECT p FROM Payment p WHERE p.farm = :farm  ORDER BY p.paymentDate ASC", Payment.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Payment> findPaymentEntitiesWithUnusedValue(Farm farm, Person customer) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Payment> query = em.createQuery("SELECT p FROM Payment p WHERE p.farm = :farm AND p.customer = :customer AND p.usedValue <> p.paymentValue ORDER BY p.paymentDate ASC", Payment.class);
            query.setParameter("farm", farm);
            query.setParameter("customer", customer);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Payment> findPaymentEntitiesWithUnusedAndUsedValue(Farm farm, Person customer) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Payment> query = em.createQuery("SELECT p FROM Payment p WHERE p.farm = :farm AND p.customer = :customer AND p.usedValue <= p.paymentValue ORDER BY p.paymentDate ASC", Payment.class);
            query.setParameter("farm", farm);
            query.setParameter("customer", customer);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Payment> findPaymentEntities(Farm farm, Date start, Date end) {
        EntityManager em = getEntityManager();
        boolean a, b, c;
        a = b = c = false;
        String queryString = "SELECT p FROM Payment p";
        if (farm != null || start != null || end != null) {
            queryString += " WHERE";
        }
        if (farm != null) {
            queryString += " p.farm = :farm";
            a = true;
        }
        if (end != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " p.paymentDate BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " p.paymentDate = :date1";
            b = true;
        }
        queryString += " ORDER BY p.paymentDate ASC";
        try {
            TypedQuery<Payment> query = em.createQuery(queryString, Payment.class);
            if (b) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (c) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("farm", farm);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
