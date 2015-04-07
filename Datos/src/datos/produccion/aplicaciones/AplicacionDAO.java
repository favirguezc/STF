/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.aplicaciones;

import datos.exceptions.NonexistentEntityException;
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
import modelo.produccion.administracion.Finca;
import modelo.produccion.administracion.Lote;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.aplicaciones.Aplicacion;

/**
 *
 * @author fredy
 */
public class AplicacionDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public AplicacionDAO(EntityManagerFactory emf) {
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
     * @param aplicacionFitosanitaria
     */
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

    /**
     *
     * @param aplicacionFitosanitaria
     * @throws NonexistentEntityException
     * @throws Exception
     */
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
                if (findAplicacion(id) == null) {
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

    /**
     *
     * @param finca
     * @return
     */
    public List<Aplicacion> findAplicacionEntitiesForSelectedFarm(Finca finca) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Aplicacion> query = em.createQuery("SELECT a FROM Aplicacion a WHERE a.modulo.lote.finca = :finca", Aplicacion.class);
            query.setParameter("finca", finca);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Aplicacion> findAplicacionEntities() {
        return AplicacionDAO.this.findAplicacionEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    public List<Aplicacion> findAplicacionEntities(int maxResults, int firstResult) {
        return findAplicacionEntities(false, maxResults, firstResult);
    }

    private List<Aplicacion> findAplicacionEntities(boolean all, int maxResults, int firstResult) {
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

    /**
     *
     * @param id
     * @return
     */
    public Aplicacion findAplicacion(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aplicacion.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public int getAplicacionCount() {
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

    /**
     *
     * @param lote
     * @param fecha1
     * @param fecha2
     * @return
     */
    public List<Aplicacion> findAplicacionEntities(Lote lote,Date fecha1,Date fecha2) {
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
