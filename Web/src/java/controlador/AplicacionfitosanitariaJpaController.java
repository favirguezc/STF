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
import modelo.Aplicacionfitosanitaria;
import modelo.Persona;
import modelo.Insumo;

/**
 *
 * @author fredy
 */
public class AplicacionfitosanitariaJpaController implements Serializable {

    public AplicacionfitosanitariaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aplicacionfitosanitaria aplicacionfitosanitaria) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona aprobanteId = aplicacionfitosanitaria.getAprobanteId();
            if (aprobanteId != null) {
                aprobanteId = em.getReference(aprobanteId.getClass(), aprobanteId.getId());
                aplicacionfitosanitaria.setAprobanteId(aprobanteId);
            }
            Persona asistenteId = aplicacionfitosanitaria.getAsistenteId();
            if (asistenteId != null) {
                asistenteId = em.getReference(asistenteId.getClass(), asistenteId.getId());
                aplicacionfitosanitaria.setAsistenteId(asistenteId);
            }
            Persona productorId = aplicacionfitosanitaria.getProductorId();
            if (productorId != null) {
                productorId = em.getReference(productorId.getClass(), productorId.getId());
                aplicacionfitosanitaria.setProductorId(productorId);
            }
            Insumo productoId = aplicacionfitosanitaria.getProductoId();
            if (productoId != null) {
                productoId = em.getReference(productoId.getClass(), productoId.getId());
                aplicacionfitosanitaria.setProductoId(productoId);
            }
            Persona responsableId = aplicacionfitosanitaria.getResponsableId();
            if (responsableId != null) {
                responsableId = em.getReference(responsableId.getClass(), responsableId.getId());
                aplicacionfitosanitaria.setResponsableId(responsableId);
            }
            em.persist(aplicacionfitosanitaria);
            if (aprobanteId != null) {
                aprobanteId.getAplicacionfitosanitariaList().add(aplicacionfitosanitaria);
                aprobanteId = em.merge(aprobanteId);
            }
            if (asistenteId != null) {
                asistenteId.getAplicacionfitosanitariaList().add(aplicacionfitosanitaria);
                asistenteId = em.merge(asistenteId);
            }
            if (productorId != null) {
                productorId.getAplicacionfitosanitariaList().add(aplicacionfitosanitaria);
                productorId = em.merge(productorId);
            }
            if (productoId != null) {
                productoId.getAplicacionfitosanitariaList().add(aplicacionfitosanitaria);
                productoId = em.merge(productoId);
            }
            if (responsableId != null) {
                responsableId.getAplicacionfitosanitariaList().add(aplicacionfitosanitaria);
                responsableId = em.merge(responsableId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAplicacionfitosanitaria(aplicacionfitosanitaria.getId()) != null) {
                throw new PreexistingEntityException("Aplicacionfitosanitaria " + aplicacionfitosanitaria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aplicacionfitosanitaria aplicacionfitosanitaria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aplicacionfitosanitaria persistentAplicacionfitosanitaria = em.find(Aplicacionfitosanitaria.class, aplicacionfitosanitaria.getId());
            Persona aprobanteIdOld = persistentAplicacionfitosanitaria.getAprobanteId();
            Persona aprobanteIdNew = aplicacionfitosanitaria.getAprobanteId();
            Persona asistenteIdOld = persistentAplicacionfitosanitaria.getAsistenteId();
            Persona asistenteIdNew = aplicacionfitosanitaria.getAsistenteId();
            Persona productorIdOld = persistentAplicacionfitosanitaria.getProductorId();
            Persona productorIdNew = aplicacionfitosanitaria.getProductorId();
            Insumo productoIdOld = persistentAplicacionfitosanitaria.getProductoId();
            Insumo productoIdNew = aplicacionfitosanitaria.getProductoId();
            Persona responsableIdOld = persistentAplicacionfitosanitaria.getResponsableId();
            Persona responsableIdNew = aplicacionfitosanitaria.getResponsableId();
            if (aprobanteIdNew != null) {
                aprobanteIdNew = em.getReference(aprobanteIdNew.getClass(), aprobanteIdNew.getId());
                aplicacionfitosanitaria.setAprobanteId(aprobanteIdNew);
            }
            if (asistenteIdNew != null) {
                asistenteIdNew = em.getReference(asistenteIdNew.getClass(), asistenteIdNew.getId());
                aplicacionfitosanitaria.setAsistenteId(asistenteIdNew);
            }
            if (productorIdNew != null) {
                productorIdNew = em.getReference(productorIdNew.getClass(), productorIdNew.getId());
                aplicacionfitosanitaria.setProductorId(productorIdNew);
            }
            if (productoIdNew != null) {
                productoIdNew = em.getReference(productoIdNew.getClass(), productoIdNew.getId());
                aplicacionfitosanitaria.setProductoId(productoIdNew);
            }
            if (responsableIdNew != null) {
                responsableIdNew = em.getReference(responsableIdNew.getClass(), responsableIdNew.getId());
                aplicacionfitosanitaria.setResponsableId(responsableIdNew);
            }
            aplicacionfitosanitaria = em.merge(aplicacionfitosanitaria);
            if (aprobanteIdOld != null && !aprobanteIdOld.equals(aprobanteIdNew)) {
                aprobanteIdOld.getAplicacionfitosanitariaList().remove(aplicacionfitosanitaria);
                aprobanteIdOld = em.merge(aprobanteIdOld);
            }
            if (aprobanteIdNew != null && !aprobanteIdNew.equals(aprobanteIdOld)) {
                aprobanteIdNew.getAplicacionfitosanitariaList().add(aplicacionfitosanitaria);
                aprobanteIdNew = em.merge(aprobanteIdNew);
            }
            if (asistenteIdOld != null && !asistenteIdOld.equals(asistenteIdNew)) {
                asistenteIdOld.getAplicacionfitosanitariaList().remove(aplicacionfitosanitaria);
                asistenteIdOld = em.merge(asistenteIdOld);
            }
            if (asistenteIdNew != null && !asistenteIdNew.equals(asistenteIdOld)) {
                asistenteIdNew.getAplicacionfitosanitariaList().add(aplicacionfitosanitaria);
                asistenteIdNew = em.merge(asistenteIdNew);
            }
            if (productorIdOld != null && !productorIdOld.equals(productorIdNew)) {
                productorIdOld.getAplicacionfitosanitariaList().remove(aplicacionfitosanitaria);
                productorIdOld = em.merge(productorIdOld);
            }
            if (productorIdNew != null && !productorIdNew.equals(productorIdOld)) {
                productorIdNew.getAplicacionfitosanitariaList().add(aplicacionfitosanitaria);
                productorIdNew = em.merge(productorIdNew);
            }
            if (productoIdOld != null && !productoIdOld.equals(productoIdNew)) {
                productoIdOld.getAplicacionfitosanitariaList().remove(aplicacionfitosanitaria);
                productoIdOld = em.merge(productoIdOld);
            }
            if (productoIdNew != null && !productoIdNew.equals(productoIdOld)) {
                productoIdNew.getAplicacionfitosanitariaList().add(aplicacionfitosanitaria);
                productoIdNew = em.merge(productoIdNew);
            }
            if (responsableIdOld != null && !responsableIdOld.equals(responsableIdNew)) {
                responsableIdOld.getAplicacionfitosanitariaList().remove(aplicacionfitosanitaria);
                responsableIdOld = em.merge(responsableIdOld);
            }
            if (responsableIdNew != null && !responsableIdNew.equals(responsableIdOld)) {
                responsableIdNew.getAplicacionfitosanitariaList().add(aplicacionfitosanitaria);
                responsableIdNew = em.merge(responsableIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = aplicacionfitosanitaria.getId();
                if (findAplicacionfitosanitaria(id) == null) {
                    throw new NonexistentEntityException("The aplicacionfitosanitaria with id " + id + " no longer exists.");
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
            Aplicacionfitosanitaria aplicacionfitosanitaria;
            try {
                aplicacionfitosanitaria = em.getReference(Aplicacionfitosanitaria.class, id);
                aplicacionfitosanitaria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aplicacionfitosanitaria with id " + id + " no longer exists.", enfe);
            }
            Persona aprobanteId = aplicacionfitosanitaria.getAprobanteId();
            if (aprobanteId != null) {
                aprobanteId.getAplicacionfitosanitariaList().remove(aplicacionfitosanitaria);
                aprobanteId = em.merge(aprobanteId);
            }
            Persona asistenteId = aplicacionfitosanitaria.getAsistenteId();
            if (asistenteId != null) {
                asistenteId.getAplicacionfitosanitariaList().remove(aplicacionfitosanitaria);
                asistenteId = em.merge(asistenteId);
            }
            Persona productorId = aplicacionfitosanitaria.getProductorId();
            if (productorId != null) {
                productorId.getAplicacionfitosanitariaList().remove(aplicacionfitosanitaria);
                productorId = em.merge(productorId);
            }
            Insumo productoId = aplicacionfitosanitaria.getProductoId();
            if (productoId != null) {
                productoId.getAplicacionfitosanitariaList().remove(aplicacionfitosanitaria);
                productoId = em.merge(productoId);
            }
            Persona responsableId = aplicacionfitosanitaria.getResponsableId();
            if (responsableId != null) {
                responsableId.getAplicacionfitosanitariaList().remove(aplicacionfitosanitaria);
                responsableId = em.merge(responsableId);
            }
            em.remove(aplicacionfitosanitaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aplicacionfitosanitaria> findAplicacionfitosanitariaEntities() {
        return findAplicacionfitosanitariaEntities(true, -1, -1);
    }

    public List<Aplicacionfitosanitaria> findAplicacionfitosanitariaEntities(int maxResults, int firstResult) {
        return findAplicacionfitosanitariaEntities(false, maxResults, firstResult);
    }

    private List<Aplicacionfitosanitaria> findAplicacionfitosanitariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aplicacionfitosanitaria.class));
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

    public Aplicacionfitosanitaria findAplicacionfitosanitaria(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aplicacionfitosanitaria.class, id);
        } finally {
            em.close();
        }
    }

    public int getAplicacionfitosanitariaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aplicacionfitosanitaria> rt = cq.from(Aplicacionfitosanitaria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
