/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.produccion.reportes.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.produccion.cosecha.RecoleccionControlador;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.administracion.Persona;
import modelo.produccion.cosecha.Recoleccion;
import modelo.util.DateFormatter;
import modelo.util.DateTools;

/**
 *
 * @author fredy
 */
public class ReporteDeProduccionMensual {

    private final int año;
    private final int mes;
    private final String nombreDelArchivo;
    private final Persona recolector;
    private final Modulo modulo;

    public ReporteDeProduccionMensual(int año, int mes, String nombreDelArchivo, Persona recolector, Modulo modulo) {
        this.año = año;
        this.mes = mes;
        this.nombreDelArchivo = nombreDelArchivo;
        this.recolector = recolector;
        this.modulo = modulo;
    }

    public void crearReporte() {
        Document archivo = new Document();
        try {
            PdfWriter.getInstance(archivo, new FileOutputStream(nombreDelArchivo));
            archivo.open();
            archivo.add(encabezado());
            archivo.add(Chunk.NEWLINE);
            archivo.add(tabla());
            archivo.add(Chunk.NEWLINE);
            archivo.add(pieDePagina());
            archivo.close();
            abrirPDF();
        } catch (FileNotFoundException | DocumentException ex) {
            ex.printStackTrace();
        }
    }

    private Element encabezado() {
        Paragraph encabezado = new Paragraph("REPORTE MENSUAL DE RECOLECCIÓN", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
        Paragraph paragraph = new Paragraph(DateTools.getMonth(mes).toUpperCase() + " DE " + año, new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        encabezado.add(paragraph);
        if (recolector != null) {
            try {
                paragraph = new Paragraph("RECOLECTOR: " + recolector.toString().toUpperCase(), new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                encabezado.add(paragraph);
            } catch (Exception ex) {
                Logger.getLogger(ReporteDeProduccionMensual.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (modulo != null) {
            try {
                paragraph = new Paragraph("MÓDULO: " + modulo.toString().toUpperCase(), new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                encabezado.add(paragraph);
            } catch (Exception ex) {
                Logger.getLogger(ReporteDeProduccionMensual.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        encabezado.setAlignment(Element.ALIGN_CENTER);
        return encabezado;
    }

    private Element tabla() {
        PdfPTable tabla = new PdfPTable(2);
        PdfPCell celda;
        Font fuenteBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
        //Agregar encabezados de la tabla
        celda = new PdfPCell(new Phrase("", fuenteBold));
        celda.setRowspan(2);
        tabla.addCell(celda);
        String[] encabezados = {"Pesada"};
        for (int i = 0; i < encabezados.length; i++) {
            celda = new PdfPCell(new Phrase(encabezados[i], fuenteBold));
            celda.setRowspan(2);
            tabla.addCell(celda);
        }
        int dias = new GregorianCalendar(año, mes, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
        float pesadaDia,
                total = 0;
        //Para cada mes se agrega una columna
        for (int i = 1; i <= dias; i++) {
            //Sumar recoleccion de cada tipo de fresa
            Recoleccion r = new RecoleccionControlador().sumarRegistros(recolector, modulo, DateTools.getDate(año, mes, i), null);
            pesadaDia = r.getPesadaGramos() / 500;
            total += pesadaDia;

            celda = new PdfPCell(new Phrase("" + i, fuenteNormal));
            celda.setRowspan(2);
            tabla.addCell(celda);
            float[] sumas = {pesadaDia};
            //Agregar total recoleccion de cada tipo de fresa
            for (float f : sumas) {
                celda = new PdfPCell(new Phrase(new DecimalFormat("0.##").format(f), fuenteNormal));
                if (f == 0) {
                    celda.setBackgroundColor(BaseColor.RED);
                }
                tabla.addCell(celda);
            }
        }//Agregar fila de totales
        celda = new PdfPCell(new Phrase("Total", fuenteNormal));
        tabla.addCell(celda);
        float[] sumas = {total};
        for (float f : sumas) {
            celda = new PdfPCell(new Phrase(new DecimalFormat("0.##").format(f), fuenteNormal));
            tabla.addCell(celda);
        }
        return tabla;
    }

    private Element pieDePagina() {
        Paragraph pie = new Paragraph("*Valores en libras");
        pie.add(new Paragraph("Este reporte fue generado automáticamente"));
        pie.add(new Paragraph("Fecha de creación: " + DateFormatter.formatDateLong(DateTools.getDate()) + " a las " + DateFormatter.formatTime(DateTools.getDate())));
        return pie;
    }

    private void abrirPDF() {
        try {
            File ruta = new File(nombreDelArchivo);
            Desktop.getDesktop().open(ruta);
        } catch (IOException ex) {
        }
    }
}
