/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.finanzas.compra;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.produccion.administracion.Lote;
import modelo.produccion.aplicaciones.Insumo;

/**
 *
 * @author John Fredy
 */
@Entity
public class Compra implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    Date fechaCompra;
    @ManyToOne
    Lote lote;
    @ManyToOne
    Insumo insumo;
    @Column(nullable = false)
    float precio;
    @Column(nullable = false)
    float cantidad;

    public Compra() {
    }

    public Compra(Date fechaCompra, Lote lote, Insumo insumo, float precio, float cantidad) {
        this.fechaCompra = fechaCompra;
        this.lote = lote;
        this.insumo = insumo;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
    public float getTotal(){
        return this.cantidad * this.precio;
    }
    
    public void sumar(Compra c) {
        //Please insert code here.
    }
}
