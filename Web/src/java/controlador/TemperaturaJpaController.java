/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Temperatura;

/**
 *
 * @author fredy
 */
public class TemperaturaJpaController implements Serializable {

    public TemperaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Temperatura temperatura) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(temperatura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTemperatura(temperatura.getId()) != null) {
                throw new PreexistingEntityException("Temperatura " + temperatura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Temperatura temperatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            temperatura = em.merge(temperatura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = temperatura.getId();
                if (findTemperatura(id) == null) {
                    throw new NonexistentEntityException("The temperatura with id " + id + " no longer exists.");
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
            Temperatura temperatura;
            try {
                temperatura = em.getReference(Temperatura.class, id);
                temperatura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The temperatura with id " + id + " no longer exists.", enfe);
            }
            em.remove(temperatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Temperatura> findTemperaturaEntities() {
        return findTemperaturaEntities(true, -1, -1);
    }

    public List<Temperatura> findTemperaturaEntities(int maxResults, int firstResult) {
        return findTemperaturaEntities(false, maxResults, firstResult);
    }

    private List<Temperatura> findTemperaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Temperatura.class));
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

    public Temperatura findTemperatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Temperatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getTemperaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Temperatura> rt = cq.from(Temperatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
