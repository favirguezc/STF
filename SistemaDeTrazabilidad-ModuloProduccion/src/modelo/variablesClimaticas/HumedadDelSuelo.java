package modelo.variablesClimaticas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class HumedadDelSuelo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private float valorEn30Cms;
    private float valorEn15Cms;
    @Temporal(TemporalType.TIME)
    private Date hora;

    public HumedadDelSuelo() {
    }

    public HumedadDelSuelo(Date fecha, float _30Cms, float _15Cms, Date hora) {
        this.fecha = fecha;
        this.valorEn30Cms = _30Cms;
        this.valorEn15Cms = _15Cms;
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getValorEn30Cms() {
        return valorEn30Cms;
    }

    public void setValorEn30Cms(float valorEn30Cms) {
        this.valorEn30Cms = valorEn30Cms;
    }

    public float getValorEn15Cms() {
        return valorEn15Cms;
    }

    public void setValorEn15Cms(float valorEn15Cms) {
        this.valorEn15Cms = valorEn15Cms;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
