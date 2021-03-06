/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.produccion.cargarRegistros;

import controlador.produccion.variablesClimaticas.LluviaControlador;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import modelo.util.DateTools;
import vista.produccion.cargarRegistros.util.CellDataExtractor;

/**
 *
 * @author fredy
 */
public class LectorDeRegistrosLluvia {

    public static int leer(File archivo) {
        int guardados = 0;
        try {
            FileInputStream file = null;
            file = new FileInputStream(archivo);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Iterator<XSSFSheet> sheetIterator = workbook.iterator();
            LluviaControlador controlador = new LluviaControlador();
            while (sheetIterator.hasNext()) {
                XSSFSheet sheet = sheetIterator.next();
                int año = Integer.parseInt(sheet.getSheetName());
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                rowIterator.next();
                rowIterator.next();
                rowIterator.next();

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    if (row.getCell(0) != null) {
                        int mes = DateTools.getMonth(row.getCell(0).getStringCellValue());
                        int diasDelMes = new GregorianCalendar(año, mes, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
                        for (int dia = 1; dia <= diasDelMes; dia++) {
                            Cell celda = row.getCell(dia);
                            double valor = CellDataExtractor.leerNumero(celda);
//                            System.out.println("Año: " + año + " Mes: " + mes + " Dia: " + dia + " Valor: " + CellDataExtractor.leerNumero(celda));
                            if (valor > 0) {
                                controlador.guardar(controlador.nuevo(DateTools.getDate(año, mes, dia), (float) valor));
                                guardados++;
                            }
                        }
                    }
                }
            }
            file.close();
        } catch (Exception ex) {
            Logger.getLogger(LectorDeRegistrosLluvia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardados;
    }

//    public static void main(String[] args) {
//        JFileChooser fileChooser = new JFileChooser();
//        for (FileFilter filter : fileChooser.getChoosableFileFilters()) {
//            fileChooser.removeChoosableFileFilter(filter);
//        }
//        fileChooser.addChoosableFileFilter(new FileTypeFilter(".xlsx", "Libro de Excel"));
//        int returnVal = fileChooser.showOpenDialog(null);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            int g = leer(fileChooser.getSelectedFile());
//            JOptionPane.showMessageDialog(null, g + " registros guardados", "Carga de Datos Finalizada", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
}
