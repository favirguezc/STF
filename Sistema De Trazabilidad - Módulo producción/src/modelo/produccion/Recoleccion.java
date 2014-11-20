package modelo.produccion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.administracion.Persona;
import modelo.administracion.Lote;

@Entity
public class Recoleccion implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @Basic
    @ManyToOne
    private Lote lote;
    @Basic
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Basic
    private float extra;
    @Basic
    private float primera;
    @Basic
    private float segunda;
    @Basic
    private float tercera;
    @Basic
    private float cuarta;
    @Basic
    private float quinta;
    @Basic
    private float danada;
    @Basic
    @ManyToOne
    private Persona recolector;

    public Recoleccion() {
    }

    public Recoleccion(Lote lote, Date fecha, float extra, float primera, float segunda, float tercera, float cuarta, float quinta, float danada, Persona recolector) {
        this.lote = lote;
        this.fecha = fecha;
        this.extra = extra;
        this.primera = primera;
        this.segunda = segunda;
        this.tercera = tercera;
        this.cuarta = cuarta;
        this.quinta = quinta;
        this.danada = danada;
        this.recolector = recolector;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getExtra() {
        return extra;
    }

    public void setExtra(float extra) {
        this.extra = extra;
    }

    public float getPrimera() {
        return primera;
    }

    public void setPrimera(float primera) {
        this.primera = primera;
    }

    public float getSegunda() {
        return segunda;
    }

    public void setSegunda(float segunda) {
        this.segunda = segunda;
    }

    public float getTercera() {
        return tercera;
    }

    public void setTercera(float tercera) {
        this.tercera = tercera;
    }

    public float getCuarta() {
        return cuarta;
    }

    public void setCuarta(float cuarta) {
        this.cuarta = cuarta;
    }

    public float getQuinta() {
        return quinta;
    }

    public void setQuinta(float quinta) {
        this.quinta = quinta;
    }

    public float getDanada() {
        return danada;
    }

    public void setDanada(float danada) {
        this.danada = danada;
    }

    public Persona getRecolector() {
        return recolector;
    }

    public void setRecolector(Persona recolector) {
        this.recolector = recolector;
    }

}
