/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.aplicaciones;

import datos.exceptions.NonexistentEntityException;
import datos.produccion.aplicaciones.AplicacionDAO;
import datos.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import modelo.produccion.administracion.Lote;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.administracion.Persona;
import modelo.produccion.aplicaciones.Aplicacion;
import modelo.produccion.aplicaciones.Insumo;
import modelo.produccion.aplicaciones.MetodoDeAplicacion;
import modelo.produccion.aplicaciones.TipoDeAplicacion;
import modelo.produccion.monitoreo.Variable;

/**
 *
 * @author fredy
 */
public class AplicacionControlador {

    AplicacionDAO dao;

    public AplicacionControlador() {
        dao = new AplicacionDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Aplicacion nuevo(TipoDeAplicacion tipo, Modulo modulo, Date fechaDeAutorizacion, Date fechaDeAplicacion, Insumo producto, Variable motivo1, Variable motivo2, Variable motivo3, float cantidad, float litrosDeAgua, MetodoDeAplicacion metodo, Persona responsable, Persona autoriza, float jornales, String observaciones) {
        return new Aplicacion(tipo, modulo, fechaDeAutorizacion, fechaDeAplicacion, producto, motivo1, motivo2, motivo3, cantidad, litrosDeAgua, metodo, responsable, autoriza, jornales, observaciones);
    }

    public Aplicacion buscar(long id) {
        return dao.findAplicacion(id);
    }

    public void eliminar(long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Aplicacion af) {
        dao.create(af);
    }

    public void editar(Aplicacion af) throws Exception {
        dao.edit(af);
    }

    public List<Aplicacion> leerLista(Lote lote, Date fecha1, Date fecha2) {
        return dao.findAplicacionEntities(lote, fecha1, fecha2);
    }

    public boolean validar(Aplicacion af) {
        
        return true;
    }
}
