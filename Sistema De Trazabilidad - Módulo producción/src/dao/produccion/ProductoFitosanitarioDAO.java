/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.produccion;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.produccion.ProductoFitosanitario;

/**
 *
 * @author fredy
 */
public class ProductoFitosanitarioDAO implements Serializable {

    public ProductoFitosanitarioDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoFitosanitario productoFitosanitario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(productoFitosanitario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoFitosanitario productoFitosanitario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            productoFitosanitario = em.merge(productoFitosanitario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = productoFitosanitario.getId();
                if (findProductoFitosanitario(id) == null) {
                    throw new NonexistentEntityException("The productoFitosanitario with id " + id + " no longer exists.");
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
            ProductoFitosanitario productoFitosanitario;
            try {
                productoFitosanitario = em.getReference(ProductoFitosanitario.class, id);
                productoFitosanitario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoFitosanitario with id " + id + " no longer exists.", enfe);
            }
            em.remove(productoFitosanitario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductoFitosanitario> findProductoFitosanitarioEntities() {
        return findProductoFitosanitarioEntities(true, -1, -1);
    }

    public List<ProductoFitosanitario> findProductoFitosanitarioEntities(int maxResults, int firstResult) {
        return findProductoFitosanitarioEntities(false, maxResults, firstResult);
    }

    private List<ProductoFitosanitario> findProductoFitosanitarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoFitosanitario.class));
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

    public ProductoFitosanitario findProductoFitosanitario(long id) throws Exception{
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoFitosanitario.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoFitosanitarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoFitosanitario> rt = cq.from(ProductoFitosanitario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public ProductoFitosanitario findProductoFitosanitario(String nombre)throws Exception{
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ProductoFitosanitario> query = em.createQuery("SELECT t FROM ProductoFitosanitario t WHERE t.nombre = :nombre", ProductoFitosanitario.class);
            query.setParameter("nombre", nombre);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
}
