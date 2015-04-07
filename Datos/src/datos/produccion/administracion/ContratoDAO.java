/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.produccion.administracion;

import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.produccion.administracion.Contrato;
import modelo.produccion.administracion.Finca;
import modelo.produccion.administracion.Persona;
import modelo.produccion.administracion.Rol;

/**
 *
 * @author fredy
 */
public class ContratoDAO implements Serializable {

    public ContratoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contrato contrato) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(contrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contrato contrato) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            contrato = em.merge(contrato);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = contrato.getId();
                if (findContrato(id) == null) {
                    throw new NonexistentEntityException("The contrato with id " + id + " no longer exists.");
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
            Contrato contrato;
            try {
                contrato = em.getReference(Contrato.class, id);
                contrato.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contrato with id " + id + " no longer exists.", enfe);
            }
            em.remove(contrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contrato> findContratoEntities(Persona persona) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Contrato> query = em.createQuery("SELECT f FROM Contrato f WHERE f.persona = :persona", Contrato.class);
            query.setParameter("persona", persona);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Contrato> findContratoEntities(Finca finca) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Contrato> query = em.createQuery("SELECT f FROM Contrato f WHERE f.finca = :finca", Contrato.class);
            query.setParameter("finca", finca);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Contrato> findContratoEntities(Persona persona, Finca finca) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Contrato> query = em.createQuery("SELECT f FROM Contrato f WHERE f.persona = :persona AND f.finca = :finca", Contrato.class);
            query.setParameter("persona", persona);
            query.setParameter("finca", finca);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Finca> findFincaEntities(Persona user) {
        List<Finca> resultList = new LinkedList<>();
        for (Contrato c : findContratoEntities(user)) {
            if (c.getFinca() != null && !resultList.contains(c.getFinca())) {
                resultList.add(c.getFinca());
            }
        }
        return resultList;
    }

    public List<Persona> findPersonaEntities(Finca farm) {
        List<Persona> resultList = new LinkedList<>();
        for (Contrato c : findContratoEntities(farm)) {
            if (!resultList.contains(c.getPersona())) {
                resultList.add(c.getPersona());
            }
        }
        return resultList;
    }

    public List<Rol> findRolEntities(Persona user, Finca farm) {
        EntityManager em = getEntityManager();
        List<Rol> resultList = new LinkedList<>();
        for (Contrato c : findContratoEntities(user, farm)) {
            if (!resultList.contains(c.getRol())) {
                resultList.add(c.getRol());
            }
        }
        return resultList;
    }

    public List<Persona> findPersonaEntities(Rol rol, Finca selectedFarm) {
        List<Persona> personas = new LinkedList<>();
        for (Contrato c : findContratoEntities(selectedFarm)) {
            if (c.getRol() == rol) {
                personas.add(c.getPersona());
            }
        }
        return personas;
    }

    public List<Contrato> findContratoEntities() {
        return findContratoEntities(true, -1, -1);
    }

    public List<Contrato> findContratoEntities(int maxResults, int firstResult) {
        return findContratoEntities(false, maxResults, firstResult);
    }

    private List<Contrato> findContratoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contrato.class));
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

    public Contrato findContrato(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contrato.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contrato> rt = cq.from(Contrato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
