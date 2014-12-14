package modelo.produccion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.administracion.Persona;

@Entity
public class TrampaDeInsectos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String especie;
    private int individuos;
    private boolean cambioDePegante;
    private String observaciones;
    @ManyToOne
    private Persona asistente;
    @ManyToOne
    private Persona productor;

    public TrampaDeInsectos() {
    }

    public TrampaDeInsectos(Date fecha, String nombre, String especie, int individuos, boolean cambio, String observaciones, Persona asistente, Persona productor) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.especie = especie;
        this.individuos = individuos;
        this.cambioDePegante = cambio;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIndividuos() {
        return individuos;
    }

    public void setIndividuos(int individuos) {
        this.individuos = individuos;
    }

    public boolean isCambioDePegante() {
        return cambioDePegante;
    }

    public void setCambioDePegante(boolean cambioDePegante) {
        this.cambioDePegante = cambioDePegante;
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
