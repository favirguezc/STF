/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

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
import java.util.Date;
import modelo.produccion.Recoleccion;
import util.DateFormatter;
import util.DateTools;

/**
 *
 * @author fredy
 */
public class ReporteDeProduccionAnualPorMes {

    private int año;
    private String nombreDelArchivo;

    public ReporteDeProduccionAnualPorMes(int año, String nombreDelArchivo) {
        this.año = año;
        this.nombreDelArchivo = nombreDelArchivo;
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
        Paragraph encabezado = new Paragraph("REPORTE ANUAL DE RECOLECCIÓN", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
        Paragraph paragraph = new Paragraph("AÑO " + año, new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        encabezado.add(paragraph);
        encabezado.setAlignment(Element.ALIGN_CENTER);
        return encabezado;
    }

    private Element tabla() {
        PdfPTable tabla = new PdfPTable(9);
        PdfPCell celda;
        Font fuenteBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
        //Agregar encabezados de la tabla
        celda = new PdfPCell(new Phrase("", fuenteBold));
        celda.setRowspan(2);
        celda.setColspan(2);
        tabla.addCell(celda);
        String[] encabezados = {"Extra", "Primera", "Segunda", "Tercera", "Cuarta", "Dañada", "Total"};
        for (int i = 0; i < encabezados.length; i++) {
            celda = new PdfPCell(new Phrase(encabezados[i], fuenteBold));
            celda.setRowspan(2);
            tabla.addCell(celda);
        }
        float extraMes,
                extraTotal = 0,
                primeraMes,
                primeraTotal = 0,
                segundaMes,
                segundaTotal = 0,
                terceraMes,
                terceraTotal = 0,
                cuartaMes,
                cuartaTotal = 0,
                dañadaMes,
                dañadaTotal = 0,
                total = 0,
                totalmes;
        //Para cada mes se agrega una columna
        for (int i = 0; i < 12; i++) {
            //Sumar recoleccion de cada tipo de fresa
            Recoleccion r = new RecoleccionControlador().sumarRegistros(null, null, DateTools.getPrimerDia(i, año), DateTools.getUltimoDia(i, año));
            extraMes = r.getExtra() / 500;
            extraTotal += extraMes;
            primeraMes = r.getPrimera() / 500;
            primeraTotal += primeraMes;
            segundaMes = r.getSegunda() / 500;
            segundaTotal += segundaMes;
            terceraMes = r.getTercera() / 500;
            terceraTotal += terceraMes;
            cuartaMes = r.getCuarta() / 500;
            cuartaTotal += cuartaMes;
            dañadaMes = r.getDanada() / 500;
            dañadaTotal += dañadaMes;
            totalmes = r.getTotal() / 500;
            total += totalmes;

            celda = new PdfPCell(new Phrase(DateTools.getMes(i), fuenteNormal));
            celda.setRowspan(2);
            celda.setColspan(2);
            tabla.addCell(celda);
            float[] sumas = {extraMes, primeraMes, segundaMes, terceraMes, cuartaMes, dañadaMes, totalmes};
            //Agregar total recoleccion de cada tipo de fresa
            for (float f : sumas) {
                celda = new PdfPCell(new Phrase(new DecimalFormat("0.##").format(f), fuenteNormal));
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
        celda.setColspan(2);
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
