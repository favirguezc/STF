package modelo.produccion.variablesClimaticas;

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
import javax.persistence.TemporalType;
import modelo.produccion.administracion.Finca;

/**
 *
 * @author fredy
 */
@Entity
public class HumedadDelSuelo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(nullable = false)
    private float valorEn30Cms;
    @Column(nullable = false)
    private float valorEn15Cms;
    @ManyToOne(optional = false)
    private Finca finca;

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
    public float getValorEn30Cms() {
        return valorEn30Cms;
    }

    /**
     *
     * @param valorEn30Cms
     */
    public void setValorEn30Cms(float valorEn30Cms) {
        this.valorEn30Cms = valorEn30Cms;
    }

    /**
     *
     * @return
     */
    public float getValorEn15Cms() {
        return valorEn15Cms;
    }

    /**
     *
     * @param valorEn15Cms
     */
    public void setValorEn15Cms(float valorEn15Cms) {
        this.valorEn15Cms = valorEn15Cms;
    }

    /**
     *
     * @return
     */
    public Date getHora() {
        return hora;
    }

    /**
     *
     * @param hora
     */
    public void setHora(Date hora) {
        this.hora = hora;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @param h
     */
    public void sumar(HumedadDelSuelo h) {
        this.valorEn15Cms += h.valorEn15Cms;
        this.valorEn30Cms += h.valorEn30Cms;
    }

    /**
     *
     * @param n
     */
    public void dividir(int n) {
        this.valorEn15Cms = this.valorEn15Cms / n;
        this.valorEn30Cms = this.valorEn30Cms / n;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.fecha);
        hash = 11 * hash + Objects.hashCode(this.hora);
        hash = 11 * hash + Float.floatToIntBits(this.valorEn30Cms);
        hash = 11 * hash + Float.floatToIntBits(this.valorEn15Cms);
        hash = 11 * hash + Objects.hashCode(this.finca);
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
        final HumedadDelSuelo other = (HumedadDelSuelo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (Float.floatToIntBits(this.valorEn30Cms) != Float.floatToIntBits(other.valorEn30Cms)) {
            return false;
        }
        if (Float.floatToIntBits(this.valorEn15Cms) != Float.floatToIntBits(other.valorEn15Cms)) {
            return false;
        }
        if (!Objects.equals(this.finca, other.finca)) {
            return false;
        }
        return true;
    }
}
