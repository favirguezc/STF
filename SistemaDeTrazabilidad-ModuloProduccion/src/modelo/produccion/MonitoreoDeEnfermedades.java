package modelo.produccion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.administracion.Modulo;

@Entity
public class MonitoreoDeEnfermedades implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @ManyToOne
    private Modulo modulo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private int botrytis;
    private boolean antracnosis;
    private int mycospharella;
    private boolean mildeoPolvoso;
    private boolean phytophtora;
    private boolean bacteriosis;

    public MonitoreoDeEnfermedades() {
    }

    public MonitoreoDeEnfermedades(Modulo modulo, Date fecha, int botrytis, boolean antracnosis, int mycospharella, boolean mildeoPolvoso, boolean phytophtora, boolean bacteriosis) {
        this.modulo = modulo;
        this.fecha = fecha;
        this.botrytis = botrytis;
        this.antracnosis = antracnosis;
        this.mycospharella = mycospharella;
        this.mildeoPolvoso = mildeoPolvoso;
        this.phytophtora = phytophtora;
        this.bacteriosis = bacteriosis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getBotrytis() {
        return botrytis;
    }

    public void setBotrytis(int botrytis) {
        this.botrytis = botrytis;
    }

    public boolean isAntracnosis() {
        return antracnosis;
    }

    public void setAntracnosis(boolean antracnosis) {
        this.antracnosis = antracnosis;
    }

    public int getMycospharella() {
        return mycospharella;
    }

    public void setMycospharella(int mycospharella) {
        this.mycospharella = mycospharella;
    }

    public boolean isMildeoPolvoso() {
        return mildeoPolvoso;
    }

    public void setMildeoPolvoso(boolean mildeoPolvoso) {
        this.mildeoPolvoso = mildeoPolvoso;
    }

    public boolean isPhytophtora() {
        return phytophtora;
    }

    public void setPhytophtora(boolean phytophtora) {
        this.phytophtora = phytophtora;
    }

    public boolean isBacteriosis() {
        return bacteriosis;
    }

    public void setBacteriosis(boolean bacteriosis) {
        this.bacteriosis = bacteriosis;
    }

    

}
