/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.administration;

import data.exceptions.NonexistentEntityException;
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
public class ContractDAO implements Serializable {

    public ContractDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contract contract) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(contract);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contract contract) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            contract = em.merge(contract);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = contract.getId();
                if (findContract(id) == null) {
                    throw new NonexistentEntityException("The contract with id " + id + " no longer exists.");
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
            Contract contract;
            try {
                contract = em.getReference(Contract.class, id);
                contract.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contract with id " + id + " no longer exists.", enfe);
            }
            em.remove(contract);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contract> findContractEntities(Person person) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Contract> query = em.createQuery("SELECT f FROM Contract f WHERE f.person = :person", Contract.class);
            query.setParameter("person", person);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Contract> findContractEntities(Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Contract> query = em.createQuery("SELECT f FROM Contract f WHERE f.farm = :farm", Contract.class);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Contract> findContractEntities(Person person, Farm farm) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Contract> query = em.createQuery("SELECT f FROM Contract f WHERE f.person = :person AND f.farm = :farm", Contract.class);
            query.setParameter("person", person);
            query.setParameter("farm", farm);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Farm> findFarmEntities(Person user) {
        List<Farm> resultList = new LinkedList<>();
        for (Contract c : findContractEntities(user)) {
            if (c.getFarm() != null && !resultList.contains(c.getFarm())) {
                resultList.add(c.getFarm());
            }
        }
        return resultList;
    }

    public List<Person> findPersonEntities(Farm farm) {
        List<Person> resultList = new LinkedList<>();
        for (Contract c : findContractEntities(farm)) {
            if (!resultList.contains(c.getPerson())) {
                resultList.add(c.getPerson());
            }
        }
        return resultList;
    }

    public List<RoleEnum> findRolEntities(Person user, Farm farm) {
        EntityManager em = getEntityManager();
        List<RoleEnum> resultList = new LinkedList<>();
        for (Contract c : findContractEntities(user, farm)) {
            if (!resultList.contains(c.getRoleEnum())) {
                resultList.add(c.getRoleEnum());
            }
        }
        return resultList;
    }

    public List<Person> findPersonEntities(RoleEnum rol, Farm selectedFarm) {
        List<Person> persons = new LinkedList<>();
        for (Contract c : findContractEntities(selectedFarm)) {
            if (c.getRoleEnum() == rol) {
                persons.add(c.getPerson());
            }
        }
        return persons;
    }

    public List<Contract> findContractEntities() {
        return findContractEntities(true, -1, -1);
    }

    public List<Contract> findContractEntities(int maxResults, int firstResult) {
        return findContractEntities(false, maxResults, firstResult);
    }

    private List<Contract> findContractEntities(boolean all, int maxResults, int firstResult) {
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

    public Contract findContract(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contract.class, id);
        } finally {
            em.close();
        }
    }

    public int getContractCount() {
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
