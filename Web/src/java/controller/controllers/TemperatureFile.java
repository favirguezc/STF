/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import controller.util.DateParser;
import controller.util.JsfUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import model.administration.ModuleClass;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "temperatureFile")
public class TemperatureFile {

    public void upload(FileUploadEvent event) {
        String nombre = "temperature" + new Date().getTime() + ".txt";
        guardar(nombre, event.getFile());
        int creados = procesar(nombre);
        eliminar(nombre);
        JsfUtil.addSuccessMessage("Se añadieron " + creados + " registros de Temperatura.");
    }

    private void guardar(String nombre, UploadedFile file) {
        try {
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(nombre));
            InputStream in = file.getInputstream();
            int read;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private int procesar(String nombre) {
        FileReader fr;
        BufferedReader br;
        ModuleClass moduleclass;
        int saltos = 15, creados = 0;

        try {
            fr = new FileReader(new File(nombre));
            br = new BufferedReader(fr);
            String linea;
            br.readLine();
            linea = br.readLine();
            long nds = Long.parseLong(linea.split(",")[5]);
            moduleclass = new ThermometerController().find(nds).getModuleObject();
            TemperatureController controller = new TemperatureController();
            while (linea != null) {
                Date fecha = DateParser.parseFecha(linea.split(",")[1].split(" ")[0]);
                Date hora = DateParser.parseHora(linea.split(",")[1].split(" ")[1]);
                float temperature = Float.parseFloat(linea.split(",")[2]);
                float soilmoisture = Float.parseFloat(linea.split(",")[3]);
                float puntoDeRocio = Float.parseFloat(linea.split(",")[4]);
                controller.guardar(controller.nuevo(fecha, hora, temperature, soilmoisture, puntoDeRocio, moduleclass));
                creados++;
                for (int i = 1; i < saltos; i++) {
                    if ((linea = br.readLine()) == null) {
                        break;
                    }
                }
                if (linea != null) {
                    linea = br.readLine();
                }
            }
            br.close();
            fr.close();
            return creados;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void eliminar(String nombre) {
        new File(nombre).delete();
    }

}
