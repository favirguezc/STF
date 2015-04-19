/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.finanzas;

import data.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.finanzas.ventas.Venta;
import model.administration.Farm;
import model.administration.Person;

/**
 *
 * @author JohnFredy
 */
public class VentaDAO {
    
    private EntityManagerFactory emf = null;
    
    public VentaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
   
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Venta venta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            venta = em.merge(venta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = venta.getId();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
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
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }
    
    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Venta.class).get("dateVenta")));
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
    
    public Venta findVenta(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Venta> findVentaEntities(Person cliente, Date start, Date end) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT t FROM Venta t";
        if (cliente != null || start != null || end != null) {
            queryString += " WHERE";
        }
        if (cliente != null) {
            queryString += " t.cliente = :cliente";
            a = true;
        }
        if (end != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.dateVenta BETWEEN :date1 AND :date2";
            b = c = true;
        } else if (start != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.dateVenta = :date1";
            b = true;
        }
        queryString += " ORDER BY t.dateVenta ASC";
        try {
            TypedQuery<Venta> query = em.createQuery(queryString, Venta.class);
            if (b) {
                query.setParameter("date1", start, TemporalType.DATE);
            }
            if (c) {
                query.setParameter("date2", end, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("cliente", cliente);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Venta> findVentaEntitiesForSelectedFarm(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Venta> query = em.createQuery("SELECT v FROM Venta v WHERE v.farm = :farm  ORDER BY v.dateVenta ASC", Venta.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}