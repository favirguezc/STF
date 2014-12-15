package modelo.produccion;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @ManyToOne
    private Insumo producto;
    private String motivo;
    private boolean pc;
    private boolean tr;
    private float cantidad;
    private float agua;
    private String equipo;
    @ManyToOne
    private Persona responsable;
    @ManyToOne
    private Persona aprobante;
    private float jornales;
    private String observaciones;
    @ManyToOne
    private Lote lote;
    @ManyToOne
    private Persona asistente;
    @ManyToOne
    private Persona productor;

    public AplicacionFitosanitaria(Date fecha, Insumo producto, String motivo, boolean pc, boolean tr, float cantidad, float agua, String equipo, Persona responsable, Persona aprobante, float jornales, String observaciones, Lote lote, Persona asistente, Persona productor) {
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

    public Insumo getProducto() {
        return producto;
    }

    public void setProducto(Insumo producto) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 41 * hash + Objects.hashCode(this.fecha);
        hash = 41 * hash + Objects.hashCode(this.producto);
        hash = 41 * hash + Objects.hashCode(this.motivo);
        hash = 41 * hash + (this.pc ? 1 : 0);
        hash = 41 * hash + (this.tr ? 1 : 0);
        hash = 41 * hash + Float.floatToIntBits(this.cantidad);
        hash = 41 * hash + Float.floatToIntBits(this.agua);
        hash = 41 * hash + Objects.hashCode(this.equipo);
        hash = 41 * hash + Objects.hashCode(this.responsable);
        hash = 41 * hash + Objects.hashCode(this.aprobante);
        hash = 41 * hash + Float.floatToIntBits(this.jornales);
        hash = 41 * hash + Objects.hashCode(this.observaciones);
        hash = 41 * hash + Objects.hashCode(this.lote);
        hash = 41 * hash + Objects.hashCode(this.asistente);
        hash = 41 * hash + Objects.hashCode(this.productor);
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
        final AplicacionFitosanitaria other = (AplicacionFitosanitaria) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        if (!Objects.equals(this.motivo, other.motivo)) {
            return false;
        }
        if (this.pc != other.pc) {
            return false;
        }
        if (this.tr != other.tr) {
            return false;
        }
        if (Float.floatToIntBits(this.cantidad) != Float.floatToIntBits(other.cantidad)) {
            return false;
        }
        if (Float.floatToIntBits(this.agua) != Float.floatToIntBits(other.agua)) {
            return false;
        }
        if (!Objects.equals(this.equipo, other.equipo)) {
            return false;
        }
        if (!Objects.equals(this.responsable, other.responsable)) {
            return false;
        }
        if (!Objects.equals(this.aprobante, other.aprobante)) {
            return false;
        }
        if (Float.floatToIntBits(this.jornales) != Float.floatToIntBits(other.jornales)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        if (!Objects.equals(this.lote, other.lote)) {
            return false;
        }
        if (!Objects.equals(this.asistente, other.asistente)) {
            return false;
        }
        if (!Objects.equals(this.productor, other.productor)) {
            return false;
        }
        return true;
    }

}
