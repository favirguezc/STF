package modelo.variablesClimaticas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class ControlDeLluvias implements Serializable {

    @Basic
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @Basic
    private float mmDeLluvia;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    public ControlDeLluvias() {
    }

    public ControlDeLluvias(Date fecha, float mmDeLluvia) {
        this.fecha = fecha;
        this.mmDeLluvia = mmDeLluvia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getMmDeLluvia() {
        return mmDeLluvia;
    }

    public void setMmDeLluvia(float mmDeLluvia) {
        this.mmDeLluvia = mmDeLluvia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
