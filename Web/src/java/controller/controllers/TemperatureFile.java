/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import controller.util.DateParser;
import controller.util.JsfUtil;
import controller.util.Storage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import model.administration.ModuleClass;
import model.weather.Thermometer;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "temperatureFile")
public class TemperatureFile {

    private String message;

    public void upload(FileUploadEvent event) {
        String filename = "temperature" + new Date().getTime() + ".txt";
        Storage.save(filename, event.getFile());
        parse(filename);
        Storage.delete(filename);
        JsfUtil.addSuccessMessage(message);
    }

    private void parse(String filename) {
        FileReader fr;
        BufferedReader br;
        ModuleClass moduleclass;
        int skip = 15, created = 0;

        try {
            fr = new FileReader(new File(filename));
            br = new BufferedReader(fr);
            String line;
            br.readLine();
            line = br.readLine();
            long nds = Long.parseLong(line.split(",")[5]);
            Thermometer thermometer = new ThermometerController().find(nds);
            if (thermometer != null) {
                moduleclass = thermometer.getModuleObject();
                TemperatureController controller = new TemperatureController();
                while (line != null) {
                    Date date = DateParser.parseDate(line.split(",")[1].split(" ")[0]);
                    Date time = DateParser.parseTime(line.split(",")[1].split(" ")[1]);
                    float temperature = Float.parseFloat(line.split(",")[2]);
                    float soilMoisture = Float.parseFloat(line.split(",")[3]);
                    float dewPoint = Float.parseFloat(line.split(",")[4]);
                    controller.save(controller.nuevo(date, time, temperature, soilMoisture, dewPoint, moduleclass));
                    created++;
                    for (int i = 1; i < skip; i++) {
                        if ((line = br.readLine()) == null) {
                            break;
                        }
                    }
                    if (line != null) {
                        line = br.readLine();
                    }
                }
                br.close();
                fr.close();
                message = "Se crearon " + created + " nuevos registros.";
            } else {
                message = "¡Error! Termómetro no registrado.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Error inesperado.";
        }
    }

}
