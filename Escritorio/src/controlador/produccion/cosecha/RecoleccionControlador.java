/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.cosecha;

import datos.exceptions.NonexistentEntityException;
import datos.produccion.cosecha.RecoleccionDAO;
import datos.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.administracion.Persona;
import modelo.produccion.cosecha.Recoleccion;

/**
 *
 * @author fredy
 */
public class RecoleccionControlador {

    private RecoleccionDAO dao;

    public RecoleccionControlador() {
        dao = new RecoleccionDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Recoleccion nuevo(Modulo modulo, Date fecha, float pesada, Persona recolector) {
        return new Recoleccion(modulo, fecha, recolector, pesada);
    }

    public Recoleccion buscar(long id) {
        return dao.findRecoleccion(id);
    }

    public void editar(Recoleccion r) throws Exception {
        dao.edit(r);
    }

    public void eliminar(long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Recoleccion r) {
        dao.create(r);
    }

    public List<Recoleccion> leerLista(Persona recolector, Modulo modulo, Date inicio, Date fin) {
        return dao.findRecoleccionEntities(recolector, modulo, inicio, fin);
    }

    public Recoleccion sumarRegistros(Persona recolector, Modulo modulo, Date inicio, Date fin) {
        List<Recoleccion> leerLista = leerLista(recolector, modulo, inicio, fin);
        Recoleccion suma = new Recoleccion(modulo, null, recolector, 0);
        for (Recoleccion r : leerLista) {
            suma.sumar(r);
        }
        return suma;
    }

    public boolean validar(Recoleccion r) {
        if(r.getPesadaGramos() <= 0){
            JOptionPane.showMessageDialog(null, "El campo Pesada debe ser mayor a 0.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
}
