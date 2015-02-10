package datos.produccion.administracion;

import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.produccion.administracion.Persona;
import modelo.produccion.administracion.Rol;
import modelo.produccion.administracion.RolPersona;

/**
 *
 * @author fredy
 */
public class RolPersonaDAO implements Serializable {

    /**
     *
     * @param emf
     */
    public RolPersonaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    /**
     *
     * @return
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     *
     * @param rolPersona
     */
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

    /**
     *
     * @param rolPersona
     * @throws NonexistentEntityException
     * @throws Exception
     */
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

    /**
     *
     * @param id
     * @throws NonexistentEntityException
     */
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

    /**
     *
     * @return
     */
    public List<RolPersona> findRolPersonaEntities() {
        return findRolPersonaEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
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

    /**
     *
     * @param id
     * @return
     */
    public RolPersona findRolPersona(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RolPersona.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @param rol
     * @return
     */
    public List<Persona> findPersonaEntities(Rol rol) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Persona> query = em.createQuery("SELECT t.persona FROM RolPersona t WHERE t.rol = :r", Persona.class);
            query.setParameter("r", rol);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param persona
     * @return
     */
    public List<Rol> findRolEntities(Persona persona) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Rol> query = em.createQuery("SELECT t.rol FROM RolPersona t WHERE t.persona = :p", Rol.class);
            query.setParameter("p", persona);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param p
     * @param r
     * @return
     * @throws Exception
     */
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
