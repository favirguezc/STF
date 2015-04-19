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
import model.administration.Contract;
import model.administration.Farm;
import model.administration.Person;
import model.administration.RoleEnum;

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

    public void create(Contract contrato) {
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

    public void edit(Contract contrato) throws NonexistentEntityException, Exception {
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
            Contract contrato;
            try {
                contrato = em.getReference(Contract.class, id);
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

    public List<Contract> findContratoEntities(Person persona) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Contract> query = em.createQuery("SELECT f FROM Contrato f WHERE f.persona = :persona", Contract.class);
            query.setParameter("persona", persona);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Contract> findContratoEntities(Farm finca) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Contract> query = em.createQuery("SELECT f FROM Contrato f WHERE f.finca = :finca", Contract.class);
            query.setParameter("finca", finca);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Contract> findContratoEntities(Person persona, Farm finca) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Contract> query = em.createQuery("SELECT f FROM Contrato f WHERE f.persona = :persona AND f.finca = :finca", Contract.class);
            query.setParameter("persona", persona);
            query.setParameter("finca", finca);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Farm> findFincaEntities(Person user) {
        List<Farm> resultList = new LinkedList<>();
        for (Contract c : findContratoEntities(user)) {
            if (c.getFarm() != null && !resultList.contains(c.getFarm())) {
                resultList.add(c.getFarm());
            }
        }
        return resultList;
    }

    public List<Person> findPersonaEntities(Farm farm) {
        List<Person> resultList = new LinkedList<>();
        for (Contract c : findContratoEntities(farm)) {
            if (!resultList.contains(c.getPerson())) {
                resultList.add(c.getPerson());
            }
        }
        return resultList;
    }

    public List<RoleEnum> findRolEntities(Person user, Farm farm) {
        EntityManager em = getEntityManager();
        List<RoleEnum> resultList = new LinkedList<>();
        for (Contract c : findContratoEntities(user, farm)) {
            if (!resultList.contains(c.getRoleEnum())) {
                resultList.add(c.getRoleEnum());
            }
        }
        return resultList;
    }

    public List<Person> findPersonaEntities(RoleEnum rol, Farm selectedFarm) {
        List<Person> personas = new LinkedList<>();
        for (Contract c : findContratoEntities(selectedFarm)) {
            if (c.getRoleEnum() == rol) {
                personas.add(c.getPerson());
            }
        }
        return personas;
    }

    public List<Contract> findContratoEntities() {
        return findContratoEntities(true, -1, -1);
    }

    public List<Contract> findContratoEntities(int maxResults, int firstResult) {
        return findContratoEntities(false, maxResults, firstResult);
    }

    private List<Contract> findContratoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contract.class));
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

    public Contract findContrato(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contract.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contract> rt = cq.from(Contract.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
