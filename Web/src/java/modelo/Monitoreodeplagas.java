/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fredy
 */
@Entity
@Table(name = "monitoreodeplagas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monitoreodeplagas.findAll", query = "SELECT m FROM Monitoreodeplagas m"),
    @NamedQuery(name = "Monitoreodeplagas.findById", query = "SELECT m FROM Monitoreodeplagas m WHERE m.id = :id"),
    @NamedQuery(name = "Monitoreodeplagas.findByAranita", query = "SELECT m FROM Monitoreodeplagas m WHERE m.aranita = :aranita"),
    @NamedQuery(name = "Monitoreodeplagas.findByBabosas", query = "SELECT m FROM Monitoreodeplagas m WHERE m.babosas = :babosas"),
    @NamedQuery(name = "Monitoreodeplagas.findByChisas", query = "SELECT m FROM Monitoreodeplagas m WHERE m.chisas = :chisas"),
    @NamedQuery(name = "Monitoreodeplagas.findByCoronas", query = "SELECT m FROM Monitoreodeplagas m WHERE m.coronas = :coronas"),
    @NamedQuery(name = "Monitoreodeplagas.findByCyclamen", query = "SELECT m FROM Monitoreodeplagas m WHERE m.cyclamen = :cyclamen"),
    @NamedQuery(name = "Monitoreodeplagas.findByFecha", query = "SELECT m FROM Monitoreodeplagas m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Monitoreodeplagas.findByFlor", query = "SELECT m FROM Monitoreodeplagas m WHERE m.flor = :flor"),
    @NamedQuery(name = "Monitoreodeplagas.findByFruto", query = "SELECT m FROM Monitoreodeplagas m WHERE m.fruto = :fruto"),
    @NamedQuery(name = "Monitoreodeplagas.findByOtro", query = "SELECT m FROM Monitoreodeplagas m WHERE m.otro = :otro"),
    @NamedQuery(name = "Monitoreodeplagas.findByThrips", query = "SELECT m FROM Monitoreodeplagas m WHERE m.thrips = :thrips")})
public class Monitoreodeplagas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ARANITA")
    private Integer aranita;
    @Column(name = "BABOSAS")
    private Boolean babosas;
    @Column(name = "CHISAS")
    private Boolean chisas;
    @Column(name = "CORONAS")
    private Integer coronas;
    @Column(name = "CYCLAMEN")
    private Boolean cyclamen;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "FLOR")
    private Boolean flor;
    @Column(name = "FRUTO")
    private Boolean fruto;
    @Column(name = "OTRO")
    private String otro;
    @Column(name = "THRIPS")
    private Integer thrips;

    public Monitoreodeplagas() {
    }

    public Monitoreodeplagas(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAranita() {
        return aranita;
    }

    public void setAranita(Integer aranita) {
        this.aranita = aranita;
    }

    public Boolean getBabosas() {
        return babosas;
    }

    public void setBabosas(Boolean babosas) {
        this.babosas = babosas;
    }

    public Boolean getChisas() {
        return chisas;
    }

    public void setChisas(Boolean chisas) {
        this.chisas = chisas;
    }

    public Integer getCoronas() {
        return coronas;
    }

    public void setCoronas(Integer coronas) {
        this.coronas = coronas;
    }

    public Boolean getCyclamen() {
        return cyclamen;
    }

    public void setCyclamen(Boolean cyclamen) {
        this.cyclamen = cyclamen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getFlor() {
        return flor;
    }

    public void setFlor(Boolean flor) {
        this.flor = flor;
    }

    public Boolean getFruto() {
        return fruto;
    }

    public void setFruto(Boolean fruto) {
        this.fruto = fruto;
    }

    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public Integer getThrips() {
        return thrips;
    }

    public void setThrips(Integer thrips) {
        this.thrips = thrips;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monitoreodeplagas)) {
            return false;
        }
        Monitoreodeplagas other = (Monitoreodeplagas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Monitoreodeplagas[ id=" + id + " ]";
    }
    
}
