package modelo.produccion.aplicaciones;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.produccion.administracion.Persona;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.monitoreo.Variable;

/**
 *
 * @author fredy
 */
@Entity
public class Aplicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Enumerated(EnumType.STRING)
    private TipoDeAplicacion tipo;
    @ManyToOne(optional = false)
    private Modulo modulo;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaDeAutorizacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaDeAplicacion;
    @ManyToOne(optional = false)
    private Insumo producto;
    @ManyToOne(optional = false)
    private Variable motivo1;
    @ManyToOne(optional = true)
    private Variable motivo2;
    @ManyToOne(optional = true)
    private Variable motivo3;
    private float cantidad;
    private float litrosDeAgua;
    @Enumerated(EnumType.STRING)
    private MetodoDeAplicacion metodo;
    @ManyToOne(optional = false)
    private Persona responsable;
    @ManyToOne(optional = false)
    private Persona autoriza;
    private float horas;
    private String observaciones;

    /**
     *
     */
    public Aplicacion() {
    }

    /**
     *
     * @param tipo
     * @param modulo
     * @param fechaDeAutorizacion
     * @param fechaDeAplicacion
     * @param producto
     * @param motivo1
     * @param motivo2
     * @param motivo3
     * @param cantidad
     * @param litrosDeAgua
     * @param metodo
     * @param responsable
     * @param autoriza
     * @param jornales
     * @param observaciones
     */
    public Aplicacion(TipoDeAplicacion tipo, Modulo modulo, Date fechaDeAutorizacion, Date fechaDeAplicacion, Insumo producto, Variable motivo1, Variable motivo2, Variable motivo3, float cantidad, float litrosDeAgua, MetodoDeAplicacion metodo, Persona responsable, Persona autoriza, float jornales, String observaciones) {
        this.tipo = tipo;
        this.modulo = modulo;
        this.fechaDeAutorizacion = fechaDeAutorizacion;
        this.fechaDeAplicacion = fechaDeAplicacion;
        this.producto = producto;
        this.motivo1 = motivo1;
        this.motivo2 = motivo2;
        this.motivo3 = motivo3;
        this.cantidad = cantidad;
        this.litrosDeAgua = litrosDeAgua;
        this.metodo = metodo;
        this.responsable = responsable;
        this.autoriza = autoriza;
        this.horas = jornales;
        this.observaciones = observaciones;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public TipoDeAplicacion getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(TipoDeAplicacion tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public Modulo getModulo() {
        return modulo;
    }

    /**
     *
     * @param modulo
     */
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    /**
     *
     * @return
     */
    public Date getFechaDeAutorizacion() {
        return fechaDeAutorizacion;
    }

    /**
     *
     * @param fechaDeAutorizacion
     */
    public void setFechaDeAutorizacion(Date fechaDeAutorizacion) {
        this.fechaDeAutorizacion = fechaDeAutorizacion;
    }

    /**
     *
     * @return
     */
    public Date getFechaDeAplicacion() {
        return fechaDeAplicacion;
    }

    /**
     *
     * @param fechaDeAplicacion
     */
    public void setFechaDeAplicacion(Date fechaDeAplicacion) {
        this.fechaDeAplicacion = fechaDeAplicacion;
    }

    /**
     *
     * @return
     */
    public Insumo getProducto() {
        return producto;
    }

    /**
     *
     * @param producto
     */
    public void setProducto(Insumo producto) {
        this.producto = producto;
    }

    /**
     *
     * @return
     */
    public Variable getMotivo1() {
        return motivo1;
    }

    /**
     *
     * @param motivo1
     */
    public void setMotivo1(Variable motivo1) {
        this.motivo1 = motivo1;
    }

    /**
     *
     * @return
     */
    public Variable getMotivo2() {
        return motivo2;
    }

    /**
     *
     * @param motivo2
     */
    public void setMotivo2(Variable motivo2) {
        this.motivo2 = motivo2;
    }

    /**
     *
     * @return
     */
    public Variable getMotivo3() {
        return motivo3;
    }

    /**
     *
     * @param motivo3
     */
    public void setMotivo3(Variable motivo3) {
        this.motivo3 = motivo3;
    }

    /**
     *
     * @return
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     *
     * @param cantidad
     */
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    /**
     *
     * @return
     */
    public float getLitrosDeAgua() {
        return litrosDeAgua;
    }

    /**
     *
     * @param litrosDeAgua
     */
    public void setLitrosDeAgua(float litrosDeAgua) {
        this.litrosDeAgua = litrosDeAgua;
    }

    /**
     *
     * @return
     */
    public MetodoDeAplicacion getMetodo() {
        return metodo;
    }

    /**
     *
     * @param metodo
     */
    public void setMetodo(MetodoDeAplicacion metodo) {
        this.metodo = metodo;
    }

    /**
     *
     * @return
     */
    public Persona getResponsable() {
        return responsable;
    }

    /**
     *
     * @param responsable
     */
    public void setResponsable(Persona responsable) {
        this.responsable = responsable;
    }

    /**
     *
     * @return
     */
    public Persona getAutoriza() {
        return autoriza;
    }

    /**
     *
     * @param autoriza
     */
    public void setAutoriza(Persona autoriza) {
        this.autoriza = autoriza;
    }

    /**
     *
     * @return
     */
    public float getHoras() {
        return horas;
    }

    /**
     *
     * @param horas
     */
    public void setHoras(float horas) {
        this.horas = horas;
    }

    /**
     *
     * @return
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     *
     * @param observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 43 * hash + Objects.hashCode(this.tipo);
        hash = 43 * hash + Objects.hashCode(this.modulo);
        hash = 43 * hash + Objects.hashCode(this.fechaDeAutorizacion);
        hash = 43 * hash + Objects.hashCode(this.fechaDeAplicacion);
        hash = 43 * hash + Objects.hashCode(this.producto);
        hash = 43 * hash + Objects.hashCode(this.motivo1);
        hash = 43 * hash + Objects.hashCode(this.motivo2);
        hash = 43 * hash + Objects.hashCode(this.motivo3);
        hash = 43 * hash + Float.floatToIntBits(this.cantidad);
        hash = 43 * hash + Float.floatToIntBits(this.litrosDeAgua);
        hash = 43 * hash + Objects.hashCode(this.metodo);
        hash = 43 * hash + Objects.hashCode(this.responsable);
        hash = 43 * hash + Objects.hashCode(this.autoriza);
        hash = 43 * hash + Float.floatToIntBits(this.horas);
        hash = 43 * hash + Objects.hashCode(this.observaciones);
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
        final Aplicacion other = (Aplicacion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.modulo, other.modulo)) {
            return false;
        }
        if (!Objects.equals(this.fechaDeAutorizacion, other.fechaDeAutorizacion)) {
            return false;
        }
        if (!Objects.equals(this.fechaDeAplicacion, other.fechaDeAplicacion)) {
            return false;
        }
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        if (!Objects.equals(this.motivo1, other.motivo1)) {
            return false;
        }
        if (!Objects.equals(this.motivo2, other.motivo2)) {
            return false;
        }
        if (!Objects.equals(this.motivo3, other.motivo3)) {
            return false;
        }
        if (Float.floatToIntBits(this.cantidad) != Float.floatToIntBits(other.cantidad)) {
            return false;
        }
        if (Float.floatToIntBits(this.litrosDeAgua) != Float.floatToIntBits(other.litrosDeAgua)) {
            return false;
        }
        if (this.metodo != other.metodo) {
            return false;
        }
        if (!Objects.equals(this.responsable, other.responsable)) {
            return false;
        }
        if (!Objects.equals(this.autoriza, other.autoriza)) {
            return false;
        }
        if (Float.floatToIntBits(this.horas) != Float.floatToIntBits(other.horas)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        return true;
    }
    
}
