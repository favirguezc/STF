/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.finanzas;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import modelo.finanzas.caja.ConceptoCaja;
import modelo.finanzas.caja.Caja;
import modelo.produccion.administracion.Finca;

/**
 *
 * @author JohnFredy
 */
public class ConceptoCajaDAO implements Serializable{
    
    public ConceptoCajaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConceptoCaja conceptoCaja) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(conceptoCaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConceptoCaja conceptoCaja) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            conceptoCaja = em.merge(conceptoCaja);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = conceptoCaja.getId();
                if (findConceptoCaja(id) == null) {
                    throw new NonexistentEntityException("The conceptoCaja with id " + id + " no longer exists.");
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
            ConceptoCaja conceptoCaja;
            try {
                conceptoCaja = em.getReference(ConceptoCaja.class, id);
                conceptoCaja.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conceptoCaja with id " + id + " no longer exists.", enfe);
            }
            em.remove(conceptoCaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConceptoCaja> findConceptoCajaEntities() {
        return findConceptoCajaEntities(true, -1, -1);
    }

    public List<ConceptoCaja> findConceptoCajaEntities(int maxResults, int firstResult) {
        return findConceptoCajaEntities(false, maxResults, firstResult);
    }

    private List<ConceptoCaja> findConceptoCajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConceptoCaja.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(ConceptoCaja.class).get("fecha")));
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
   
    public ConceptoCaja findConceptoCaja(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConceptoCaja.class, id);
        } finally {
            em.close();
        }
    }
    
    public ConceptoCaja findConceptoCaja(String descripcion) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ConceptoCaja> query = em.createQuery("SELECT cc FROM ConceptoCaja cc WHERE cc.descripcion = :descripcion", ConceptoCaja.class);
            query.setParameter("descripcion", descripcion);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public ConceptoCaja findConceptoCaja(String descripcion, Caja caja) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ConceptoCaja> query = em.createQuery("SELECT t FROM ConceptoCaja t WHERE t.descripcion = :descripcion AND t.caja.id = :caja_id", ConceptoCaja.class);
            query.setParameter("descripcion", descripcion);
            query.setParameter("caja_id", caja.getId());
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<ConceptoCaja> findConceptoCajaEntities(Caja caja) {
        EntityManager em = getEntityManager();
        String queryString ="SELECT t FROM ConceptoCaja t";
        if(caja != null){
            queryString += " WHERE t.caja = :caja";
        }
        queryString += " ORDER BY t.fecha ASC";
        try {
            TypedQuery<ConceptoCaja> query = em.createQuery(queryString, ConceptoCaja.class);
            if(caja !=null){
                query.setParameter("caja", caja);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public int getConceptoCajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConceptoCaja> rt = cq.from(ConceptoCaja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<ConceptoCaja> findConceptoCajaEntitiesForSelectedFarm(Finca selectedFarm) {
        EntityManager em = getEntityManager();
        String queryString ="SELECT t FROM ConceptoCaja t WHERE t.caja.finca = :finca ORDER BY t.fecha ASC";
        try {
            TypedQuery<ConceptoCaja> query = em.createQuery(queryString, ConceptoCaja.class);
            query.setParameter("finca", selectedFarm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
