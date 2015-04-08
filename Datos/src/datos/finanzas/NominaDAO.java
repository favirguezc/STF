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
import java.util.Date;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import modelo.finanzas.nomina.Nomina;
import modelo.produccion.administracion.Finca;
import modelo.produccion.administracion.Persona;

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

    public void create(Nomina nomina) {
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

    public void edit(Nomina nomina) throws NonexistentEntityException, Exception {
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
            Nomina nomina;
            try {
                nomina = em.getReference(Nomina.class, id);
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

    public List<Nomina> findNominaEntities() {
        return findNominaEntities(true, -1, -1);
    }

    public List<Nomina> findNominaEntities(int maxResults, int firstResult) {
        return findNominaEntities(false, maxResults, firstResult);
    }

    private List<Nomina> findNominaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nomina.class));
            Query q = em.createQuery(cq);
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Nomina.class).get("fecha")));
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Nomina findNomina(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nomina.class, id);
        } finally {
            em.close();
        }
    }

    public int getNominaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nomina> rt = cq.from(Nomina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Nomina> findNominaEntities(Finca finca, Persona trabajador, Date inicio, Date fin) {
        EntityManager em = getEntityManager();
        boolean a, b, c;
        a = b = c = false;
        String queryString = "SELECT t FROM Nomina t WHERE t.finca = :finca";
        if (trabajador != null) {
            queryString += "AND t.trabajador = :trabajador";
            a = true;
        }
        if (fin != null) {
            queryString += " AND t.fecha BETWEEN :fecha1 AND :fecha2";
            b = c = true;
        } else if (inicio != null) {
            queryString += " AND t.fecha = :fecha1";
            b = true;
        }
        queryString += " ORDER BY t.fecha ASC";
        try {
            TypedQuery<Nomina> query = em.createQuery(queryString, Nomina.class);
            query.setParameter("finca", finca);
            if (b) {
                query.setParameter("fecha1", inicio, TemporalType.DATE);
            }
            if (c) {
                query.setParameter("fecha2", fin, TemporalType.DATE);
            }
            if (a) {
                query.setParameter("trabajador", trabajador);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Nomina> findNominaEntitiesForSelectedFarm(Finca finca) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Nomina> query = em.createQuery("SELECT n FROM Nomina n WHERE n.finca = :finca  ORDER BY n.fecha ASC", Nomina.class);
            query.setParameter("finca", finca);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
