package modelo.produccion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.administracion.Lote;

@Entity
public class MonitoreoDeEnfermedades implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @Basic
    @ManyToOne
    private Lote lote;
    @Basic
    private int modulo;
    @Basic
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Basic
    private int botrytis;
    @Basic
    private int antracnosis;
    @Basic
    private int mycospharella;
    @Basic
    private int mildeoPolvoso;
    @Basic
    private int phytophtora;
    @Basic
    private int bacteriosis;

    public MonitoreoDeEnfermedades() {
    }

    public MonitoreoDeEnfermedades(Lote lote, int modulo, Date fecha, int botrytis, int antracnosis, int mycospharella, int mildeoPolvoso, int phytophtora, int bacteriosis) {
        this.lote = lote;
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

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
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

    public int getAntracnosis() {
        return antracnosis;
    }

    public void setAntracnosis(int antracnosis) {
        this.antracnosis = antracnosis;
    }

    public int getMycospharella() {
        return mycospharella;
    }

    public void setMycospharella(int mycospharella) {
        this.mycospharella = mycospharella;
    }

    public int getMildeoPolvoso() {
        return mildeoPolvoso;
    }

    public void setMildeoPolvoso(int mildeoPolvoso) {
        this.mildeoPolvoso = mildeoPolvoso;
    }

    public int getPhytophtora() {
        return phytophtora;
    }

    public void setPhytophtora(int phytophtora) {
        this.phytophtora = phytophtora;
    }

    public int getBacteriosis() {
        return bacteriosis;
    }

    public void setBacteriosis(int bacteriosis) {
        this.bacteriosis = bacteriosis;
    }

}
