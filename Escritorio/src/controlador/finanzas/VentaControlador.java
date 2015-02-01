/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.finanzas;

import dao.exceptions.NonexistentEntityException;
import dao.fnanzas.VentaDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import modelo.finanzas.ventas.Venta;
import modelo.produccion.administracion.Persona;

/**
 *
 * @author JohnFredy
 */
public class VentaControlador {
    
    private VentaDAO dao;

    public VentaControlador() {
        dao = new VentaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }
    
    public Venta nuevo(Date fechaVenta, Persona cliente, float extraGramos, float primeraGramos,
            float segundaGramos, float terceraGramos, float cuartaGramos, float quintaGramos) {
        return new Venta(fechaVenta, cliente, extraGramos, primeraGramos, segundaGramos, terceraGramos, cuartaGramos, quintaGramos);
    }

    public Venta nuevo(Date fechaVenta, Persona cliente, float extraGramos, float primeraGramos,
            float segundaGramos, float terceraGramos, float cuartaGramos, float quintaGramos,
            float extraPrecioUnid, float primeraPrecioUnid, float segundaPrecioUnid,
            float terceraPrecioUnid, float cuartaPrecioUnid, float quintaPrecioUnid) {
        return new Venta(fechaVenta, cliente, extraGramos, primeraGramos, segundaGramos, terceraGramos, cuartaGramos, quintaGramos,
                  extraPrecioUnid, primeraPrecioUnid, segundaPrecioUnid, terceraPrecioUnid, cuartaPrecioUnid, quintaPrecioUnid);
    }
    
    public Venta buscar(long id) {
        return dao.findVenta(id);
    }

    public void editar(Venta v) throws Exception {
        dao.edit(v);
    }

    public void eliminar(long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Venta v) {
        dao.create(v);
    }
    
    public List<Venta> leerLista(Persona cliente, Date inicio, Date fin) {
        return dao.findVentaEntities(cliente, inicio, fin);
    }
    
    public Venta sumarRegistros(Persona cliente, Date inicio, Date fin) {
        List<Venta> leerLista = leerLista(cliente, inicio, fin);
        Venta suma = new Venta(null, cliente, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        leerLista.stream().forEach((v) -> {
            suma.sumar(v);
        });
        return suma;
    }
    
    public boolean validar(Venta v) {
        //Insert code here
        return true;
    }
}
