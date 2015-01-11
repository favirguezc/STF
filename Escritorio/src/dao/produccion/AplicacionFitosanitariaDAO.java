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
import modelo.produccion.administracion.Lote;
import modelo.produccion.aplicaciones.Aplicacion;

/**
 *
 * @author fredy
 */
public class AplicacionFitosanitariaDAO implements Serializable {

    public AplicacionFitosanitariaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aplicacion aplicacionFitosanitaria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(aplicacionFitosanitaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aplicacion aplicacionFitosanitaria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            aplicacionFitosanitaria = em.merge(aplicacionFitosanitaria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = aplicacionFitosanitaria.getId();
                if (findAplicacionFitosanitaria(id) == null) {
                    throw new NonexistentEntityException("The aplicacionFitosanitaria with id " + id + " no longer exists.");
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
            Aplicacion aplicacionFitosanitaria;
            try {
                aplicacionFitosanitaria = em.getReference(Aplicacion.class, id);
                aplicacionFitosanitaria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aplicacionFitosanitaria with id " + id + " no longer exists.", enfe);
            }
            em.remove(aplicacionFitosanitaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aplicacion> findAplicacionFitosanitariaEntities() {
        return findAplicacionFitosanitariaEntities(true, -1, -1);
    }

    public List<Aplicacion> findAplicacionFitosanitariaEntities(int maxResults, int firstResult) {
        return findAplicacionFitosanitariaEntities(false, maxResults, firstResult);
    }

    private List<Aplicacion> findAplicacionFitosanitariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aplicacion.class));
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

    public Aplicacion findAplicacionFitosanitaria(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aplicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAplicacionFitosanitariaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aplicacion> rt = cq.from(Aplicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Aplicacion> findAplicacionFitosanitariaEntities(Lote lote,Date fecha1,Date fecha2) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Aplicacion> query = em.createQuery("SELECT t FROM AplicacionFitosanitaria t WHERE t.fecha BETWEEN :fecha1 AND :fecha2 AND t.lote = :lote", Aplicacion.class);
            query.setParameter("fecha1", fecha1, TemporalType.DATE);
            query.setParameter("fecha2", fecha2,TemporalType.DATE);
            query.setParameter("lote", lote);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
