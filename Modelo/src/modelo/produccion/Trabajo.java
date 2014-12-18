package modelo.produccion;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.administracion.Modulo;
import modelo.administracion.Persona;

@Entity
public class Trabajo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    @ManyToOne(optional = false)
    private Modulo modulo;
    @ManyToOne(optional = false)
    private Labor labor;
    @ManyToOne(optional = false)
    private Persona operario;
    private float jornales;
    private String observaciones;
    @ManyToOne(optional = true)
    private Persona asistente;
    @ManyToOne(optional = true)
    private Persona productor;

    public Trabajo() {
    }

    public Trabajo(Date fecha, Modulo modulo, Labor labor, Persona operario, float jornales, String observaciones, Persona asistente, Persona productor) {
        this.fecha = fecha;
        this.modulo = modulo;
        this.labor = labor;
        this.operario = operario;
        this.jornales = jornales;
        this.observaciones = observaciones;
        this.asistente = asistente;
        this.productor = productor;
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

    public Labor getLabor() {
        return labor;
    }

    public void setLabor(Labor labor) {
        this.labor = labor;
    }

    public Persona getOperario() {
        return operario;
    }

    public void setOperario(Persona operario) {
        this.operario = operario;
    }

    public float getJornales() {
        return jornales;
    }

    public void setJornales(float jornales) {
        this.jornales = jornales;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Persona getAsistente() {
        return asistente;
    }

    public void setAsistente(Persona asistente) {
        this.asistente = asistente;
    }

    public Persona getProductor() {
        return productor;
    }

    public void setProductor(Persona productor) {
        this.productor = productor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.fecha);
        hash = 29 * hash + Objects.hashCode(this.modulo);
        hash = 29 * hash + Objects.hashCode(this.labor);
        hash = 29 * hash + Objects.hashCode(this.operario);
        hash = 29 * hash + Float.floatToIntBits(this.jornales);
        hash = 29 * hash + Objects.hashCode(this.observaciones);
        hash = 29 * hash + Objects.hashCode(this.asistente);
        hash = 29 * hash + Objects.hashCode(this.productor);
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
        final Trabajo other = (Trabajo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.modulo, other.modulo)) {
            return false;
        }
        if (!Objects.equals(this.labor, other.labor)) {
            return false;
        }
        if (!Objects.equals(this.operario, other.operario)) {
            return false;
        }
        if (Float.floatToIntBits(this.jornales) != Float.floatToIntBits(other.jornales)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        if (!Objects.equals(this.asistente, other.asistente)) {
            return false;
        }
        if (!Objects.equals(this.productor, other.productor)) {
            return false;
        }
        return true;
    }

}
