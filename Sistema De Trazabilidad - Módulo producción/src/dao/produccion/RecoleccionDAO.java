/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.produccion;

import dao.exceptions.NonexistentEntityException;
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
import modelo.administracion.Lote;
import modelo.administracion.Persona;
import modelo.produccion.Recoleccion;

/**
 *
 * @author fredy
 */
public class RecoleccionDAO implements Serializable {

    public RecoleccionDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recoleccion recoleccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(recoleccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recoleccion recoleccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            recoleccion = em.merge(recoleccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = recoleccion.getId();
                if (findRecoleccion(id) == null) {
                    throw new NonexistentEntityException("The recoleccion with id " + id + " no longer exists.");
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
            Recoleccion recoleccion;
            try {
                recoleccion = em.getReference(Recoleccion.class, id);
                recoleccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recoleccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(recoleccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Recoleccion> findRecoleccionEntities() {
        return findRecoleccionEntities(true, -1, -1);
    }

    public List<Recoleccion> findRecoleccionEntities(int maxResults, int firstResult) {
        return findRecoleccionEntities(false, maxResults, firstResult);
    }

    private List<Recoleccion> findRecoleccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Recoleccion.class));
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

    public Recoleccion findRecoleccion(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recoleccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecoleccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Recoleccion> rt = cq.from(Recoleccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Recoleccion> findRecoleccionEntities(Persona recolector, Lote lote, Date inicio, Date fin) {
        EntityManager em = getEntityManager();
        boolean a, b, c, d;
        a = b = c = d = false;
        String queryString = "SELECT t FROM Recoleccion t WHERE";
        if (recolector != null) {
            queryString += " t.recolector = :recolector";
            a = true;
        }
        if (lote != null) {
            if (a) {
                queryString += " AND";
            }
            queryString += " t.lote = :lote ";
            b = true;
        }
        if (fin != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " t.fecha BETWEEN :fecha1 AND :fecha2";
            c = d = true;
        } else if (inicio != null) {
            if (a || b) {
                queryString += " AND";
            }
            queryString += " t.fecha = :fecha1";
            c = true;
        }
        try {
            TypedQuery<Recoleccion> query = em.createQuery(queryString, Recoleccion.class);
            if (c) {
                query.setParameter("fecha1", inicio, TemporalType.DATE);
            }
            if (d) {
                query.setParameter("fecha2", fin, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("recolector", recolector);
            }
            if (b) {
                query.setParameter("lote", lote);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
