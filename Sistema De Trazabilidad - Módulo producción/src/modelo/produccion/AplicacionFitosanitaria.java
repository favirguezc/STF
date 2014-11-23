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
import modelo.administracion.Persona;
import modelo.administracion.Lote;

@Entity
public class AplicacionFitosanitaria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Basic
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Basic
    @ManyToOne
    private ProductoFitosanitario producto;
    @Basic
    private String motivo;
    @Basic
    private boolean pc;
    @Basic
    private boolean tr;
    @Basic
    private float cantidad;
    @Basic
    private float agua;
    @Basic
    private String equipo;
    @Basic
    @ManyToOne
    private Persona responsable;
    @Basic
    @ManyToOne
    private Persona aprobante;
    @Basic
    private float jornales;
    @Basic
    private String observaciones;
    @Basic
    @ManyToOne
    private Lote lote;
    @Basic
    @ManyToOne
    private Persona asistente;
    @Basic
    @ManyToOne
    private Persona productor;

    public AplicacionFitosanitaria(Date fecha, ProductoFitosanitario producto, String motivo, boolean pc, boolean tr, float cantidad, float agua, String equipo, Persona responsable, Persona aprobante, float jornales, String observaciones, Lote lote, Persona asistente, Persona productor) {
        this.fecha = fecha;
        this.producto = producto;
        this.motivo = motivo;
        this.pc = pc;
        this.tr = tr;
        this.cantidad = cantidad;
        this.agua = agua;
        this.equipo = equipo;
        this.responsable = responsable;
        this.aprobante = aprobante;
        this.jornales = jornales;
        this.observaciones = observaciones;
        this.lote = lote;
        this.asistente = asistente;
        this.productor = productor;
    }

    public AplicacionFitosanitaria() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ProductoFitosanitario getProducto() {
        return producto;
    }

    public void setProducto(ProductoFitosanitario producto) {
        this.producto = producto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isPc() {
        return pc;
    }

    public void setPc(boolean pc) {
        this.pc = pc;
    }

    public boolean isTr() {
        return tr;
    }

    public void setTr(boolean tr) {
        this.tr = tr;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getAgua() {
        return agua;
    }

    public void setAgua(float agua) {
        this.agua = agua;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public Persona getResponsable() {
        return responsable;
    }

    public void setResponsable(Persona responsable) {
        this.responsable = responsable;
    }

    public Persona getAprobante() {
        return aprobante;
    }

    public void setAprobante(Persona aprobante) {
        this.aprobante = aprobante;
    }

    public float getJornales() {
        return jornales;
    }

    public void setJornales(float jornales) {
        this.jornales = jornales;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Persona getAsistente() {
        return asistente;
    }

    public void setAsistente(Persona asistente) {
        this.asistente = asistente;
    }

    public Persona getProductor() {
        return productor;
    }

    public void setProductor(Persona productor) {
        this.productor = productor;
    }

}
