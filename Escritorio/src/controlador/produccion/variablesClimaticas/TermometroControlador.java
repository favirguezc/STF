/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.variablesClimaticas;

import datos.util.EntityManagerFactorySingleton;
import datos.exceptions.NonexistentEntityException;
import datos.produccion.variablesClimaticas.TermometroDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.variablesClimaticas.Termometro;

/**
 *
 * @author fredy
 */
public class TermometroControlador {

    private final TermometroDAO dao;

    public TermometroControlador() {
        dao = new TermometroDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Termometro nuevo(String nombre, long nds, Modulo modulo) {
        return new Termometro(nombre, nds, modulo);
    }

    public Termometro buscar(long nds) throws Exception {
        return dao.findTermometro(nds);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Termometro nueva) throws Exception {
        dao.create(nueva);
    }

    public void editar(Termometro seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<Termometro> buscarLista() {
        return dao.findTermometroEntities();
    }

    public boolean validar(Termometro registroSeleccionado) {
        if (registroSeleccionado.getNombre() == null) {
            return false;
        }
        if (registroSeleccionado.getModulo() == null) {
            return false;
        }
        if (registroSeleccionado.getNumeroDeSerie() <= 0) {
            return false;
        }
        try {
            buscar(registroSeleccionado.getNumeroDeSerie());
            return false;
        } catch (Exception ex) {
        }
        return true;
    }
}
