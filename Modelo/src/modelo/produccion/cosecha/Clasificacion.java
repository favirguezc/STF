/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.cosecha;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.produccion.administracion.Modulo;

/**
 *
 * @author fredy
 */
@Entity
public class Clasificacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @ManyToOne(optional = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    private Modulo modulo;
    @Enumerated(EnumType.STRING)
    private TipoDeFresa tipo;
    private float gramos;

    public Clasificacion() {
    }

    public Clasificacion(Modulo modulo, TipoDeFresa tipo, float gramos) {
        this.modulo = modulo;
        this.tipo = tipo;
        this.gramos = gramos;
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

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public TipoDeFresa getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeFresa tipo) {
        this.tipo = tipo;
    }

    public float getGramos() {
        return gramos;
    }

    public void setGramos(float gramos) {
        this.gramos = gramos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 11 * hash + Objects.hashCode(this.modulo);
        hash = 11 * hash + Objects.hashCode(this.tipo);
        hash = 11 * hash + Float.floatToIntBits(this.gramos);
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
        final Clasificacion other = (Clasificacion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.modulo, other.modulo)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (Float.floatToIntBits(this.gramos) != Float.floatToIntBits(other.gramos)) {
            return false;
        }
        return true;
    }

}
