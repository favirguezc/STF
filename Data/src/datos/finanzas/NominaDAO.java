/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.finanzas;

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
public class NominaDAO implements Serializable {

    public NominaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Payroll nomina) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nomina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Payroll nomina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nomina = em.merge(nomina);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = nomina.getId();
                if (findNomina(id) == null) {
                    throw new NonexistentEntityException("The nomina with id " + id + " no longer exists.");
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
            Payroll nomina;
            try {
                nomina = em.getReference(Payroll.class, id);
                nomina.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomina with id " + id + " no longer exists.", enfe);
            }
            em.remove(nomina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Payroll> findNominaEntities() {
        return findNominaEntities(true, -1, -1);
    }

    public List<Payroll> findNominaEntities(int maxResults, int firstResult) {
        return findNominaEntities(false, maxResults, firstResult);
    }

    private List<Payroll> findNominaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Payroll.class));
            Query q = em.createQuery(cq);
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Payroll.class).get("date")));
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Payroll findNomina(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Payroll.class, id);
        } finally {
            em.close();
        }
    }

    public int getNominaCount() {
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
    
    public List<Payroll> findNominaEntities(Farm farm, Person trabajador, Date start, Date end) {
        EntityManager em = getEntityManager();
        boolean a, b, c;
        a = b = c = false;
        String queryString = "SELECT t FROM Nomina t WHERE t.farm = :farm";
        if (trabajador != null) {
            queryString += "AND t.trabajador = :trabajador";
            a = true;
        }
        if (end != null) {
            queryString += " AND t.date BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            queryString += " AND t.date = :date1";
            b = true;
        }
        queryString += " ORDER BY t.date ASC";
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
                query.setParameter("trabajador", trabajador);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Payroll> findNominaEntitiesForSelectedFarm(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Payroll> query = em.createQuery("SELECT n FROM Nomina n WHERE n.farm = :farm  ORDER BY n.date ASC", Payroll.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
