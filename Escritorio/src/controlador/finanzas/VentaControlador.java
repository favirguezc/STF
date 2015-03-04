/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.finanzas;

import datos.exceptions.NonexistentEntityException;
import datos.finanzas.VentaDAO;
import datos.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
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
        if(v.getExtraGramos() <= 0 && v.getPrimeraGramos() <= 0 && v.getSegundaGramos() <= 0 
                && v.getTerceraGramos() <= 0 && v.getCuartaGramos() <= 0 && v.getQuintaGramos() <= 0){
            JOptionPane.showMessageDialog(null, "No hay ninguna cantidad ingresada, por favor ingrese por lo menos una", "Registro no ingresado", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        if(v.getExtraGramos() < 0){
            JOptionPane.showMessageDialog(null, "El valor del registro debe ser mayor a cero, por favor ingreselo nuevamente", "Registro mal ingresado", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else if(v.getExtraGramos() > 0){
            if(v.getExtraPrecioUnid() <= 0){
                JOptionPane.showMessageDialog(null, "Si engreso una cantidad de un tipo de fresa, por favor ingrese su precio en la pestaña Establecer Precio", "Registro no ingresado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        if(v.getPrimeraGramos() < 0){
            JOptionPane.showMessageDialog(null, "El valor del registro debe ser mayor a cero, por favor ingreselo nuevamente", "Registro mal ingresado", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else if(v.getPrimeraGramos() > 0){
            if(v.getPrimeraPrecioUnid() <= 0){
                JOptionPane.showMessageDialog(null, "Si engreso una cantidad de un tipo de fresa, por favor ingrese su precio en la pestaña Establecer Precio", "Registro no ingresado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        if(v.getSegundaGramos() < 0){
            JOptionPane.showMessageDialog(null, "El valor del registro debe ser mayor a cero, por favor ingreselo nuevamente", "Registro mal ingresado", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else if(v.getSegundaGramos() > 0){
            if(v.getSegundaPrecioUnid() <= 0){
                JOptionPane.showMessageDialog(null, "Si engreso una cantidad de un tipo de fresa, por favor ingrese su precio en la pestaña Establecer Precio", "Registro no ingresado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        if(v.getTerceraGramos() < 0){
            JOptionPane.showMessageDialog(null, "El valor del registro debe ser mayor a cero, por favor ingreselo nuevamente", "Registro mal ingresado", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else if(v.getTerceraGramos() > 0){
            if(v.getTerceraPrecioUnid() <= 0){
                JOptionPane.showMessageDialog(null, "Si engreso una cantidad de un tipo de fresa, por favor ingrese su precio en la pestaña Establecer Precio", "Registro no ingresado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        if(v.getCuartaGramos() < 0){
            JOptionPane.showMessageDialog(null, "El valor del registro debe ser mayor a cero, por favor ingreselo nuevamente", "Registro mal ingresado", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else if(v.getCuartaGramos() > 0){
            if(v.getCuartaPrecioUnid() <= 0){
                JOptionPane.showMessageDialog(null, "Si engreso una cantidad de un tipo de fresa, por favor ingrese su precio en la pestaña Establecer Precio", "Registro no ingresado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        if(v.getQuintaGramos() < 0){
            JOptionPane.showMessageDialog(null, "El valor del registro debe ser mayor a cero, por favor ingreselo nuevamente", "Registro mal ingresado", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else if(v.getQuintaGramos() > 0){
            if(v.getQuintaPrecioUnid() <= 0){
                JOptionPane.showMessageDialog(null, "Si engreso una cantidad de un tipo de fresa, por favor ingrese su precio en la pestaña Establecer Precio", "Registro no ingresado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }

        return true;
    }
}
