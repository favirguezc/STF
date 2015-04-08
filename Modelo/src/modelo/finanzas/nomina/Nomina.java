/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.finanzas.nomina;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Column;
import modelo.produccion.administracion.Finca;
import modelo.produccion.administracion.Persona;

/**
 *
 * @author John Fredy
 */
@Entity
public class Nomina implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDesde;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @ManyToOne(optional = false)
    private Persona trabajador;
    @Column(nullable = false)
    private float total;
    @ManyToOne(optional = false)
    private Finca finca;

    public Nomina() {
    }

    public Nomina(Date fecha, Persona trabajador) {
        this.fecha = fecha;
        this.trabajador = trabajador;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Persona getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Persona trabajador) {
        this.trabajador = trabajador;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Finca getFinca() {
        return finca;
    }

    public void setFinca(Finca finca) {
        this.finca = finca;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.fecha);
        hash = 71 * hash + Objects.hashCode(this.trabajador);
        hash = 71 * hash + Float.floatToIntBits(this.total);
        hash = 71 * hash + Objects.hashCode(this.finca);
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
        final Nomina other = (Nomina) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.trabajador, other.trabajador)) {
            return false;
        }
        if (Float.floatToIntBits(this.total) != Float.floatToIntBits(other.total)) {
            return false;
        }
        if (!Objects.equals(this.finca, other.finca)) {
            return false;
        }
        return true;
    }
    
    
}
