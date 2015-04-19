/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.finanzas.costo;

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
import model.administration.ModuleClass;

/**
 *
 * @author John Fredy
 */
@Entity
public class Costo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Column(nullable = false)
    private TipoCosto tipo;
    @Column
    private String subTipo; //enum(?)
    @Column(nullable = false)
    private String nombre;
    @Column
    private ItemCosto item;
    @Column(nullable = false)
    private float cantidad;
    @Column
    private float precioUnid;
    @ManyToOne(optional = false)
    private ModuleClass modulo;

    public Costo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoCosto getTipo() {
        return tipo;
    }

    public void setTipo(TipoCosto tipo) {
        this.tipo = tipo;
    }

    public String getSubTipo() {
        return subTipo;
    }

    public void setSubTipo(String subTipo) {
        this.subTipo = subTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ItemCosto getItem() {
        return item;
    }

    public void setItem(ItemCosto item) {
        this.item = item;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnid() {
        return precioUnid;
    }

    public void setPrecioUnid(float precioUnid) {
        this.precioUnid = precioUnid;
    }
    
    public float getPrecioTotal(){
        return cantidad * precioUnid;
    }
    
    public ModuleClass getModulo() {
        return modulo;
    }

    public void setModulo(ModuleClass modulo) {
        this.modulo = modulo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 13 * hash + Objects.hashCode(this.fecha);
        hash = 13 * hash + Objects.hashCode(this.tipo);
        hash = 13 * hash + Objects.hashCode(this.subTipo);
        hash = 13 * hash + Objects.hashCode(this.nombre);
        hash = 13 * hash + Objects.hashCode(this.item);
        hash = 13 * hash + Float.floatToIntBits(this.cantidad);
        hash = 13 * hash + Float.floatToIntBits(this.precioUnid);
        hash = 13 * hash + Objects.hashCode(this.modulo);
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
        final Costo other = (Costo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.subTipo, other.subTipo)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (this.item != other.item) {
            return false;
        }
        if (Float.floatToIntBits(this.cantidad) != Float.floatToIntBits(other.cantidad)) {
            return false;
        }
        if (Float.floatToIntBits(this.precioUnid) != Float.floatToIntBits(other.precioUnid)) {
            return false;
        }
        if (!Objects.equals(this.modulo, other.modulo)) {
            return false;
        }
        return true;
    }

    
}
