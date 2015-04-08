/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.finanzas.compra;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
    @ManyToOne(optional = false)
    Lote lote;
    @ManyToOne(optional = false)
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 61 * hash + Objects.hashCode(this.fechaCompra);
        hash = 61 * hash + Objects.hashCode(this.lote);
        hash = 61 * hash + Objects.hashCode(this.insumo);
        hash = 61 * hash + Float.floatToIntBits(this.precio);
        hash = 61 * hash + Float.floatToIntBits(this.cantidad);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compra other = (Compra) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fechaCompra, other.fechaCompra)) {
            return false;
        }
        if (!Objects.equals(this.lote, other.lote)) {
            return false;
        }
        if (!Objects.equals(this.insumo, other.insumo)) {
            return false;
        }
        if (Float.floatToIntBits(this.precio) != Float.floatToIntBits(other.precio)) {
            return false;
        }
        if (Float.floatToIntBits(this.cantidad) != Float.floatToIntBits(other.cantidad)) {
            return false;
        }
        return true;
    }
}
