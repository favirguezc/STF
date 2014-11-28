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
        crearReporte();
    }
    
    private void crearReporte() {
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
        float extra, primera, segunda, tercera, cuarta, dañada, total = 0, totalmes;
        //Para cada mes se agrega una columna
        for (int i = 0; i < 12; i++) {
            extra = primera = segunda = tercera = cuarta = dañada = totalmes = 0;
            //Sumar recoleccion de cada tipo de fresa
            for (Recoleccion r : new RecoleccionControlador().leerLista(null, null, DateTools.getPrimerDia(i, año), DateTools.getUltimoDia(i, año))) {
                extra += r.getExtra() / 500;
                primera += r.getPrimera() / 500;
                segunda += r.getSegunda() / 500;
                tercera += r.getTercera() / 500;
                cuarta += r.getCuarta() / 500;
                dañada += r.getDanada() / 500;
            }
            totalmes = extra + primera + segunda + tercera + cuarta + dañada;
            total += totalmes;
            
            celda = new PdfPCell(new Phrase(DateTools.getMes(i), fuenteNormal));
            celda.setRowspan(2);
            celda.setColspan(2);
            tabla.addCell(celda);
            float[] sumas = {extra, primera, segunda, tercera, cuarta, dañada, totalmes};
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
