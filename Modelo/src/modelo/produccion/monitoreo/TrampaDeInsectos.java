package modelo.produccion.monitoreo;

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
import modelo.produccion.administracion.Persona;

@Entity
public class TrampaDeInsectos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String especie;
    private int individuos;
    private boolean cambioDePegante;
    private String observaciones;
    @ManyToOne(optional = true)
    private Persona asistente;
    @ManyToOne(optional = true)
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.fecha);
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.especie);
        hash = 29 * hash + this.individuos;
        hash = 29 * hash + (this.cambioDePegante ? 1 : 0);
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
        final TrampaDeInsectos other = (TrampaDeInsectos) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.especie, other.especie)) {
            return false;
        }
        if (this.individuos != other.individuos) {
            return false;
        }
        if (this.cambioDePegante != other.cambioDePegante) {
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
