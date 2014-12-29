/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.util.JsfUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "temperaturaArchivo")
public class TemperaturaArchivo {
    private UploadedFile archivo;

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }
    
    public void upload() {
        if(archivo != null) {
            JsfUtil.addSuccessMessage(archivo.getFileName() + " is uploaded.");
            System.out.println(archivo.getFileName());
        }
    }
}