package modelo.produccion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
    @Basic
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @ManyToOne
    private Modulo modulo;
    @ManyToOne
    private LaborCultural labor;
    @ManyToOne
    private Persona operario;
    @Basic
    private float jornales;
    @Basic
    private String observaciones;
    @ManyToOne
    private Persona asistente;
    @ManyToOne
    private Persona productor;

    public Trabajo() {
    }

    public Trabajo(Date fecha, Modulo modulo, LaborCultural labor, Persona operario, float jornales, String observaciones, Persona asistente, Persona productor) {
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

    public LaborCultural getLabor() {
        return labor;
    }

    public void setLabor(LaborCultural labor) {
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

}
