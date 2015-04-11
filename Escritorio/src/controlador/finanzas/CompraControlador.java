/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.finanzas;

import datos.exceptions.NonexistentEntityException;
import datos.util.EntityManagerFactorySingleton;
import datos.finanzas.CompraDAO;
import datos.finanzas.PrecioDAO;
import java.util.Date;
import java.util.List;
import modelo.finanzas.compra.Compra;
import modelo.produccion.administracion.Lote;
import modelo.produccion.aplicaciones.Insumo;

/**
 *
 * @author John Fredy
 */
public class CompraControlador {
    
    private CompraDAO dao;
    private PrecioDAO precioDao;

    public CompraControlador() {
        dao = new CompraDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }
    
    public Compra nuevo(Date fechaCompra, Lote lote,Insumo insumo, float precio, float cantidad) {
        return new Compra(fechaCompra, lote,insumo, precio, cantidad);
    }
    
    public Compra buscar(long id) {
        return dao.findCompra(id);
    }

    public void editar(Compra v) throws Exception {
        dao.edit(v);
    }

    public void eliminar(long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Compra v) {
        dao.create(v);
    }
    
    public List<Compra> leerLista(Lote lote, Date inicio, Date fin) {
        return dao.findCompraEntities(lote, inicio, fin);
    }
    
    public Compra sumarRegistros(Lote lote, Date inicio, Date fin) {
        List<Compra> leerLista = leerLista(lote, inicio, fin);
        Compra suma = new Compra(null, lote, null, 0, 0);
        leerLista.stream().forEach((co) -> {
            suma.sumar(co);
        });
        return suma;
    }
    
    public boolean validar(Compra co) {
        if(co.getFechaCompra() == null){
            return false;
        }
        if(co.getLote() == null){
            return false;
        }
        if(co.getInsumo() == null){
            return false;
        }
        if(co.getCantidad() <= 0){
            return false;
        }
        if(co.getPrecio() <= 0){
            return false;
        }
        
        return true;
    }
    
    public PrecioDAO getPrecioDAO(){
        if(precioDao == null){
            precioDao = new PrecioDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return precioDao;
    }
}
