/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.archivos;

import controlador.variablesClimaticas.TemperaturaControlador;
import controlador.variablesClimaticas.TermometroControlador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import modelo.administracion.Modulo;
import modelo.variablesClimaticas.Termometro;
import vista.archivos.util.DateParser;

/**
 *
 * @author fredy
 */
public class LectorDeRegistrosTemperatura {

    private static final int saltos = 15;

    public static int leer(File archivo) {

        int guardados = 0;
        FileReader fr = null;
        BufferedReader br = null;
        Modulo modulo = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            linea = br.readLine();
            linea = br.readLine();
            long nds = Long.parseLong(linea.split(",")[5]);
            try {
                Termometro buscar = new TermometroControlador().buscar(nds);
                modulo = buscar.getModulo();
            } catch (Exception e) {
                return -1;
            }
            TemperaturaControlador controlador = new TemperaturaControlador();
            linea = br.readLine();
            while (linea != null) {
                Date fecha = DateParser.parseFecha(linea.split(",")[1].split(" ")[0]);
                Date hora = DateParser.parseHora(linea.split(",")[1].split(" ")[1]);
                float temperatura = Float.parseFloat(linea.split(",")[2]);
                float humedad = Float.parseFloat(linea.split(",")[3]);
                float puntoDeRocio = Float.parseFloat(linea.split(",")[4]);
                controlador.guardar(controlador.nuevo(fecha, hora, temperatura, humedad, puntoDeRocio, modulo));
                guardados++;
                for (int i = 1; i < saltos; i++) {
                    if ((linea = br.readLine()) == null) {
                        break;
                    }
                }
                if (linea != null) {
                    linea = br.readLine();
                }
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return guardados;
    }

}
