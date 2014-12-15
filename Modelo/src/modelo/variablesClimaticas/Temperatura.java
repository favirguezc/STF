package modelo.variablesClimaticas;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import modelo.administracion.Modulo;

@Entity
public  class Temperatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Temporal(TemporalType.TIME)
    private Date hora;
    private float temperatura;
    private float humedad;
    private float puntoDeRocio;
    @ManyToOne
    private Modulo modulo;
    
    public Temperatura(){
    }

    public Temperatura(Date fecha, Date hora, float temperatura, float humedad, float puntoDeRocio, Modulo modulo) {
        this.fecha = fecha;
        this.hora = hora;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.puntoDeRocio = puntoDeRocio;
        this.modulo = modulo;
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getHumedad() {
        return humedad;
    }

    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    public float getPuntoDeRocio() {
        return puntoDeRocio;
    }

    public void setPuntoDeRocio(float puntoDeRocio) {
        this.puntoDeRocio = puntoDeRocio;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.fecha);
        hash = 71 * hash + Objects.hashCode(this.hora);
        hash = 71 * hash + Float.floatToIntBits(this.temperatura);
        hash = 71 * hash + Float.floatToIntBits(this.humedad);
        hash = 71 * hash + Float.floatToIntBits(this.puntoDeRocio);
        hash = 71 * hash + Objects.hashCode(this.modulo);
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
        final Temperatura other = (Temperatura) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (Float.floatToIntBits(this.temperatura) != Float.floatToIntBits(other.temperatura)) {
            return false;
        }
        if (Float.floatToIntBits(this.humedad) != Float.floatToIntBits(other.humedad)) {
            return false;
        }
        if (Float.floatToIntBits(this.puntoDeRocio) != Float.floatToIntBits(other.puntoDeRocio)) {
            return false;
        }
        if (!Objects.equals(this.modulo, other.modulo)) {
            return false;
        }
        return true;
    }
    
}

