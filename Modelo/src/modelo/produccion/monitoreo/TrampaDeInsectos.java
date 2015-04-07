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
import modelo.produccion.administracion.Finca;

/**
 *
 * @author fredy
 */
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
    @ManyToOne(optional = false)
    private Finca finca;

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     *
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Finca getFinca() {
        return finca;
    }

    public void setFinca(Finca finca) {
        this.finca = finca;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getEspecie() {
        return especie;
    }

    /**
     *
     * @param especie
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     *
     * @return
     */
    public int getIndividuos() {
        return individuos;
    }

    /**
     *
     * @param individuos
     */
    public void setIndividuos(int individuos) {
        this.individuos = individuos;
    }

    /**
     *
     * @return
     */
    public boolean isCambioDePegante() {
        return cambioDePegante;
    }

    /**
     *
     * @param cambioDePegante
     */
    public void setCambioDePegante(boolean cambioDePegante) {
        this.cambioDePegante = cambioDePegante;
    }

    /**
     *
     * @return
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     *
     * @param observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode(this.fecha);
        hash = 47 * hash + Objects.hashCode(this.nombre);
        hash = 47 * hash + Objects.hashCode(this.especie);
        hash = 47 * hash + this.individuos;
        hash = 47 * hash + (this.cambioDePegante ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.observaciones);
        hash = 47 * hash + Objects.hashCode(this.finca);
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
        if (!Objects.equals(this.finca, other.finca)) {
            return false;
        }
        return true;
    }

}
