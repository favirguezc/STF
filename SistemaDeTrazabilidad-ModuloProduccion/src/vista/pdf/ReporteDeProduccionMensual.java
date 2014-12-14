/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.pdf;

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
import controlador.produccion.RecoleccionControlador;
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
import modelo.administracion.Lote;
import modelo.administracion.Persona;
import modelo.produccion.Recoleccion;
import util.DateFormatter;
import util.DateTools;

/**
 *
 * @author fredy
 */
public class ReporteDeProduccionMensual {

    private int año;
    private int mes;
    private String nombreDelArchivo;
    private Persona recolector;
    private Lote lote;

    public ReporteDeProduccionMensual(int año, int mes, String nombreDelArchivo, Persona recolector, Lote lote) {
        this.año = año;
        this.mes = mes;
        this.nombreDelArchivo = nombreDelArchivo;
        this.recolector = recolector;
        this.lote = lote;
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
        Paragraph paragraph = new Paragraph(DateTools.getMes(mes).toUpperCase() + " DE " + año, new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
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
        if (lote != null) {
            try {
                paragraph = new Paragraph("LOTE: " + lote.toString().toUpperCase(), new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
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
        PdfPTable tabla = new PdfPTable(8);
        PdfPCell celda;
        Font fuenteBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
        //Agregar encabezados de la tabla
        celda = new PdfPCell(new Phrase("", fuenteBold));
        celda.setRowspan(2);
        tabla.addCell(celda);
        String[] encabezados = {"Extra", "Primera", "Segunda", "Tercera", "Cuarta", "Dañada", "Total"};
        for (int i = 0; i < encabezados.length; i++) {
            celda = new PdfPCell(new Phrase(encabezados[i], fuenteBold));
            celda.setRowspan(2);
            tabla.addCell(celda);
        }
        int dias = new GregorianCalendar(año, mes, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
        float extraDia,
                extraTotal = 0,
                primeraDia,
                primeraTotal = 0,
                segundaDia,
                segundaTotal = 0,
                terceraDia,
                terceraTotal = 0,
                cuartaDia,
                cuartaTotal = 0,
                dañadaDia,
                dañadaTotal = 0,
                total = 0,
                totalmes;
        //Para cada mes se agrega una columna
        for (int i = 1; i <= dias; i++) {
            //Sumar recoleccion de cada tipo de fresa
            Recoleccion r = new RecoleccionControlador().sumarRegistros(recolector, lote, new Date(año - 1900, mes, i), null);
            extraDia = r.getExtraGramos() / 500;
            extraTotal += extraDia;
            primeraDia = r.getPrimeraGramos() / 500;
            primeraTotal += primeraDia;
            segundaDia = r.getSegundaGramos() / 500;
            segundaTotal += segundaDia;
            terceraDia = r.getTerceraGramos() / 500;
            terceraTotal += terceraDia;
            cuartaDia = r.getCuartaGramos() / 500;
            cuartaTotal += cuartaDia;
            dañadaDia = r.getDanadaGramos() / 500;
            dañadaTotal += dañadaDia;
            totalmes = r.getTotalGramos() / 500;
            total += totalmes;

            celda = new PdfPCell(new Phrase("" + i, fuenteNormal));
            celda.setRowspan(2);
            tabla.addCell(celda);
            float[] sumas = {extraDia, primeraDia, segundaDia, terceraDia, cuartaDia, dañadaDia, totalmes};
            //Agregar total recoleccion de cada tipo de fresa
            for (float f : sumas) {
                celda = new PdfPCell(new Phrase(new DecimalFormat("0.##").format(f), fuenteNormal));
                if (f == 0) {
                    celda.setBackgroundColor(BaseColor.RED);
                }
                tabla.addCell(celda);
            }
            //Agregar porcentajes
            for (float f : sumas) {
                if (totalmes > 0) {
                    celda = new PdfPCell(new Phrase(new DecimalFormat("0.##").format(f * 100 / totalmes) + "%", fuenteNormal));
                } else {
                    celda = new PdfPCell(new Phrase("100%", fuenteNormal));
                }
                tabla.addCell(celda);
            }
        }//Agregar fila de totales
        celda = new PdfPCell(new Phrase("Total", fuenteNormal));
        tabla.addCell(celda);
        float[] sumas = {extraTotal, primeraTotal, segundaTotal, terceraTotal, cuartaTotal, dañadaTotal, total};
        for (float f : sumas) {
            celda = new PdfPCell(new Phrase(new DecimalFormat("0.##").format(f), fuenteNormal));
            tabla.addCell(celda);
        }
        return tabla;
    }

    private Element pieDePagina() {
        Paragraph pie = new Paragraph("*Valores en libras");
        pie.add(new Paragraph("Este reporte fue generado automáticamente"));
        pie.add(new Paragraph("Fecha de creación: " + DateFormatter.formatDateLong(new Date()) + " a las " + DateFormatter.formatTime(new Date())));
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
