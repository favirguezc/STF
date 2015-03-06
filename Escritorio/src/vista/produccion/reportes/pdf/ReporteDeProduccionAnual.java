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
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.produccion.recoleccion.RecoleccionControlador;
import vista.produccion.reportes.ReporteAnual;
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
import modelo.produccion.recoleccion.Recoleccion;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import modelo.util.DateFormatter;
import modelo.util.DateTools;
import vista.produccion.graficas.RecoleccionAnualPorMesIF;
import vista.produccion.graficas.RecoleccionAnualPorSemanaIF;

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
        Document archivo = new Document(PageSize.A4);
        PdfWriter writer;
        try {
            writer = PdfWriter.getInstance(archivo, new FileOutputStream(nombreDelArchivo));
            archivo.open();
            archivo.add(encabezado());
            archivo.add(Chunk.NEWLINE);
            archivo.add(grafico1());
            archivo.add(Chunk.NEWLINE);
            archivo.add(grafico2());
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

    private Element grafico1() {
        String path = "temp.jpeg";
        float width = PageSize.A4.getWidth() * 5 / 6;
        float height = PageSize.A4.getHeight() / 3;
        JFreeChart grafica = null;
        if (tipo == ReporteAnual.POR_MES) {
            grafica = RecoleccionAnualPorMesIF.crearGrafica(año);
        } else if (tipo == ReporteAnual.POR_SEMANA) {
            grafica = RecoleccionAnualPorSemanaIF.crearGrafica(año);
            height = PageSize.A4.getHeight() * 3 / 4;
        }
        if (grafica != null) {
            try {
                ChartUtilities.saveChartAsJPEG(new File(path), grafica, (int) width, (int) height);
            } catch (IOException ex) {
                Logger.getLogger(ReporteDeProduccionAnual.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Image imagen = Image.getInstance(path);
                return imagen;
            } catch (Exception ex) {
                Logger.getLogger(ReporteDeProduccionAnual.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new Chunk("Gráfica 1 no disponible");
    }

    private Element grafico2() {
        String path = "temp.jpeg";
        float width = PageSize.A4.getWidth() * 5 / 6;
        float height = PageSize.A4.getHeight() / 3;
        JFreeChart grafica = null;
        if (tipo == ReporteAnual.POR_MES) {
            grafica = RecoleccionAnualPorMesIF.crearGraficaDeTotales(año);
        } else if (tipo == ReporteAnual.POR_SEMANA) {
            grafica = RecoleccionAnualPorSemanaIF.crearGraficaDeTotales(año);
        }
        if (grafica != null) {
            try {
                ChartUtilities.saveChartAsJPEG(new File(path), grafica, (int) width, (int) height);
            } catch (IOException ex) {
                Logger.getLogger(ReporteDeProduccionAnual.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Image imagen = Image.getInstance(path);
                return imagen;
            } catch (Exception ex) {
                Logger.getLogger(ReporteDeProduccionAnual.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new Chunk("Gráfica 2 no disponible");
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
        float pesadaPeriodo, total = 0;
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
            pesadaPeriodo = r.getPesadaGramos();
            total += pesadaPeriodo;
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
            float[] sumas = {pesadaPeriodo};
            //Agregar total recoleccion de cada tipo de fresa
            for (float f : sumas) {
                celda = new PdfPCell(new Phrase(new DecimalFormat("0.##").format(f), fuenteNormal));
                if (f == 0) {
                    celda.setBackgroundColor(BaseColor.RED);
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
