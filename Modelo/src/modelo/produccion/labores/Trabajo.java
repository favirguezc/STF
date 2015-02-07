package modelo.produccion.labores;

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
import modelo.produccion.administracion.Modulo;
import modelo.produccion.administracion.Persona;

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
    private float horas;
    private String observaciones;
    @ManyToOne(optional = true)
    private Persona asistente;

    public Trabajo() {
    }

    public Trabajo(Date fecha, Modulo modulo, Labor labor, Persona operario, float jornales, String observaciones, Persona asistente) {
        this.fecha = fecha;
        this.modulo = modulo;
        this.labor = labor;
        this.operario = operario;
        this.horas = jornales;
        this.observaciones = observaciones;
        this.asistente = asistente;
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

    public float getHoras() {
        return horas;
    }

    public void setHoras(float horas) {
        this.horas = horas;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.fecha);
        hash = 29 * hash + Objects.hashCode(this.modulo);
        hash = 29 * hash + Objects.hashCode(this.labor);
        hash = 29 * hash + Objects.hashCode(this.operario);
        hash = 29 * hash + Float.floatToIntBits(this.horas);
        hash = 29 * hash + Objects.hashCode(this.observaciones);
        hash = 29 * hash + Objects.hashCode(this.asistente);
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
        if (Float.floatToIntBits(this.horas) != Float.floatToIntBits(other.horas)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        if (!Objects.equals(this.asistente, other.asistente)) {
            return false;
        }
        return true;
    }

}
