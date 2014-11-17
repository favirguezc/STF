package dao.administracion;

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
import modelo.administracion.Persona;
import modelo.administracion.Rol;
import modelo.administracion.RolPersona;

/**
 *
 * @author fredy
 */
public class RolPersonaDAO implements Serializable {

    public RolPersonaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RolPersona rolPersona) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rolPersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RolPersona rolPersona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            rolPersona = em.merge(rolPersona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = rolPersona.getId();
                if (findRolPersona(id) == null) {
                    throw new NonexistentEntityException("The rolPersona with id " + id + " no longer exists.");
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
            RolPersona rolPersona;
            try {
                rolPersona = em.getReference(RolPersona.class, id);
                rolPersona.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolPersona with id " + id + " no longer exists.", enfe);
            }
            em.remove(rolPersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RolPersona> findRolPersonaEntities() {
        return findRolPersonaEntities(true, -1, -1);
    }

    public List<RolPersona> findRolPersonaEntities(int maxResults, int firstResult) {
        return findRolPersonaEntities(false, maxResults, firstResult);
    }

    private List<RolPersona> findRolPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RolPersona.class));
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

    public RolPersona findRolPersona(long id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            return em.find(RolPersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RolPersona> rt = cq.from(RolPersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<RolPersona> findPersonaEntities(Rol rol) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<RolPersona> query = em.createQuery("SELECT t FROM RolPersona t WHERE t.rol = :r", RolPersona.class);
            query.setParameter("r", rol);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<RolPersona> findRolEntities(Persona persona) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<RolPersona> query = em.createQuery("SELECT t FROM RolPersona t WHERE t.persona = :p", RolPersona.class);
            query.setParameter("p", persona);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public RolPersona findRolPersona(Persona p, Rol r) throws Exception{
        EntityManager em = getEntityManager();
        try {
            TypedQuery<RolPersona> query = em.createQuery("SELECT t FROM RolPersona t WHERE t.persona = :p AND t.rol = :rol", RolPersona.class);
            query.setParameter("p", p);
            query.setParameter("rol", r);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}
