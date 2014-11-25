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
public  class Temperatura implements Serializable {


    @Basic
    private float tempMax;    
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;
    @Temporal(TemporalType.TIME)
    @Basic
    private Date horaMin;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(TemporalType.TIME)
    @Basic
    private Date horaMax;
    @Basic
    private float tempMin;
    
    public Temperatura(){

    }


   public float getTempMax() {
        return this.tempMax;
    }


  public void setTempMax (float tempMax) {
        this.tempMax = tempMax;
    }



   public Date getFecha() {
        return this.fecha;
    }


  public void setFecha (Date fecha) {
        this.fecha = fecha;
    }



   public Date getHoraMin() {
        return this.horaMin;
    }


  public void setHoraMin (Date horaMin) {
        this.horaMin = horaMin;
    }



   public Long getId() {
        return this.id;
    }


  public void setId (Long id) {
        this.id = id;
    }



   public Date getHoraMax() {
        return this.horaMax;
    }


  public void setHoraMax (Date horaMax) {
        this.horaMax = horaMax;
    }



   public float getTempMin() {
        return this.tempMin;
    }


  public void setTempMin (float tempMin) {
        this.tempMin = tempMin;
    }

}

