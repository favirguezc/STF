/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.TrampaDeInsectosDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.administracion.Persona;
import modelo.produccion.TrampaDeInsectos;

/**
 *
 * @author fredy
 */
public class TrampaDeInsectosControlador {

    private TrampaDeInsectosDAO dao;

    public TrampaDeInsectosControlador() {
        dao = new TrampaDeInsectosDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public TrampaDeInsectos nuevo(Date fecha, String nombre, String especie, int individuos, boolean cambio, String observaciones, Persona asistente, Persona productor) {
        return new TrampaDeInsectos(fecha, nombre, especie, individuos, cambio, observaciones, asistente, productor);
    }

    public TrampaDeInsectos buscar(long id) {
        return dao.findTrampaDeInsectos(id);
    }

    public void eliminar(long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(TrampaDeInsectos t) {
        dao.create(t);
    }

    public void editar(TrampaDeInsectos t) throws Exception {
        dao.edit(t);
    }

    public List<TrampaDeInsectos> leerLista() {
        return dao.findTrampaDeInsectosEntities();
    }

    public boolean validar(TrampaDeInsectos t) {
        if (t.getNombre() == null) {
            JOptionPane.showMessageDialog(null, "El campo nombre no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (t.getEspecie() == null) {
            JOptionPane.showMessageDialog(null, "El campo especie no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public int contar(Date fecha1, Date fecha2) {
        int suma = 0;
        List<TrampaDeInsectos> lista = leerLista(fecha1, fecha2);
        for (TrampaDeInsectos t : lista) {
            suma += t.getIndividuos();
        }
        return suma;
    }

    private List<TrampaDeInsectos> leerLista(Date fecha1, Date fecha2) {
        return dao.findTrampaDeInsectosEntities(fecha1, fecha2);
    }
}
