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
import interfacesGraficas.reportes.ReporteAnual;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import modelo.produccion.Recoleccion;
import util.DateFormatter;
import util.DateTools;

/**
 *
 * @author fredy
 */
public class ReporteDeProduccionAnual {

    private int año;
    private int tipo;
    private String nombreDelArchivo;

    public ReporteDeProduccionAnual(int año, int tipo, String nombreDelArchivo) {
        this.año = año;
        this.tipo = tipo;
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
        float extraPeriodo,
                extraTotal = 0,
                primeraPeriodo,
                primeraTotal = 0,
                segundaPeriodo,
                segundaTotal = 0,
                terceraPeriodo,
                terceraTotal = 0,
                cuartaPeriodo,
                cuartaTotal = 0,
                dañadaPeriodo,
                dañadaTotal = 0,
                total = 0,
                totalmes;
        int columnas = 12;
        if (tipo == ReporteAnual.POR_DIA) {
            columnas = DateTools.getDiasDelAño(año);
        } else if (tipo == ReporteAnual.POR_SEMANA) {
            columnas = 53;
        }
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(new Date(año - 1900, 0, 1));
        for (int i = 0; i < columnas; i++) {
            //Sumar recoleccion de cada tipo de fresa
            Recoleccion r = null;
            if (tipo == ReporteAnual.POR_MES) {
                r = new RecoleccionControlador().sumarRegistros(null, null, DateTools.getPrimerDiaDelMes(i, año), DateTools.getUltimoDiaDelMes(i, año));
            } else if (tipo == ReporteAnual.POR_SEMANA) {
                r = new RecoleccionControlador().sumarRegistros(null, null, DateTools.getPrimerDiaDeLaSemana(c.getTime()), DateTools.getUltimoDiaDeLaSemana(c.getTime()));
            } else {
                r = new RecoleccionControlador().sumarRegistros(null, null, c.getTime(), null);
            }
            //Se dividen por 500 para convertirlos a libras
            extraPeriodo = r.getExtraGramos() / 500;
            extraTotal += extraPeriodo;
            primeraPeriodo = r.getPrimeraGramos() / 500;
            primeraTotal += primeraPeriodo;
            segundaPeriodo = r.getSegundaGramos() / 500;
            segundaTotal += segundaPeriodo;
            terceraPeriodo = r.getTerceraGramos() / 500;
            terceraTotal += terceraPeriodo;
            cuartaPeriodo = r.getCuartaGramos() / 500;
            cuartaTotal += cuartaPeriodo;
            dañadaPeriodo = r.getDanadaGramos() / 500;
            dañadaTotal += dañadaPeriodo;
            totalmes = r.getTotal() / 500;
            total += totalmes;
            String contador = "";
            if (tipo == ReporteAnual.POR_MES) {
                contador = DateTools.getMes(i);
            } else if (tipo == ReporteAnual.POR_SEMANA) {
                contador = DateTools.getSemanaCorta(c.getTime());
            } else {
                contador = DateFormatter.formatDateShort(c.getTime());
            }
            celda = new PdfPCell(new Phrase(contador, fuenteNormal));
            celda.setRowspan(2);
            celda.setColspan(2);
            tabla.addCell(celda);
            float[] sumas = {extraPeriodo, primeraPeriodo, segundaPeriodo, terceraPeriodo, cuartaPeriodo, dañadaPeriodo, totalmes};
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
            if (tipo == ReporteAnual.POR_SEMANA) {
                c.add(Calendar.DAY_OF_MONTH, 7);
            } else if (tipo == ReporteAnual.POR_DIA) {
                c.add(Calendar.DAY_OF_MONTH, 1);
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