package modelo.produccion;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.administracion.Modulo;

@Entity
public class MonitoreoDePlagas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @ManyToOne
    private Modulo modulo;
    private int aranita;
    private int thrips;
    private boolean cyclamen;
    private boolean chisas;
    private boolean babosas;
    private String otro;
    private boolean flor;
    private boolean fruto;
    private int coronas;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public MonitoreoDePlagas() {
    }

    public MonitoreoDePlagas(Modulo modulo, int aranita, int thrips, boolean cyclamen, boolean chisas, boolean babosas, String otro, boolean flor, boolean fruto, int coronas, Date fecha) {
        this.modulo = modulo;
        this.aranita = aranita;
        this.thrips = thrips;
        this.cyclamen = cyclamen;
        this.chisas = chisas;
        this.babosas = babosas;
        this.otro = otro;
        this.flor = flor;
        this.fruto = fruto;
        this.coronas = coronas;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public int getAranita() {
        return aranita;
    }

    public void setAranita(int aranita) {
        this.aranita = aranita;
    }

    public int getThrips() {
        return thrips;
    }

    public void setThrips(int thrips) {
        this.thrips = thrips;
    }

    public boolean isCyclamen() {
        return cyclamen;
    }

    public void setCyclamen(boolean cyclamen) {
        this.cyclamen = cyclamen;
    }

    public boolean isChisas() {
        return chisas;
    }

    public void setChisas(boolean chisas) {
        this.chisas = chisas;
    }

    public boolean isBabosas() {
        return babosas;
    }

    public void setBabosas(boolean babosas) {
        this.babosas = babosas;
    }

    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public boolean isFlor() {
        return flor;
    }

    public void setFlor(boolean flor) {
        this.flor = flor;
    }

    public boolean isFruto() {
        return fruto;
    }

    public void setFruto(boolean fruto) {
        this.fruto = fruto;
    }

    public int getCoronas() {
        return coronas;
    }

    public void setCoronas(int coronas) {
        this.coronas = coronas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.modulo);
        hash = 67 * hash + this.aranita;
        hash = 67 * hash + this.thrips;
        hash = 67 * hash + (this.cyclamen ? 1 : 0);
        hash = 67 * hash + (this.chisas ? 1 : 0);
        hash = 67 * hash + (this.babosas ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.otro);
        hash = 67 * hash + (this.flor ? 1 : 0);
        hash = 67 * hash + (this.fruto ? 1 : 0);
        hash = 67 * hash + this.coronas;
        hash = 67 * hash + Objects.hashCode(this.fecha);
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
        final MonitoreoDePlagas other = (MonitoreoDePlagas) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.modulo, other.modulo)) {
            return false;
        }
        if (this.aranita != other.aranita) {
            return false;
        }
        if (this.thrips != other.thrips) {
            return false;
        }
        if (this.cyclamen != other.cyclamen) {
            return false;
        }
        if (this.chisas != other.chisas) {
            return false;
        }
        if (this.babosas != other.babosas) {
            return false;
        }
        if (!Objects.equals(this.otro, other.otro)) {
            return false;
        }
        if (this.flor != other.flor) {
            return false;
        }
        if (this.fruto != other.fruto) {
            return false;
        }
        if (this.coronas != other.coronas) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }

}