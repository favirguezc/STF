package modelo.produccion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.administracion.Persona;
import modelo.administracion.Lote;

@Entity
public class Recoleccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @ManyToOne
    private Lote lote;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @ManyToOne
    private Persona recolector;
    private float extra;
    private float primera;
    private float segunda;
    private float tercera;
    private float cuarta;
    private float danada;

    public Recoleccion() {
    }

    public Recoleccion(Lote lote, Date fecha, float extra, float primera, float segunda, float tercera, float cuarta, float danada, Persona recolector) {
        this.lote = lote;
        this.fecha = fecha;
        this.extra = extra;
        this.primera = primera;
        this.segunda = segunda;
        this.tercera = tercera;
        this.cuarta = cuarta;
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

    public float getTotal() {
        return extra + primera + segunda + tercera + cuarta + danada;
    }

    public void sumar(Recoleccion r) {
        this.extra += r.extra;
        this.primera += r.primera;
        this.segunda += r.segunda;
        this.tercera += r.tercera;
        this.cuarta += r.cuarta;
        this.danada += r.danada;
    }
}
