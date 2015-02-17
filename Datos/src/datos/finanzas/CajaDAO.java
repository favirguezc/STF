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
import java.util.ArrayList;
import modelo.finanzas.caja.Caja;
import modelo.finanzas.caja.ConceptoCaja;

/**
 *
 * @author JohnFredy
 */
public class CajaDAO implements Serializable {
    
    public CajaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Caja caja) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(caja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Caja caja) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caja persistentCaja = em.find(Caja.class, caja.getId());
            List<ConceptoCaja> conceptosOld = persistentCaja.getConceptos();
            List<ConceptoCaja> conceptosNew = caja.getConceptos();
            List<ConceptoCaja> attachedConceptosNew = new ArrayList<ConceptoCaja>();
            for (ConceptoCaja conceptosNewConceptoCajaToAttach : conceptosNew) {
                conceptosNewConceptoCajaToAttach = em.getReference(conceptosNewConceptoCajaToAttach.getClass(), conceptosNewConceptoCajaToAttach.getId());
                attachedConceptosNew.add(conceptosNewConceptoCajaToAttach);
            }
            conceptosNew = attachedConceptosNew;
            caja.setConceptos(conceptosNew);
            caja = em.merge(caja);
            for (ConceptoCaja conceptosOldConceptoCaja : conceptosOld) {
                if (!conceptosNew.contains(conceptosOldConceptoCaja)) {
                    conceptosOldConceptoCaja.setCaja(null);
                    conceptosOldConceptoCaja = em.merge(conceptosOldConceptoCaja);
                }
            }
            for (ConceptoCaja conceptosNewConceptoCaja : conceptosNew) {
                if (!conceptosOld.contains(conceptosNewConceptoCaja)) {
                    Caja oldCajaOfConceptosNewConceptoCaja = conceptosNewConceptoCaja.getCaja();
                    conceptosNewConceptoCaja.setCaja(caja);
                    conceptosNewConceptoCaja = em.merge(conceptosNewConceptoCaja);
                    if (oldCajaOfConceptosNewConceptoCaja != null && !oldCajaOfConceptosNewConceptoCaja.equals(caja)) {
                        oldCajaOfConceptosNewConceptoCaja.getConceptos().remove(conceptosNewConceptoCaja);
                        oldCajaOfConceptosNewConceptoCaja = em.merge(oldCajaOfConceptosNewConceptoCaja);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = caja.getId();
                if (findCaja(id) == null) {
                    throw new NonexistentEntityException("The caja with id " + id + " no longer exists.");
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
            Caja caja;
            try {
                caja = em.getReference(Caja.class, id);
                caja.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caja with id " + id + " no longer exists.", enfe);
            }
            List<ConceptoCaja> conceptos = caja.getConceptos();
            for (ConceptoCaja conceptosConceptoCaja : conceptos) {
                conceptosConceptoCaja.setCaja(null);
                em.remove(conceptosConceptoCaja);
            }
            em.remove(caja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Caja> findCajaEntities() {
        return findCajaEntities(true, -1, -1);
    }

    public List<Caja> findCajaEntities(int maxResults, int firstResult) {
        return findCajaEntities(false, maxResults, firstResult);
    }

    private List<Caja> findCajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Caja.class));
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

    public Caja findCaja(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Caja.class, id);
        } finally {
            em.close();
        }
    }
    
    public Caja findCaja(String name) throws Exception {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Caja> query = em.createQuery("SELECT c FROM Caja c WHERE c.nombre = :nombre", Caja.class);
            query.setParameter("nombre", name);
            Caja caja = query.getSingleResult();
            caja.getConceptos();
            return caja;
        } finally {
            em.close();
        }
    }

    public int getCajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Caja> rt = cq.from(Caja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void eliminarConcepto(long id, long idConcepto) throws NonexistentEntityException, Exception{
        EntityManager em = getEntityManager();
        ConceptoCajaDAO conceptoDAO = new ConceptoCajaDAO(emf);
            Caja caja = findCaja(id);
            List<ConceptoCaja> conceptos = caja.getConceptos();
            ConceptoCaja concepto = conceptoDAO.findConceptoCaja(idConcepto);
            conceptoDAO.destroy(idConcepto);
            conceptos.remove(concepto);
            caja.setConceptos(conceptos);
            edit(caja);
    }
}
