package modelo.variablesClimaticas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class HumedadDelSuelo implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Basic
    private float _30Cms;
    
    @Basic
    private float _15Cms;
    
    @Basic
    @Temporal(TemporalType.TIME)
    private Date hora;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    public HumedadDelSuelo() {
    }

    public HumedadDelSuelo(Date fecha, float _30Cms, float _15Cms, Date hora) {
        this.fecha = fecha;
        this._30Cms = _30Cms;
        this._15Cms = _15Cms;
        this.hora = hora;
    }
    
    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float get30Cms() {
        return _30Cms;
    }

    public void set30Cms(float _30Cms) {
        this._30Cms = _30Cms;
    }

    public float get15Cms() {
        return _15Cms;
    }

    public void set15Cms(float _15Cms) {
        this._15Cms = _15Cms;
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
