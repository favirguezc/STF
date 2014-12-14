package modelo.variablesClimaticas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Lluvia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private float mm;
    
    public Lluvia() {
    }

    public Lluvia(Date fecha, float mmDeLluvia) {
        this.fecha = fecha;
        this.mm = mmDeLluvia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getMm() {
        return mm;
    }

    public void setMm(float mm) {
        this.mm = mm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
