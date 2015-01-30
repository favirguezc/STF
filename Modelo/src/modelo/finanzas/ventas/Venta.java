/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.finanzas.ventas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.produccion.administracion.Persona;

/**
 *
 * @author JohnFredy
 */
@Entity
public class Venta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaVenta;
    @ManyToOne(optional = false)
    private Persona cliente;
    @Column
    private float extraGramos;
    @Column
    private float primeraGramos;
    @Column
    private float segundaGramos;
    @Column
    private float terceraGramos;
    @Column
    private float cuartaGramos;
    @Column
    private float quintaGramos;
    @Column
    private float extraPrecioUnid;
    @Column
    private float primeraPrecioUnid;
    @Column
    private float segundaPrecioUnid;
    @Column
    private float terceraPrecioUnid;
    @Column
    private float cuartaPrecioUnid;
    @Column
    private float quintaPrecioUnid;
    /*
    private float extraPrecioTotal;
    private float primeraPrecioTotal;
    private float segundaPrecioTotal;
    private float terceraPrecioTotal;
    private float cuartaPrecioTotal;
    private float quintaPrecioTotal;
    */

    public Venta() {
    }
    
    public Venta(Date fechaVenta, Persona cliente, float extraGramos, float primeraGramos,
            float segundaGramos, float terceraGramos, float cuartaGramos, float quintaGramos, 
            float extraPrecioUnid, float primeraPrecioUnid, float segundaPrecioUnid,
            float terceraPrecioUnid, float cuartaPrecioUnid, float quintaPrecioUnid) {
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.extraGramos = extraGramos;
        this.primeraGramos = primeraGramos;
        this.segundaGramos = segundaGramos;
        this.terceraGramos = terceraGramos;
        this.cuartaGramos = cuartaGramos;
        this.quintaGramos = quintaGramos;
        this.extraPrecioUnid = extraPrecioUnid;
        this.primeraPrecioUnid = primeraPrecioUnid;
        this.segundaPrecioUnid = segundaPrecioUnid;
        this.terceraPrecioUnid = terceraPrecioUnid;
        this.cuartaPrecioUnid = cuartaPrecioUnid;
        this.quintaPrecioUnid = quintaPrecioUnid;
    }

    public Venta(Date fechaVenta, Persona cliente, float extraGramos, float primeraGramos,
            float segundaGramos, float terceraGramos, float cuartaGramos, float quintaGramos) {
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.extraGramos = extraGramos;
        this.primeraGramos = primeraGramos;
        this.segundaGramos = segundaGramos;
        this.terceraGramos = terceraGramos;
        this.cuartaGramos = cuartaGramos;
        this.quintaGramos = quintaGramos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public float getExtraGramos() {
        return extraGramos;
    }

    public void setExtraGramos(float extraGramos) {
        this.extraGramos = extraGramos;
    }

    public float getPrimeraGramos() {
        return primeraGramos;
    }

    public void setPrimeraGramos(float primeraGramos) {
        this.primeraGramos = primeraGramos;
    }

    public float getSegundaGramos() {
        return segundaGramos;
    }

    public void setSegundaGramos(float segundaGramos) {
        this.segundaGramos = segundaGramos;
    }

    public float getTerceraGramos() {
        return terceraGramos;
    }

    public void setTerceraGramos(float terceraGramos) {
        this.terceraGramos = terceraGramos;
    }

    public float getCuartaGramos() {
        return cuartaGramos;
    }

    public void setCuartaGramos(float cuartaGramos) {
        this.cuartaGramos = cuartaGramos;
    }

    public float getQuintaGramos() {
        return quintaGramos;
    }

    public void setQuintaGramos(float quintaGramos) {
        this.quintaGramos = quintaGramos;
    }

    public float getExtraPrecioUnid() {
        return extraPrecioUnid;
    }

    public void setExtraPrecioUnid(float extraPrecioUnid) {
        this.extraPrecioUnid = extraPrecioUnid;
    }

    public float getPrimeraPrecioUnid() {
        return primeraPrecioUnid;
    }

    public void setPrimeraPrecioUnid(float primeraPrecioUnid) {
        this.primeraPrecioUnid = primeraPrecioUnid;
    }

    public float getSegundaPrecioUnid() {
        return segundaPrecioUnid;
    }

    public void setSegundaPrecioUnid(float segundaPrecioUnid) {
        this.segundaPrecioUnid = segundaPrecioUnid;
    }

    public float getTerceraPrecioUnid() {
        return terceraPrecioUnid;
    }

    public void setTerceraPrecioUnid(float terceraPrecioUnid) {
        this.terceraPrecioUnid = terceraPrecioUnid;
    }

    public float getCuartaPrecioUnid() {
        return cuartaPrecioUnid;
    }

    public void setCuartaPrecioUnid(float cuartaPrecioUnid) {
        this.cuartaPrecioUnid = cuartaPrecioUnid;
    }

    public float getQuintaPrecioUnid() {
        return quintaPrecioUnid;
    }

    public void setQuintaPrecioUnid(float quintaPrecioUnid) {
        this.quintaPrecioUnid = quintaPrecioUnid;
    }

    public float getExtraPrecioTotal() {
        return extraGramos * extraPrecioUnid;
    }

    public float getPrimeraPrecioTotal() {
        return primeraGramos * primeraPrecioUnid;
    }

    public float getSegundaPrecioTotal() {
        return segundaGramos * segundaPrecioUnid;
    }

    public float getTerceraPrecioTotal() {
        return terceraGramos * terceraPrecioUnid;
    }

    public float getCuartaPrecioTotal() {
        return cuartaGramos * cuartaPrecioUnid;
    }

    public float getQuintaPrecioTotal() {
        return quintaGramos * quintaPrecioUnid;
    }
    
    public float getVentaTotal (){
        return getExtraPrecioTotal() + getPrimeraPrecioTotal() + 
               getSegundaPrecioTotal() + getTerceraPrecioTotal() + 
                getCuartaPrecioTotal() + getQuintaPrecioTotal();
    }
    
    public void sumar(Venta v) {
        this.extraGramos += v.extraGramos;
        this.primeraGramos += v.primeraGramos;
        this.segundaGramos += v.segundaGramos;
        this.terceraGramos += v.terceraGramos;
        this.cuartaGramos += v.cuartaGramos;
        this.quintaGramos += v.quintaGramos;
        this.extraPrecioUnid += v.extraPrecioUnid;
        this.primeraPrecioUnid += v.primeraPrecioUnid;
        this.segundaPrecioUnid += v.segundaPrecioUnid;
        this.terceraPrecioUnid += v.terceraPrecioUnid;
        this.cuartaPrecioUnid += v.cuartaPrecioUnid;
        this.quintaPrecioUnid += v.quintaPrecioUnid;
    }
}
