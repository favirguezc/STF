/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.produccion;

import controlador.administracion.ModuloControlador;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
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
import modelo.produccion.MonitoreoDePlagas;
import modelo.administracion.Lote;
import modelo.administracion.Modulo;

/**
 *
 * @author fredy
 */
public class MonitoreoDePlagasDAO implements Serializable {

    public MonitoreoDePlagasDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MonitoreoDePlagas monitoreoDePlagas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(monitoreoDePlagas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MonitoreoDePlagas monitoreoDePlagas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            monitoreoDePlagas = em.merge(monitoreoDePlagas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = monitoreoDePlagas.getId();
                if (findMonitoreoDePlagas(id) == null) {
                    throw new NonexistentEntityException("The monitoreoDePlagas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MonitoreoDePlagas monitoreoDePlagas;
            try {
                monitoreoDePlagas = em.getReference(MonitoreoDePlagas.class, id);
                monitoreoDePlagas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The monitoreoDePlagas with id " + id + " no longer exists.", enfe);
            }
            em.remove(monitoreoDePlagas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MonitoreoDePlagas> findMonitoreoDePlagasEntities() {
        return findMonitoreoDePlagasEntities(true, -1, -1);
    }

    public List<MonitoreoDePlagas> findMonitoreoDePlagasEntities(Lote lote, Date fecha) {
        EntityManager em = getEntityManager();
        List<Modulo> listaModulos = new ModuloControlador().leerLista(lote);
        List<MonitoreoDePlagas> lista = new ArrayList<>();
        for (Modulo m : listaModulos) {
            try {
                TypedQuery<MonitoreoDePlagas> query = em.createQuery("SELECT t FROM MonitoreoDePlagas t WHERE t.fecha = :fecha AND t.modulo = :modulo", MonitoreoDePlagas.class);
                query.setParameter("fecha", fecha, TemporalType.DATE);
                query.setParameter("modulo", m);
                lista.addAll(query.getResultList());
            } catch (Exception e) {
            }
        }
        em.close();
        return lista;
    }

    public List<MonitoreoDePlagas> findMonitoreoDePlagasEntities(int maxResults, int firstResult) {
        return findMonitoreoDePlagasEntities(false, maxResults, firstResult);
    }

    private List<MonitoreoDePlagas> findMonitoreoDePlagasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MonitoreoDePlagas.class));
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

    public MonitoreoDePlagas findMonitoreoDePlagas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MonitoreoDePlagas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonitoreoDePlagasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MonitoreoDePlagas> rt = cq.from(MonitoreoDePlagas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
