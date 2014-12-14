/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Trabajo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Rolpersona;
import modelo.Recoleccion;
import modelo.Trampadeinsectos;
import modelo.Aplicacionfitosanitaria;
import modelo.Persona;

/**
 *
 * @author fredy
 */
public class PersonaJpaController implements Serializable {

    public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) throws PreexistingEntityException, Exception {
        if (persona.getTrabajoList() == null) {
            persona.setTrabajoList(new ArrayList<Trabajo>());
        }
        if (persona.getTrabajoList1() == null) {
            persona.setTrabajoList1(new ArrayList<Trabajo>());
        }
        if (persona.getTrabajoList2() == null) {
            persona.setTrabajoList2(new ArrayList<Trabajo>());
        }
        if (persona.getRolpersonaList() == null) {
            persona.setRolpersonaList(new ArrayList<Rolpersona>());
        }
        if (persona.getRecoleccionList() == null) {
            persona.setRecoleccionList(new ArrayList<Recoleccion>());
        }
        if (persona.getTrampadeinsectosList() == null) {
            persona.setTrampadeinsectosList(new ArrayList<Trampadeinsectos>());
        }
        if (persona.getTrampadeinsectosList1() == null) {
            persona.setTrampadeinsectosList1(new ArrayList<Trampadeinsectos>());
        }
        if (persona.getAplicacionfitosanitariaList() == null) {
            persona.setAplicacionfitosanitariaList(new ArrayList<Aplicacionfitosanitaria>());
        }
        if (persona.getAplicacionfitosanitariaList1() == null) {
            persona.setAplicacionfitosanitariaList1(new ArrayList<Aplicacionfitosanitaria>());
        }
        if (persona.getAplicacionfitosanitariaList2() == null) {
            persona.setAplicacionfitosanitariaList2(new ArrayList<Aplicacionfitosanitaria>());
        }
        if (persona.getAplicacionfitosanitariaList3() == null) {
            persona.setAplicacionfitosanitariaList3(new ArrayList<Aplicacionfitosanitaria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Trabajo> attachedTrabajoList = new ArrayList<Trabajo>();
            for (Trabajo trabajoListTrabajoToAttach : persona.getTrabajoList()) {
                trabajoListTrabajoToAttach = em.getReference(trabajoListTrabajoToAttach.getClass(), trabajoListTrabajoToAttach.getId());
                attachedTrabajoList.add(trabajoListTrabajoToAttach);
            }
            persona.setTrabajoList(attachedTrabajoList);
            List<Trabajo> attachedTrabajoList1 = new ArrayList<Trabajo>();
            for (Trabajo trabajoList1TrabajoToAttach : persona.getTrabajoList1()) {
                trabajoList1TrabajoToAttach = em.getReference(trabajoList1TrabajoToAttach.getClass(), trabajoList1TrabajoToAttach.getId());
                attachedTrabajoList1.add(trabajoList1TrabajoToAttach);
            }
            persona.setTrabajoList1(attachedTrabajoList1);
            List<Trabajo> attachedTrabajoList2 = new ArrayList<Trabajo>();
            for (Trabajo trabajoList2TrabajoToAttach : persona.getTrabajoList2()) {
                trabajoList2TrabajoToAttach = em.getReference(trabajoList2TrabajoToAttach.getClass(), trabajoList2TrabajoToAttach.getId());
                attachedTrabajoList2.add(trabajoList2TrabajoToAttach);
            }
            persona.setTrabajoList2(attachedTrabajoList2);
            List<Rolpersona> attachedRolpersonaList = new ArrayList<Rolpersona>();
            for (Rolpersona rolpersonaListRolpersonaToAttach : persona.getRolpersonaList()) {
                rolpersonaListRolpersonaToAttach = em.getReference(rolpersonaListRolpersonaToAttach.getClass(), rolpersonaListRolpersonaToAttach.getId());
                attachedRolpersonaList.add(rolpersonaListRolpersonaToAttach);
            }
            persona.setRolpersonaList(attachedRolpersonaList);
            List<Recoleccion> attachedRecoleccionList = new ArrayList<Recoleccion>();
            for (Recoleccion recoleccionListRecoleccionToAttach : persona.getRecoleccionList()) {
                recoleccionListRecoleccionToAttach = em.getReference(recoleccionListRecoleccionToAttach.getClass(), recoleccionListRecoleccionToAttach.getId());
                attachedRecoleccionList.add(recoleccionListRecoleccionToAttach);
            }
            persona.setRecoleccionList(attachedRecoleccionList);
            List<Trampadeinsectos> attachedTrampadeinsectosList = new ArrayList<Trampadeinsectos>();
            for (Trampadeinsectos trampadeinsectosListTrampadeinsectosToAttach : persona.getTrampadeinsectosList()) {
                trampadeinsectosListTrampadeinsectosToAttach = em.getReference(trampadeinsectosListTrampadeinsectosToAttach.getClass(), trampadeinsectosListTrampadeinsectosToAttach.getId());
                attachedTrampadeinsectosList.add(trampadeinsectosListTrampadeinsectosToAttach);
            }
            persona.setTrampadeinsectosList(attachedTrampadeinsectosList);
            List<Trampadeinsectos> attachedTrampadeinsectosList1 = new ArrayList<Trampadeinsectos>();
            for (Trampadeinsectos trampadeinsectosList1TrampadeinsectosToAttach : persona.getTrampadeinsectosList1()) {
                trampadeinsectosList1TrampadeinsectosToAttach = em.getReference(trampadeinsectosList1TrampadeinsectosToAttach.getClass(), trampadeinsectosList1TrampadeinsectosToAttach.getId());
                attachedTrampadeinsectosList1.add(trampadeinsectosList1TrampadeinsectosToAttach);
            }
            persona.setTrampadeinsectosList1(attachedTrampadeinsectosList1);
            List<Aplicacionfitosanitaria> attachedAplicacionfitosanitariaList = new ArrayList<Aplicacionfitosanitaria>();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListAplicacionfitosanitariaToAttach : persona.getAplicacionfitosanitariaList()) {
                aplicacionfitosanitariaListAplicacionfitosanitariaToAttach = em.getReference(aplicacionfitosanitariaListAplicacionfitosanitariaToAttach.getClass(), aplicacionfitosanitariaListAplicacionfitosanitariaToAttach.getId());
                attachedAplicacionfitosanitariaList.add(aplicacionfitosanitariaListAplicacionfitosanitariaToAttach);
            }
            persona.setAplicacionfitosanitariaList(attachedAplicacionfitosanitariaList);
            List<Aplicacionfitosanitaria> attachedAplicacionfitosanitariaList1 = new ArrayList<Aplicacionfitosanitaria>();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList1AplicacionfitosanitariaToAttach : persona.getAplicacionfitosanitariaList1()) {
                aplicacionfitosanitariaList1AplicacionfitosanitariaToAttach = em.getReference(aplicacionfitosanitariaList1AplicacionfitosanitariaToAttach.getClass(), aplicacionfitosanitariaList1AplicacionfitosanitariaToAttach.getId());
                attachedAplicacionfitosanitariaList1.add(aplicacionfitosanitariaList1AplicacionfitosanitariaToAttach);
            }
            persona.setAplicacionfitosanitariaList1(attachedAplicacionfitosanitariaList1);
            List<Aplicacionfitosanitaria> attachedAplicacionfitosanitariaList2 = new ArrayList<Aplicacionfitosanitaria>();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList2AplicacionfitosanitariaToAttach : persona.getAplicacionfitosanitariaList2()) {
                aplicacionfitosanitariaList2AplicacionfitosanitariaToAttach = em.getReference(aplicacionfitosanitariaList2AplicacionfitosanitariaToAttach.getClass(), aplicacionfitosanitariaList2AplicacionfitosanitariaToAttach.getId());
                attachedAplicacionfitosanitariaList2.add(aplicacionfitosanitariaList2AplicacionfitosanitariaToAttach);
            }
            persona.setAplicacionfitosanitariaList2(attachedAplicacionfitosanitariaList2);
            List<Aplicacionfitosanitaria> attachedAplicacionfitosanitariaList3 = new ArrayList<Aplicacionfitosanitaria>();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList3AplicacionfitosanitariaToAttach : persona.getAplicacionfitosanitariaList3()) {
                aplicacionfitosanitariaList3AplicacionfitosanitariaToAttach = em.getReference(aplicacionfitosanitariaList3AplicacionfitosanitariaToAttach.getClass(), aplicacionfitosanitariaList3AplicacionfitosanitariaToAttach.getId());
                attachedAplicacionfitosanitariaList3.add(aplicacionfitosanitariaList3AplicacionfitosanitariaToAttach);
            }
            persona.setAplicacionfitosanitariaList3(attachedAplicacionfitosanitariaList3);
            em.persist(persona);
            for (Trabajo trabajoListTrabajo : persona.getTrabajoList()) {
                Persona oldAsistenteIdOfTrabajoListTrabajo = trabajoListTrabajo.getAsistenteId();
                trabajoListTrabajo.setAsistenteId(persona);
                trabajoListTrabajo = em.merge(trabajoListTrabajo);
                if (oldAsistenteIdOfTrabajoListTrabajo != null) {
                    oldAsistenteIdOfTrabajoListTrabajo.getTrabajoList().remove(trabajoListTrabajo);
                    oldAsistenteIdOfTrabajoListTrabajo = em.merge(oldAsistenteIdOfTrabajoListTrabajo);
                }
            }
            for (Trabajo trabajoList1Trabajo : persona.getTrabajoList1()) {
                Persona oldOperarioIdOfTrabajoList1Trabajo = trabajoList1Trabajo.getOperarioId();
                trabajoList1Trabajo.setOperarioId(persona);
                trabajoList1Trabajo = em.merge(trabajoList1Trabajo);
                if (oldOperarioIdOfTrabajoList1Trabajo != null) {
                    oldOperarioIdOfTrabajoList1Trabajo.getTrabajoList1().remove(trabajoList1Trabajo);
                    oldOperarioIdOfTrabajoList1Trabajo = em.merge(oldOperarioIdOfTrabajoList1Trabajo);
                }
            }
            for (Trabajo trabajoList2Trabajo : persona.getTrabajoList2()) {
                Persona oldProductorIdOfTrabajoList2Trabajo = trabajoList2Trabajo.getProductorId();
                trabajoList2Trabajo.setProductorId(persona);
                trabajoList2Trabajo = em.merge(trabajoList2Trabajo);
                if (oldProductorIdOfTrabajoList2Trabajo != null) {
                    oldProductorIdOfTrabajoList2Trabajo.getTrabajoList2().remove(trabajoList2Trabajo);
                    oldProductorIdOfTrabajoList2Trabajo = em.merge(oldProductorIdOfTrabajoList2Trabajo);
                }
            }
            for (Rolpersona rolpersonaListRolpersona : persona.getRolpersonaList()) {
                Persona oldPersonaIdOfRolpersonaListRolpersona = rolpersonaListRolpersona.getPersonaId();
                rolpersonaListRolpersona.setPersonaId(persona);
                rolpersonaListRolpersona = em.merge(rolpersonaListRolpersona);
                if (oldPersonaIdOfRolpersonaListRolpersona != null) {
                    oldPersonaIdOfRolpersonaListRolpersona.getRolpersonaList().remove(rolpersonaListRolpersona);
                    oldPersonaIdOfRolpersonaListRolpersona = em.merge(oldPersonaIdOfRolpersonaListRolpersona);
                }
            }
            for (Recoleccion recoleccionListRecoleccion : persona.getRecoleccionList()) {
                Persona oldRecolectorIdOfRecoleccionListRecoleccion = recoleccionListRecoleccion.getRecolectorId();
                recoleccionListRecoleccion.setRecolectorId(persona);
                recoleccionListRecoleccion = em.merge(recoleccionListRecoleccion);
                if (oldRecolectorIdOfRecoleccionListRecoleccion != null) {
                    oldRecolectorIdOfRecoleccionListRecoleccion.getRecoleccionList().remove(recoleccionListRecoleccion);
                    oldRecolectorIdOfRecoleccionListRecoleccion = em.merge(oldRecolectorIdOfRecoleccionListRecoleccion);
                }
            }
            for (Trampadeinsectos trampadeinsectosListTrampadeinsectos : persona.getTrampadeinsectosList()) {
                Persona oldAsistenteIdOfTrampadeinsectosListTrampadeinsectos = trampadeinsectosListTrampadeinsectos.getAsistenteId();
                trampadeinsectosListTrampadeinsectos.setAsistenteId(persona);
                trampadeinsectosListTrampadeinsectos = em.merge(trampadeinsectosListTrampadeinsectos);
                if (oldAsistenteIdOfTrampadeinsectosListTrampadeinsectos != null) {
                    oldAsistenteIdOfTrampadeinsectosListTrampadeinsectos.getTrampadeinsectosList().remove(trampadeinsectosListTrampadeinsectos);
                    oldAsistenteIdOfTrampadeinsectosListTrampadeinsectos = em.merge(oldAsistenteIdOfTrampadeinsectosListTrampadeinsectos);
                }
            }
            for (Trampadeinsectos trampadeinsectosList1Trampadeinsectos : persona.getTrampadeinsectosList1()) {
                Persona oldProductorIdOfTrampadeinsectosList1Trampadeinsectos = trampadeinsectosList1Trampadeinsectos.getProductorId();
                trampadeinsectosList1Trampadeinsectos.setProductorId(persona);
                trampadeinsectosList1Trampadeinsectos = em.merge(trampadeinsectosList1Trampadeinsectos);
                if (oldProductorIdOfTrampadeinsectosList1Trampadeinsectos != null) {
                    oldProductorIdOfTrampadeinsectosList1Trampadeinsectos.getTrampadeinsectosList1().remove(trampadeinsectosList1Trampadeinsectos);
                    oldProductorIdOfTrampadeinsectosList1Trampadeinsectos = em.merge(oldProductorIdOfTrampadeinsectosList1Trampadeinsectos);
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListAplicacionfitosanitaria : persona.getAplicacionfitosanitariaList()) {
                Persona oldAprobanteIdOfAplicacionfitosanitariaListAplicacionfitosanitaria = aplicacionfitosanitariaListAplicacionfitosanitaria.getAprobanteId();
                aplicacionfitosanitariaListAplicacionfitosanitaria.setAprobanteId(persona);
                aplicacionfitosanitariaListAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaListAplicacionfitosanitaria);
                if (oldAprobanteIdOfAplicacionfitosanitariaListAplicacionfitosanitaria != null) {
                    oldAprobanteIdOfAplicacionfitosanitariaListAplicacionfitosanitaria.getAplicacionfitosanitariaList().remove(aplicacionfitosanitariaListAplicacionfitosanitaria);
                    oldAprobanteIdOfAplicacionfitosanitariaListAplicacionfitosanitaria = em.merge(oldAprobanteIdOfAplicacionfitosanitariaListAplicacionfitosanitaria);
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList1Aplicacionfitosanitaria : persona.getAplicacionfitosanitariaList1()) {
                Persona oldAsistenteIdOfAplicacionfitosanitariaList1Aplicacionfitosanitaria = aplicacionfitosanitariaList1Aplicacionfitosanitaria.getAsistenteId();
                aplicacionfitosanitariaList1Aplicacionfitosanitaria.setAsistenteId(persona);
                aplicacionfitosanitariaList1Aplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList1Aplicacionfitosanitaria);
                if (oldAsistenteIdOfAplicacionfitosanitariaList1Aplicacionfitosanitaria != null) {
                    oldAsistenteIdOfAplicacionfitosanitariaList1Aplicacionfitosanitaria.getAplicacionfitosanitariaList1().remove(aplicacionfitosanitariaList1Aplicacionfitosanitaria);
                    oldAsistenteIdOfAplicacionfitosanitariaList1Aplicacionfitosanitaria = em.merge(oldAsistenteIdOfAplicacionfitosanitariaList1Aplicacionfitosanitaria);
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList2Aplicacionfitosanitaria : persona.getAplicacionfitosanitariaList2()) {
                Persona oldProductorIdOfAplicacionfitosanitariaList2Aplicacionfitosanitaria = aplicacionfitosanitariaList2Aplicacionfitosanitaria.getProductorId();
                aplicacionfitosanitariaList2Aplicacionfitosanitaria.setProductorId(persona);
                aplicacionfitosanitariaList2Aplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList2Aplicacionfitosanitaria);
                if (oldProductorIdOfAplicacionfitosanitariaList2Aplicacionfitosanitaria != null) {
                    oldProductorIdOfAplicacionfitosanitariaList2Aplicacionfitosanitaria.getAplicacionfitosanitariaList2().remove(aplicacionfitosanitariaList2Aplicacionfitosanitaria);
                    oldProductorIdOfAplicacionfitosanitariaList2Aplicacionfitosanitaria = em.merge(oldProductorIdOfAplicacionfitosanitariaList2Aplicacionfitosanitaria);
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList3Aplicacionfitosanitaria : persona.getAplicacionfitosanitariaList3()) {
                Persona oldResponsableIdOfAplicacionfitosanitariaList3Aplicacionfitosanitaria = aplicacionfitosanitariaList3Aplicacionfitosanitaria.getResponsableId();
                aplicacionfitosanitariaList3Aplicacionfitosanitaria.setResponsableId(persona);
                aplicacionfitosanitariaList3Aplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList3Aplicacionfitosanitaria);
                if (oldResponsableIdOfAplicacionfitosanitariaList3Aplicacionfitosanitaria != null) {
                    oldResponsableIdOfAplicacionfitosanitariaList3Aplicacionfitosanitaria.getAplicacionfitosanitariaList3().remove(aplicacionfitosanitariaList3Aplicacionfitosanitaria);
                    oldResponsableIdOfAplicacionfitosanitariaList3Aplicacionfitosanitaria = em.merge(oldResponsableIdOfAplicacionfitosanitariaList3Aplicacionfitosanitaria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersona(persona.getId()) != null) {
                throw new PreexistingEntityException("Persona " + persona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getId());
            List<Trabajo> trabajoListOld = persistentPersona.getTrabajoList();
            List<Trabajo> trabajoListNew = persona.getTrabajoList();
            List<Trabajo> trabajoList1Old = persistentPersona.getTrabajoList1();
            List<Trabajo> trabajoList1New = persona.getTrabajoList1();
            List<Trabajo> trabajoList2Old = persistentPersona.getTrabajoList2();
            List<Trabajo> trabajoList2New = persona.getTrabajoList2();
            List<Rolpersona> rolpersonaListOld = persistentPersona.getRolpersonaList();
            List<Rolpersona> rolpersonaListNew = persona.getRolpersonaList();
            List<Recoleccion> recoleccionListOld = persistentPersona.getRecoleccionList();
            List<Recoleccion> recoleccionListNew = persona.getRecoleccionList();
            List<Trampadeinsectos> trampadeinsectosListOld = persistentPersona.getTrampadeinsectosList();
            List<Trampadeinsectos> trampadeinsectosListNew = persona.getTrampadeinsectosList();
            List<Trampadeinsectos> trampadeinsectosList1Old = persistentPersona.getTrampadeinsectosList1();
            List<Trampadeinsectos> trampadeinsectosList1New = persona.getTrampadeinsectosList1();
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaListOld = persistentPersona.getAplicacionfitosanitariaList();
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaListNew = persona.getAplicacionfitosanitariaList();
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaList1Old = persistentPersona.getAplicacionfitosanitariaList1();
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaList1New = persona.getAplicacionfitosanitariaList1();
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaList2Old = persistentPersona.getAplicacionfitosanitariaList2();
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaList2New = persona.getAplicacionfitosanitariaList2();
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaList3Old = persistentPersona.getAplicacionfitosanitariaList3();
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaList3New = persona.getAplicacionfitosanitariaList3();
            List<Trabajo> attachedTrabajoListNew = new ArrayList<Trabajo>();
            for (Trabajo trabajoListNewTrabajoToAttach : trabajoListNew) {
                trabajoListNewTrabajoToAttach = em.getReference(trabajoListNewTrabajoToAttach.getClass(), trabajoListNewTrabajoToAttach.getId());
                attachedTrabajoListNew.add(trabajoListNewTrabajoToAttach);
            }
            trabajoListNew = attachedTrabajoListNew;
            persona.setTrabajoList(trabajoListNew);
            List<Trabajo> attachedTrabajoList1New = new ArrayList<Trabajo>();
            for (Trabajo trabajoList1NewTrabajoToAttach : trabajoList1New) {
                trabajoList1NewTrabajoToAttach = em.getReference(trabajoList1NewTrabajoToAttach.getClass(), trabajoList1NewTrabajoToAttach.getId());
                attachedTrabajoList1New.add(trabajoList1NewTrabajoToAttach);
            }
            trabajoList1New = attachedTrabajoList1New;
            persona.setTrabajoList1(trabajoList1New);
            List<Trabajo> attachedTrabajoList2New = new ArrayList<Trabajo>();
            for (Trabajo trabajoList2NewTrabajoToAttach : trabajoList2New) {
                trabajoList2NewTrabajoToAttach = em.getReference(trabajoList2NewTrabajoToAttach.getClass(), trabajoList2NewTrabajoToAttach.getId());
                attachedTrabajoList2New.add(trabajoList2NewTrabajoToAttach);
            }
            trabajoList2New = attachedTrabajoList2New;
            persona.setTrabajoList2(trabajoList2New);
            List<Rolpersona> attachedRolpersonaListNew = new ArrayList<Rolpersona>();
            for (Rolpersona rolpersonaListNewRolpersonaToAttach : rolpersonaListNew) {
                rolpersonaListNewRolpersonaToAttach = em.getReference(rolpersonaListNewRolpersonaToAttach.getClass(), rolpersonaListNewRolpersonaToAttach.getId());
                attachedRolpersonaListNew.add(rolpersonaListNewRolpersonaToAttach);
            }
            rolpersonaListNew = attachedRolpersonaListNew;
            persona.setRolpersonaList(rolpersonaListNew);
            List<Recoleccion> attachedRecoleccionListNew = new ArrayList<Recoleccion>();
            for (Recoleccion recoleccionListNewRecoleccionToAttach : recoleccionListNew) {
                recoleccionListNewRecoleccionToAttach = em.getReference(recoleccionListNewRecoleccionToAttach.getClass(), recoleccionListNewRecoleccionToAttach.getId());
                attachedRecoleccionListNew.add(recoleccionListNewRecoleccionToAttach);
            }
            recoleccionListNew = attachedRecoleccionListNew;
            persona.setRecoleccionList(recoleccionListNew);
            List<Trampadeinsectos> attachedTrampadeinsectosListNew = new ArrayList<Trampadeinsectos>();
            for (Trampadeinsectos trampadeinsectosListNewTrampadeinsectosToAttach : trampadeinsectosListNew) {
                trampadeinsectosListNewTrampadeinsectosToAttach = em.getReference(trampadeinsectosListNewTrampadeinsectosToAttach.getClass(), trampadeinsectosListNewTrampadeinsectosToAttach.getId());
                attachedTrampadeinsectosListNew.add(trampadeinsectosListNewTrampadeinsectosToAttach);
            }
            trampadeinsectosListNew = attachedTrampadeinsectosListNew;
            persona.setTrampadeinsectosList(trampadeinsectosListNew);
            List<Trampadeinsectos> attachedTrampadeinsectosList1New = new ArrayList<Trampadeinsectos>();
            for (Trampadeinsectos trampadeinsectosList1NewTrampadeinsectosToAttach : trampadeinsectosList1New) {
                trampadeinsectosList1NewTrampadeinsectosToAttach = em.getReference(trampadeinsectosList1NewTrampadeinsectosToAttach.getClass(), trampadeinsectosList1NewTrampadeinsectosToAttach.getId());
                attachedTrampadeinsectosList1New.add(trampadeinsectosList1NewTrampadeinsectosToAttach);
            }
            trampadeinsectosList1New = attachedTrampadeinsectosList1New;
            persona.setTrampadeinsectosList1(trampadeinsectosList1New);
            List<Aplicacionfitosanitaria> attachedAplicacionfitosanitariaListNew = new ArrayList<Aplicacionfitosanitaria>();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListNewAplicacionfitosanitariaToAttach : aplicacionfitosanitariaListNew) {
                aplicacionfitosanitariaListNewAplicacionfitosanitariaToAttach = em.getReference(aplicacionfitosanitariaListNewAplicacionfitosanitariaToAttach.getClass(), aplicacionfitosanitariaListNewAplicacionfitosanitariaToAttach.getId());
                attachedAplicacionfitosanitariaListNew.add(aplicacionfitosanitariaListNewAplicacionfitosanitariaToAttach);
            }
            aplicacionfitosanitariaListNew = attachedAplicacionfitosanitariaListNew;
            persona.setAplicacionfitosanitariaList(aplicacionfitosanitariaListNew);
            List<Aplicacionfitosanitaria> attachedAplicacionfitosanitariaList1New = new ArrayList<Aplicacionfitosanitaria>();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList1NewAplicacionfitosanitariaToAttach : aplicacionfitosanitariaList1New) {
                aplicacionfitosanitariaList1NewAplicacionfitosanitariaToAttach = em.getReference(aplicacionfitosanitariaList1NewAplicacionfitosanitariaToAttach.getClass(), aplicacionfitosanitariaList1NewAplicacionfitosanitariaToAttach.getId());
                attachedAplicacionfitosanitariaList1New.add(aplicacionfitosanitariaList1NewAplicacionfitosanitariaToAttach);
            }
            aplicacionfitosanitariaList1New = attachedAplicacionfitosanitariaList1New;
            persona.setAplicacionfitosanitariaList1(aplicacionfitosanitariaList1New);
            List<Aplicacionfitosanitaria> attachedAplicacionfitosanitariaList2New = new ArrayList<Aplicacionfitosanitaria>();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList2NewAplicacionfitosanitariaToAttach : aplicacionfitosanitariaList2New) {
                aplicacionfitosanitariaList2NewAplicacionfitosanitariaToAttach = em.getReference(aplicacionfitosanitariaList2NewAplicacionfitosanitariaToAttach.getClass(), aplicacionfitosanitariaList2NewAplicacionfitosanitariaToAttach.getId());
                attachedAplicacionfitosanitariaList2New.add(aplicacionfitosanitariaList2NewAplicacionfitosanitariaToAttach);
            }
            aplicacionfitosanitariaList2New = attachedAplicacionfitosanitariaList2New;
            persona.setAplicacionfitosanitariaList2(aplicacionfitosanitariaList2New);
            List<Aplicacionfitosanitaria> attachedAplicacionfitosanitariaList3New = new ArrayList<Aplicacionfitosanitaria>();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList3NewAplicacionfitosanitariaToAttach : aplicacionfitosanitariaList3New) {
                aplicacionfitosanitariaList3NewAplicacionfitosanitariaToAttach = em.getReference(aplicacionfitosanitariaList3NewAplicacionfitosanitariaToAttach.getClass(), aplicacionfitosanitariaList3NewAplicacionfitosanitariaToAttach.getId());
                attachedAplicacionfitosanitariaList3New.add(aplicacionfitosanitariaList3NewAplicacionfitosanitariaToAttach);
            }
            aplicacionfitosanitariaList3New = attachedAplicacionfitosanitariaList3New;
            persona.setAplicacionfitosanitariaList3(aplicacionfitosanitariaList3New);
            persona = em.merge(persona);
            for (Trabajo trabajoListOldTrabajo : trabajoListOld) {
                if (!trabajoListNew.contains(trabajoListOldTrabajo)) {
                    trabajoListOldTrabajo.setAsistenteId(null);
                    trabajoListOldTrabajo = em.merge(trabajoListOldTrabajo);
                }
            }
            for (Trabajo trabajoListNewTrabajo : trabajoListNew) {
                if (!trabajoListOld.contains(trabajoListNewTrabajo)) {
                    Persona oldAsistenteIdOfTrabajoListNewTrabajo = trabajoListNewTrabajo.getAsistenteId();
                    trabajoListNewTrabajo.setAsistenteId(persona);
                    trabajoListNewTrabajo = em.merge(trabajoListNewTrabajo);
                    if (oldAsistenteIdOfTrabajoListNewTrabajo != null && !oldAsistenteIdOfTrabajoListNewTrabajo.equals(persona)) {
                        oldAsistenteIdOfTrabajoListNewTrabajo.getTrabajoList().remove(trabajoListNewTrabajo);
                        oldAsistenteIdOfTrabajoListNewTrabajo = em.merge(oldAsistenteIdOfTrabajoListNewTrabajo);
                    }
                }
            }
            for (Trabajo trabajoList1OldTrabajo : trabajoList1Old) {
                if (!trabajoList1New.contains(trabajoList1OldTrabajo)) {
                    trabajoList1OldTrabajo.setOperarioId(null);
                    trabajoList1OldTrabajo = em.merge(trabajoList1OldTrabajo);
                }
            }
            for (Trabajo trabajoList1NewTrabajo : trabajoList1New) {
                if (!trabajoList1Old.contains(trabajoList1NewTrabajo)) {
                    Persona oldOperarioIdOfTrabajoList1NewTrabajo = trabajoList1NewTrabajo.getOperarioId();
                    trabajoList1NewTrabajo.setOperarioId(persona);
                    trabajoList1NewTrabajo = em.merge(trabajoList1NewTrabajo);
                    if (oldOperarioIdOfTrabajoList1NewTrabajo != null && !oldOperarioIdOfTrabajoList1NewTrabajo.equals(persona)) {
                        oldOperarioIdOfTrabajoList1NewTrabajo.getTrabajoList1().remove(trabajoList1NewTrabajo);
                        oldOperarioIdOfTrabajoList1NewTrabajo = em.merge(oldOperarioIdOfTrabajoList1NewTrabajo);
                    }
                }
            }
            for (Trabajo trabajoList2OldTrabajo : trabajoList2Old) {
                if (!trabajoList2New.contains(trabajoList2OldTrabajo)) {
                    trabajoList2OldTrabajo.setProductorId(null);
                    trabajoList2OldTrabajo = em.merge(trabajoList2OldTrabajo);
                }
            }
            for (Trabajo trabajoList2NewTrabajo : trabajoList2New) {
                if (!trabajoList2Old.contains(trabajoList2NewTrabajo)) {
                    Persona oldProductorIdOfTrabajoList2NewTrabajo = trabajoList2NewTrabajo.getProductorId();
                    trabajoList2NewTrabajo.setProductorId(persona);
                    trabajoList2NewTrabajo = em.merge(trabajoList2NewTrabajo);
                    if (oldProductorIdOfTrabajoList2NewTrabajo != null && !oldProductorIdOfTrabajoList2NewTrabajo.equals(persona)) {
                        oldProductorIdOfTrabajoList2NewTrabajo.getTrabajoList2().remove(trabajoList2NewTrabajo);
                        oldProductorIdOfTrabajoList2NewTrabajo = em.merge(oldProductorIdOfTrabajoList2NewTrabajo);
                    }
                }
            }
            for (Rolpersona rolpersonaListOldRolpersona : rolpersonaListOld) {
                if (!rolpersonaListNew.contains(rolpersonaListOldRolpersona)) {
                    rolpersonaListOldRolpersona.setPersonaId(null);
                    rolpersonaListOldRolpersona = em.merge(rolpersonaListOldRolpersona);
                }
            }
            for (Rolpersona rolpersonaListNewRolpersona : rolpersonaListNew) {
                if (!rolpersonaListOld.contains(rolpersonaListNewRolpersona)) {
                    Persona oldPersonaIdOfRolpersonaListNewRolpersona = rolpersonaListNewRolpersona.getPersonaId();
                    rolpersonaListNewRolpersona.setPersonaId(persona);
                    rolpersonaListNewRolpersona = em.merge(rolpersonaListNewRolpersona);
                    if (oldPersonaIdOfRolpersonaListNewRolpersona != null && !oldPersonaIdOfRolpersonaListNewRolpersona.equals(persona)) {
                        oldPersonaIdOfRolpersonaListNewRolpersona.getRolpersonaList().remove(rolpersonaListNewRolpersona);
                        oldPersonaIdOfRolpersonaListNewRolpersona = em.merge(oldPersonaIdOfRolpersonaListNewRolpersona);
                    }
                }
            }
            for (Recoleccion recoleccionListOldRecoleccion : recoleccionListOld) {
                if (!recoleccionListNew.contains(recoleccionListOldRecoleccion)) {
                    recoleccionListOldRecoleccion.setRecolectorId(null);
                    recoleccionListOldRecoleccion = em.merge(recoleccionListOldRecoleccion);
                }
            }
            for (Recoleccion recoleccionListNewRecoleccion : recoleccionListNew) {
                if (!recoleccionListOld.contains(recoleccionListNewRecoleccion)) {
                    Persona oldRecolectorIdOfRecoleccionListNewRecoleccion = recoleccionListNewRecoleccion.getRecolectorId();
                    recoleccionListNewRecoleccion.setRecolectorId(persona);
                    recoleccionListNewRecoleccion = em.merge(recoleccionListNewRecoleccion);
                    if (oldRecolectorIdOfRecoleccionListNewRecoleccion != null && !oldRecolectorIdOfRecoleccionListNewRecoleccion.equals(persona)) {
                        oldRecolectorIdOfRecoleccionListNewRecoleccion.getRecoleccionList().remove(recoleccionListNewRecoleccion);
                        oldRecolectorIdOfRecoleccionListNewRecoleccion = em.merge(oldRecolectorIdOfRecoleccionListNewRecoleccion);
                    }
                }
            }
            for (Trampadeinsectos trampadeinsectosListOldTrampadeinsectos : trampadeinsectosListOld) {
                if (!trampadeinsectosListNew.contains(trampadeinsectosListOldTrampadeinsectos)) {
                    trampadeinsectosListOldTrampadeinsectos.setAsistenteId(null);
                    trampadeinsectosListOldTrampadeinsectos = em.merge(trampadeinsectosListOldTrampadeinsectos);
                }
            }
            for (Trampadeinsectos trampadeinsectosListNewTrampadeinsectos : trampadeinsectosListNew) {
                if (!trampadeinsectosListOld.contains(trampadeinsectosListNewTrampadeinsectos)) {
                    Persona oldAsistenteIdOfTrampadeinsectosListNewTrampadeinsectos = trampadeinsectosListNewTrampadeinsectos.getAsistenteId();
                    trampadeinsectosListNewTrampadeinsectos.setAsistenteId(persona);
                    trampadeinsectosListNewTrampadeinsectos = em.merge(trampadeinsectosListNewTrampadeinsectos);
                    if (oldAsistenteIdOfTrampadeinsectosListNewTrampadeinsectos != null && !oldAsistenteIdOfTrampadeinsectosListNewTrampadeinsectos.equals(persona)) {
                        oldAsistenteIdOfTrampadeinsectosListNewTrampadeinsectos.getTrampadeinsectosList().remove(trampadeinsectosListNewTrampadeinsectos);
                        oldAsistenteIdOfTrampadeinsectosListNewTrampadeinsectos = em.merge(oldAsistenteIdOfTrampadeinsectosListNewTrampadeinsectos);
                    }
                }
            }
            for (Trampadeinsectos trampadeinsectosList1OldTrampadeinsectos : trampadeinsectosList1Old) {
                if (!trampadeinsectosList1New.contains(trampadeinsectosList1OldTrampadeinsectos)) {
                    trampadeinsectosList1OldTrampadeinsectos.setProductorId(null);
                    trampadeinsectosList1OldTrampadeinsectos = em.merge(trampadeinsectosList1OldTrampadeinsectos);
                }
            }
            for (Trampadeinsectos trampadeinsectosList1NewTrampadeinsectos : trampadeinsectosList1New) {
                if (!trampadeinsectosList1Old.contains(trampadeinsectosList1NewTrampadeinsectos)) {
                    Persona oldProductorIdOfTrampadeinsectosList1NewTrampadeinsectos = trampadeinsectosList1NewTrampadeinsectos.getProductorId();
                    trampadeinsectosList1NewTrampadeinsectos.setProductorId(persona);
                    trampadeinsectosList1NewTrampadeinsectos = em.merge(trampadeinsectosList1NewTrampadeinsectos);
                    if (oldProductorIdOfTrampadeinsectosList1NewTrampadeinsectos != null && !oldProductorIdOfTrampadeinsectosList1NewTrampadeinsectos.equals(persona)) {
                        oldProductorIdOfTrampadeinsectosList1NewTrampadeinsectos.getTrampadeinsectosList1().remove(trampadeinsectosList1NewTrampadeinsectos);
                        oldProductorIdOfTrampadeinsectosList1NewTrampadeinsectos = em.merge(oldProductorIdOfTrampadeinsectosList1NewTrampadeinsectos);
                    }
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListOldAplicacionfitosanitaria : aplicacionfitosanitariaListOld) {
                if (!aplicacionfitosanitariaListNew.contains(aplicacionfitosanitariaListOldAplicacionfitosanitaria)) {
                    aplicacionfitosanitariaListOldAplicacionfitosanitaria.setAprobanteId(null);
                    aplicacionfitosanitariaListOldAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaListOldAplicacionfitosanitaria);
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListNewAplicacionfitosanitaria : aplicacionfitosanitariaListNew) {
                if (!aplicacionfitosanitariaListOld.contains(aplicacionfitosanitariaListNewAplicacionfitosanitaria)) {
                    Persona oldAprobanteIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria = aplicacionfitosanitariaListNewAplicacionfitosanitaria.getAprobanteId();
                    aplicacionfitosanitariaListNewAplicacionfitosanitaria.setAprobanteId(persona);
                    aplicacionfitosanitariaListNewAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaListNewAplicacionfitosanitaria);
                    if (oldAprobanteIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria != null && !oldAprobanteIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria.equals(persona)) {
                        oldAprobanteIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria.getAplicacionfitosanitariaList().remove(aplicacionfitosanitariaListNewAplicacionfitosanitaria);
                        oldAprobanteIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria = em.merge(oldAprobanteIdOfAplicacionfitosanitariaListNewAplicacionfitosanitaria);
                    }
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList1OldAplicacionfitosanitaria : aplicacionfitosanitariaList1Old) {
                if (!aplicacionfitosanitariaList1New.contains(aplicacionfitosanitariaList1OldAplicacionfitosanitaria)) {
                    aplicacionfitosanitariaList1OldAplicacionfitosanitaria.setAsistenteId(null);
                    aplicacionfitosanitariaList1OldAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList1OldAplicacionfitosanitaria);
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList1NewAplicacionfitosanitaria : aplicacionfitosanitariaList1New) {
                if (!aplicacionfitosanitariaList1Old.contains(aplicacionfitosanitariaList1NewAplicacionfitosanitaria)) {
                    Persona oldAsistenteIdOfAplicacionfitosanitariaList1NewAplicacionfitosanitaria = aplicacionfitosanitariaList1NewAplicacionfitosanitaria.getAsistenteId();
                    aplicacionfitosanitariaList1NewAplicacionfitosanitaria.setAsistenteId(persona);
                    aplicacionfitosanitariaList1NewAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList1NewAplicacionfitosanitaria);
                    if (oldAsistenteIdOfAplicacionfitosanitariaList1NewAplicacionfitosanitaria != null && !oldAsistenteIdOfAplicacionfitosanitariaList1NewAplicacionfitosanitaria.equals(persona)) {
                        oldAsistenteIdOfAplicacionfitosanitariaList1NewAplicacionfitosanitaria.getAplicacionfitosanitariaList1().remove(aplicacionfitosanitariaList1NewAplicacionfitosanitaria);
                        oldAsistenteIdOfAplicacionfitosanitariaList1NewAplicacionfitosanitaria = em.merge(oldAsistenteIdOfAplicacionfitosanitariaList1NewAplicacionfitosanitaria);
                    }
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList2OldAplicacionfitosanitaria : aplicacionfitosanitariaList2Old) {
                if (!aplicacionfitosanitariaList2New.contains(aplicacionfitosanitariaList2OldAplicacionfitosanitaria)) {
                    aplicacionfitosanitariaList2OldAplicacionfitosanitaria.setProductorId(null);
                    aplicacionfitosanitariaList2OldAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList2OldAplicacionfitosanitaria);
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList2NewAplicacionfitosanitaria : aplicacionfitosanitariaList2New) {
                if (!aplicacionfitosanitariaList2Old.contains(aplicacionfitosanitariaList2NewAplicacionfitosanitaria)) {
                    Persona oldProductorIdOfAplicacionfitosanitariaList2NewAplicacionfitosanitaria = aplicacionfitosanitariaList2NewAplicacionfitosanitaria.getProductorId();
                    aplicacionfitosanitariaList2NewAplicacionfitosanitaria.setProductorId(persona);
                    aplicacionfitosanitariaList2NewAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList2NewAplicacionfitosanitaria);
                    if (oldProductorIdOfAplicacionfitosanitariaList2NewAplicacionfitosanitaria != null && !oldProductorIdOfAplicacionfitosanitariaList2NewAplicacionfitosanitaria.equals(persona)) {
                        oldProductorIdOfAplicacionfitosanitariaList2NewAplicacionfitosanitaria.getAplicacionfitosanitariaList2().remove(aplicacionfitosanitariaList2NewAplicacionfitosanitaria);
                        oldProductorIdOfAplicacionfitosanitariaList2NewAplicacionfitosanitaria = em.merge(oldProductorIdOfAplicacionfitosanitariaList2NewAplicacionfitosanitaria);
                    }
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList3OldAplicacionfitosanitaria : aplicacionfitosanitariaList3Old) {
                if (!aplicacionfitosanitariaList3New.contains(aplicacionfitosanitariaList3OldAplicacionfitosanitaria)) {
                    aplicacionfitosanitariaList3OldAplicacionfitosanitaria.setResponsableId(null);
                    aplicacionfitosanitariaList3OldAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList3OldAplicacionfitosanitaria);
                }
            }
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList3NewAplicacionfitosanitaria : aplicacionfitosanitariaList3New) {
                if (!aplicacionfitosanitariaList3Old.contains(aplicacionfitosanitariaList3NewAplicacionfitosanitaria)) {
                    Persona oldResponsableIdOfAplicacionfitosanitariaList3NewAplicacionfitosanitaria = aplicacionfitosanitariaList3NewAplicacionfitosanitaria.getResponsableId();
                    aplicacionfitosanitariaList3NewAplicacionfitosanitaria.setResponsableId(persona);
                    aplicacionfitosanitariaList3NewAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList3NewAplicacionfitosanitaria);
                    if (oldResponsableIdOfAplicacionfitosanitariaList3NewAplicacionfitosanitaria != null && !oldResponsableIdOfAplicacionfitosanitariaList3NewAplicacionfitosanitaria.equals(persona)) {
                        oldResponsableIdOfAplicacionfitosanitariaList3NewAplicacionfitosanitaria.getAplicacionfitosanitariaList3().remove(aplicacionfitosanitariaList3NewAplicacionfitosanitaria);
                        oldResponsableIdOfAplicacionfitosanitariaList3NewAplicacionfitosanitaria = em.merge(oldResponsableIdOfAplicacionfitosanitariaList3NewAplicacionfitosanitaria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = persona.getId();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<Trabajo> trabajoList = persona.getTrabajoList();
            for (Trabajo trabajoListTrabajo : trabajoList) {
                trabajoListTrabajo.setAsistenteId(null);
                trabajoListTrabajo = em.merge(trabajoListTrabajo);
            }
            List<Trabajo> trabajoList1 = persona.getTrabajoList1();
            for (Trabajo trabajoList1Trabajo : trabajoList1) {
                trabajoList1Trabajo.setOperarioId(null);
                trabajoList1Trabajo = em.merge(trabajoList1Trabajo);
            }
            List<Trabajo> trabajoList2 = persona.getTrabajoList2();
            for (Trabajo trabajoList2Trabajo : trabajoList2) {
                trabajoList2Trabajo.setProductorId(null);
                trabajoList2Trabajo = em.merge(trabajoList2Trabajo);
            }
            List<Rolpersona> rolpersonaList = persona.getRolpersonaList();
            for (Rolpersona rolpersonaListRolpersona : rolpersonaList) {
                rolpersonaListRolpersona.setPersonaId(null);
                rolpersonaListRolpersona = em.merge(rolpersonaListRolpersona);
            }
            List<Recoleccion> recoleccionList = persona.getRecoleccionList();
            for (Recoleccion recoleccionListRecoleccion : recoleccionList) {
                recoleccionListRecoleccion.setRecolectorId(null);
                recoleccionListRecoleccion = em.merge(recoleccionListRecoleccion);
            }
            List<Trampadeinsectos> trampadeinsectosList = persona.getTrampadeinsectosList();
            for (Trampadeinsectos trampadeinsectosListTrampadeinsectos : trampadeinsectosList) {
                trampadeinsectosListTrampadeinsectos.setAsistenteId(null);
                trampadeinsectosListTrampadeinsectos = em.merge(trampadeinsectosListTrampadeinsectos);
            }
            List<Trampadeinsectos> trampadeinsectosList1 = persona.getTrampadeinsectosList1();
            for (Trampadeinsectos trampadeinsectosList1Trampadeinsectos : trampadeinsectosList1) {
                trampadeinsectosList1Trampadeinsectos.setProductorId(null);
                trampadeinsectosList1Trampadeinsectos = em.merge(trampadeinsectosList1Trampadeinsectos);
            }
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaList = persona.getAplicacionfitosanitariaList();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaListAplicacionfitosanitaria : aplicacionfitosanitariaList) {
                aplicacionfitosanitariaListAplicacionfitosanitaria.setAprobanteId(null);
                aplicacionfitosanitariaListAplicacionfitosanitaria = em.merge(aplicacionfitosanitariaListAplicacionfitosanitaria);
            }
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaList1 = persona.getAplicacionfitosanitariaList1();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList1Aplicacionfitosanitaria : aplicacionfitosanitariaList1) {
                aplicacionfitosanitariaList1Aplicacionfitosanitaria.setAsistenteId(null);
                aplicacionfitosanitariaList1Aplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList1Aplicacionfitosanitaria);
            }
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaList2 = persona.getAplicacionfitosanitariaList2();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList2Aplicacionfitosanitaria : aplicacionfitosanitariaList2) {
                aplicacionfitosanitariaList2Aplicacionfitosanitaria.setProductorId(null);
                aplicacionfitosanitariaList2Aplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList2Aplicacionfitosanitaria);
            }
            List<Aplicacionfitosanitaria> aplicacionfitosanitariaList3 = persona.getAplicacionfitosanitariaList3();
            for (Aplicacionfitosanitaria aplicacionfitosanitariaList3Aplicacionfitosanitaria : aplicacionfitosanitariaList3) {
                aplicacionfitosanitariaList3Aplicacionfitosanitaria.setResponsableId(null);
                aplicacionfitosanitariaList3Aplicacionfitosanitaria = em.merge(aplicacionfitosanitariaList3Aplicacionfitosanitaria);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
