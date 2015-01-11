/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.monitoreo;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author fredy
 */
@Embeddable
public class Valoracion implements Serializable {
    private Integer conteo;
    private String relacion;
    private Riesgo riesgo;

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
        this.relacion = null;
        this.riesgo = null;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
        this.conteo = null;
        this.riesgo = null;
    }

    public Riesgo getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgo riesgo) {
        this.riesgo = riesgo;
        this.conteo = null;
        this.relacion = null;
    }

    @Override
    public String toString() {
        if(riesgo!=null){
            return riesgo.toString();
        }
        if(relacion!= null){
            return relacion;
        }
        if(conteo!=null){
            return conteo.toString();
        }
        return "";
    }
    
}
