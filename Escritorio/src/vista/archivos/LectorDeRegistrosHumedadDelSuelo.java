/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.archivos;

import controlador.produccion.variablesClimaticas.HumedadDelSueloControlador;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vista.archivos.util.CellDataExtractor;

/**
 *
 * @author fredy
 */
public class LectorDeRegistrosHumedadDelSuelo {

    public static int leer(File archivo) {
        try {
            FileInputStream file = new FileInputStream(archivo);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            rowIterator.next();
            HumedadDelSueloControlador controlador = new HumedadDelSueloControlador();
            int guardados = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Date fecha = CellDataExtractor.leerFecha(row.getCell(0));
                double celda15 = CellDataExtractor.leerNumero(row.getCell(1));
                double celda30 = CellDataExtractor.leerNumero(row.getCell(2));
                Date hora = CellDataExtractor.leerHora(row.getCell(3));
                if (fecha != null && hora != null) {
                    try {
                        controlador.guardar(controlador.nuevo(fecha, (float) celda15, (float) celda30, hora));
                        guardados++;
                    } catch (Exception e) {
                    }
                } else {
                    System.out.println("error: " + fecha + "\t" + celda15 + "\t" + celda30 + "\t" + hora);
                }
            }
            file.close();
            return guardados;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
