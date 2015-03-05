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
import datos.exceptions.NonexistentEntityException;
import datos.exceptions.PreexistingEntityException;
import java.util.Date;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import modelo.finanzas.costo.Costo;
import modelo.finanzas.costo.TipoCosto;

/**
 *
 * @author John Fredy
 */
public class CostoDAO implements Serializable {

    public CostoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Costo costo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(costo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCosto(costo.getId()) != null) {
                throw new PreexistingEntityException("Costo " + costo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Costo costo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            costo = em.merge(costo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = costo.getId();
                if (findCosto(id) == null) {
                    throw new NonexistentEntityException("The costo with id " + id + " no longer exists.");
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
            Costo costo;
            try {
                costo = em.getReference(Costo.class, id);
                costo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The costo with id " + id + " no longer exists.", enfe);
            }
            em.remove(costo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Costo> findCostoEntities() {
        return findCostoEntities(true, -1, -1);
    }

    public List<Costo> findCostoEntities(int maxResults, int firstResult) {
        return findCostoEntities(false, maxResults, firstResult);
    }

    private List<Costo> findCostoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Costo.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Costo.class).get("fecha")));
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

    public Costo findCosto(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Costo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCostoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Costo> rt = cq.from(Costo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Costo> findCostoEntities(TipoCosto tipo, Date inicio, Date fin) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT c FROM Costo c";
        if (tipo != null || inicio != null || fin != null) {
            queryString += " WHERE";
        }
        if (tipo != null) {
            queryString += " c.tipo = :tipo";
            a = true;
        }
        if (fin != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " c.fecha BETWEEN :fecha1 AND :fecha2";
            b = c = true;
        } else if (inicio != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " c.fecha = :fecha1";
            b = true;
        }
        queryString += " ORDER BY c.fecha ASC";
        try {
            TypedQuery<Costo> query = em.createQuery(queryString, Costo.class);
            if (b) {
                query.setParameter("fecha1", inicio, TemporalType.DATE);
            }
            if (c) {
                query.setParameter("fecha2", fin, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("tipo", tipo);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
