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
    private float extraGramos;
    private float primeraGramos;
    private float segundaGramos;
    private float terceraGramos;
    private float cuartaGramos;
    private float danadaGramos;

    public Recoleccion() {
    }

    public Recoleccion(Lote lote, Date fecha, float extra, float primera, float segunda, float tercera, float cuarta, float danada, Persona recolector) {
        this.lote = lote;
        this.fecha = fecha;
        this.extraGramos = extra;
        this.primeraGramos = primera;
        this.segundaGramos = segunda;
        this.terceraGramos = tercera;
        this.cuartaGramos = cuarta;
        this.danadaGramos = danada;
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

    public float getExtraGramos() {
        return extraGramos;
    }

    public void setExtraGramos(float extraGramos) {
        this.extraGramos = extraGramos;
    }

    public float getPrimeraGramos() {
        return primeraGramos;
    }

    public void setPrimeraGramos(float primeraGramos) {
        this.primeraGramos = primeraGramos;
    }

    public float getSegundaGramos() {
        return segundaGramos;
    }

    public void setSegundaGramos(float segundaGramos) {
        this.segundaGramos = segundaGramos;
    }

    public float getTerceraGramos() {
        return terceraGramos;
    }

    public void setTerceraGramos(float terceraGramos) {
        this.terceraGramos = terceraGramos;
    }

    public float getCuartaGramos() {
        return cuartaGramos;
    }

    public void setCuartaGramos(float cuartaGramos) {
        this.cuartaGramos = cuartaGramos;
    }

    public float getDanadaGramos() {
        return danadaGramos;
    }

    public void setDanadaGramos(float danadaGramos) {
        this.danadaGramos = danadaGramos;
    }

    public Persona getRecolector() {
        return recolector;
    }

    public void setRecolector(Persona recolector) {
        this.recolector = recolector;
    }

    public float getBuena() {
        return extraGramos + primeraGramos + segundaGramos + terceraGramos + cuartaGramos;
    }

    public float getTotal() {
        return extraGramos + primeraGramos + segundaGramos + terceraGramos + cuartaGramos + danadaGramos;
    }

    public void sumar(Recoleccion r) {
        this.extraGramos += r.extraGramos;
        this.primeraGramos += r.primeraGramos;
        this.segundaGramos += r.segundaGramos;
        this.terceraGramos += r.terceraGramos;
        this.cuartaGramos += r.cuartaGramos;
        this.danadaGramos += r.danadaGramos;
    }
}
