/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.archivos;

import controlador.variablesClimaticas.HumedadDelSueloControlador;
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

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
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
                if (fecha != null && celda15 != Double.MIN_VALUE && celda30 != Double.MIN_VALUE && hora != null) {
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
