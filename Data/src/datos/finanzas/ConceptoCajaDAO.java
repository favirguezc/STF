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
import data.exceptions.NonexistentEntityException;
import java.io.Serializable;
import model.finances.cash.CashConcept;
import model.finances.cash.Cash;
import model.administration.Farm;

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

    public void create(CashConcept conceptoCaja) {
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

    public void edit(CashConcept conceptoCaja) throws NonexistentEntityException, Exception {
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
            CashConcept conceptoCaja;
            try {
                conceptoCaja = em.getReference(CashConcept.class, id);
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

    public List<CashConcept> findConceptoCajaEntities() {
        return findConceptoCajaEntities(true, -1, -1);
    }

    public List<CashConcept> findConceptoCajaEntities(int maxResults, int firstResult) {
        return findConceptoCajaEntities(false, maxResults, firstResult);
    }

    private List<CashConcept> findConceptoCajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CashConcept.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(CashConcept.class).get("date")));
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
   
    public CashConcept findConceptoCaja(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CashConcept.class, id);
        } finally {
            em.close();
        }
    }
    
    public CashConcept findConceptoCaja(String descripcion) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<CashConcept> query = em.createQuery("SELECT cc FROM ConceptoCaja cc WHERE cc.descripcion = :descripcion", CashConcept.class);
            query.setParameter("descripcion", descripcion);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public CashConcept findConceptoCaja(String descripcion, Cash caja) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<CashConcept> query = em.createQuery("SELECT t FROM ConceptoCaja t WHERE t.descripcion = :descripcion AND t.caja.id = :caja_id", CashConcept.class);
            query.setParameter("descripcion", descripcion);
            query.setParameter("caja_id", caja.getId());
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<CashConcept> findConceptoCajaEntities(Cash caja) {
        EntityManager em = getEntityManager();
        String queryString ="SELECT t FROM ConceptoCaja t";
        if(caja != null){
            queryString += " WHERE t.caja = :caja";
        }
        queryString += " ORDER BY t.date ASC";
        try {
            TypedQuery<CashConcept> query = em.createQuery(queryString, CashConcept.class);
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
            Root<CashConcept> rt = cq.from(CashConcept.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<CashConcept> findConceptoCajaEntitiesForSelectedFarm(Farm selectedFarm) {
        EntityManager em = getEntityManager();
        String queryString ="SELECT t FROM ConceptoCaja t WHERE t.caja.farm = :farm ORDER BY t.date ASC";
        try {
            TypedQuery<CashConcept> query = em.createQuery(queryString, CashConcept.class);
            query.setParameter("farm", selectedFarm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
