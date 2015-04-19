/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.finanzas;

import data.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.finanzas.Precio;

/**
 *
 * @author John Fredy
 */
public class PrecioDAO {
    
    public PrecioDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Precio precio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(precio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Precio precio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            precio = em.merge(precio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = precio.getId();
                if (findPrecio(id) == null) {
                    throw new NonexistentEntityException("The precio with id " + id + " no longer exists.");
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
            Precio precio;
            try {
                precio = em.getReference(Precio.class, id);
                precio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The precio with id " + id + " no longer exists.", enfe);
            }
            em.remove(precio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Precio> findPrecioEntities() {
        return findPrecioEntities(true, -1, -1);
    }

    public List<Precio> findPrecioEntities(int maxResults, int firstResult) {
        return findPrecioEntities(false, maxResults, firstResult);
    }

    private List<Precio> findPrecioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Precio.class));
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

    public Precio findPrecio(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Precio.class, id);
        } finally {
            em.close();
        }
    }
    
    public Precio findPrecio(String item) {
        EntityManager em = getEntityManager();
        String queryString = "SELECT t FROM Precio t";
        if (item != null) {
            queryString += " WHERE t.item = :item";
        }
        try {
            TypedQuery<Precio> query = em.createQuery(queryString, Precio.class);
            query.setParameter("item", item);
            List<Precio> result = query.getResultList();
            if(result == null || result.isEmpty()){
                return null;
            }
            return result.get(0);
        } finally {
            em.close();
        }
    }

    public int getPrecioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Precio> rt = cq.from(Precio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
