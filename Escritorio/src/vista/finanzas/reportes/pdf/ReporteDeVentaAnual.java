/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.finanzas.reportes.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.finanzas.VentaControlador;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import modelo.finanzas.ventas.Venta;
import modelo.util.DateFormatter;
import modelo.util.DateTools;
import vista.produccion.reportes.ReporteAnual;

/**
 *
 * @author John Fredy
 */
public class ReporteDeVentaAnual {
    
    private int año;
    private int tipo;
    private String nombreDelArchivo;

    public ReporteDeVentaAnual(int año, int tipo, String nombreDelArchivo) {
        this.año = año;
        this.tipo = tipo;
        this.nombreDelArchivo = nombreDelArchivo;
    }
    
    public void crearReporte() {
        Document archivo = new Document(PageSize.A4.rotate());
        PdfWriter writer;
        try {
            writer = PdfWriter.getInstance(archivo, new FileOutputStream(nombreDelArchivo));
            archivo.open();
            archivo.add(encabezado());
            archivo.add(Chunk.NEWLINE);
//            archivo.add(grafico1());
//            archivo.add(Chunk.NEWLINE);
//            archivo.add(grafico2());
//            archivo.add(Chunk.NEWLINE);
            Paragraph cantEnca = new Paragraph("CANTIDAD DE FRESAS VENDIDAS POR TIPO\n", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));
            cantEnca.setAlignment(Element.ALIGN_CENTER);
            archivo.add(cantEnca);
            archivo.add(tablaCantidad());
            archivo.add(Chunk.NEWLINE);
            Paragraph precEnca = new Paragraph("INGRESO DE LAS VENTAS POR TIPO DE FRESA\n", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK));
            precEnca.setAlignment(Element.ALIGN_CENTER);
            archivo.add(precEnca);
            archivo.add(tablaPrecio());
            archivo.add(Chunk.NEWLINE);
            archivo.add(pieDePagina());
            archivo.close();
            abrirPDF();
        } catch (FileNotFoundException | DocumentException ex) {
            ex.printStackTrace();
        }
    }

    private Element encabezado() {
        Paragraph encabezado = new Paragraph("REPORTE ANUAL DE VENTAS", new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
        Paragraph paragraph = new Paragraph("AÑO " + año, new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        encabezado.add(paragraph);
        encabezado.setAlignment(Element.ALIGN_CENTER);
        return encabezado;
    }

    
    private Element tablaCantidad() {
        PdfPTable tabla = new PdfPTable(9);
        PdfPCell celda;
        Font fuenteBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
       //Agregar encabezados de la tabla
        celda = new PdfPCell(new Phrase("Cantidad", fuenteBold));
        celda.setRowspan(2);
        celda.setColspan(2);
        tabla.addCell(celda);
        String[] encabezados = {"`Extra", "Primera", "Segunda", "Tercera", "Cuarta", "Quinta", "Total"};
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
                quintaPeriodo,
                quintaTotal = 0,
                total = 0,
                totalmes;
        int columnas = 12;
        if (tipo == ReporteAnual.POR_DIA) {
            columnas = DateTools.getDaysInYear(año);
        } else if (tipo == ReporteAnual.POR_SEMANA) {
            columnas = 53;
        }
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(DateTools.getDate(año, 0, 1));
        for (int i = 0; i < columnas; i++) {
            //Sumar ventas de cada tipo de fresa
            Venta v = null;
            if (tipo == ReporteAnual.POR_MES) {
                v = new VentaControlador().sumarRegistros(null, DateTools.getFirstDayOfMonth(i, año), DateTools.getLastDayOfMonth(i, año));
            } else if (tipo == ReporteAnual.POR_SEMANA) {
                v = new VentaControlador().sumarRegistros(null, DateTools.getFirstDayOfWeek(c.getTime()), DateTools.getLastDayOfWeek(c.getTime()));
            } else {
                v = new VentaControlador().sumarRegistros(null, c.getTime(), null);
            }
            //Vienen en libras *
            extraPeriodo = v.getExtraGramos();
            extraTotal += extraPeriodo;
            primeraPeriodo = v.getPrimeraGramos();
            primeraTotal += primeraPeriodo;
            segundaPeriodo = v.getSegundaGramos();
            segundaTotal += segundaPeriodo;
            terceraPeriodo = v.getTerceraGramos();
            terceraTotal += terceraPeriodo;
            cuartaPeriodo = v.getCuartaGramos();
            cuartaTotal += cuartaPeriodo;
            quintaPeriodo = v.getQuintaGramos();
            quintaTotal += quintaPeriodo;
            totalmes = v.getCantidadTotal();
            total += totalmes;
            String contador = "";
            if (tipo == ReporteAnual.POR_MES) {
                contador = DateTools.getMonth(i);
            } else if (tipo == ReporteAnual.POR_SEMANA) {
                contador = DateTools.getWeek_Short(c.getTime());
            } else {
                contador = DateFormatter.formatDateShort(c.getTime());
            }
            celda = new PdfPCell(new Phrase(contador, fuenteNormal));
            celda.setRowspan(2);
            celda.setColspan(2);
            tabla.addCell(celda);
            float[] sumas = {extraPeriodo, primeraPeriodo, segundaPeriodo, terceraPeriodo, cuartaPeriodo, quintaPeriodo, totalmes};
            //Agregar total venta de cada tipo de fresa
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
        float[] sumas = {extraTotal, primeraTotal, segundaTotal, terceraTotal, cuartaTotal, quintaTotal, total};
        for (float f : sumas) {
            celda = new PdfPCell(new Phrase(new DecimalFormat("0.##").format(f), fuenteNormal));
            tabla.addCell(celda);
        }
        return tabla;
    }

    private Element tablaPrecio() {
        PdfPTable tabla = new PdfPTable(9);
        PdfPCell celda;
        Font fuenteBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
       //Agregar encabezados de la tabla
        celda = new PdfPCell(new Phrase("Ingreso", fuenteBold));
        celda.setRowspan(2);
        celda.setColspan(2);
        tabla.addCell(celda);
        String[] encabezados = {"`Extra", "Primera", "Segunda", "Tercera", "Cuarta", "Quinta", "Total"};
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
                quintaPeriodo,
                quintaTotal = 0,
                total = 0,
                totalmes;
        int columnas = 12;
        if (tipo == ReporteAnual.POR_DIA) {
            columnas = DateTools.getDaysInYear(año);
        } else if (tipo == ReporteAnual.POR_SEMANA) {
            columnas = 53;
        }
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(DateTools.getDate(año, 0, 1));
        for (int i = 0; i < columnas; i++) {
            //Sumar ventas de cada tipo de fresa
            Venta v = null;
            if (tipo == ReporteAnual.POR_MES) {
                v = new VentaControlador().sumarRegistros(null, DateTools.getFirstDayOfMonth(i, año), DateTools.getLastDayOfMonth(i, año));
            } else if (tipo == ReporteAnual.POR_SEMANA) {
                v = new VentaControlador().sumarRegistros(null, DateTools.getFirstDayOfWeek(c.getTime()), DateTools.getLastDayOfWeek(c.getTime()));
            } else {
                v = new VentaControlador().sumarRegistros(null, c.getTime(), null);
            }
            
            extraPeriodo = v.getExtraPrecioTotal();
            extraTotal += extraPeriodo;
            primeraPeriodo = v.getPrimeraPrecioTotal();
            primeraTotal += primeraPeriodo;
            segundaPeriodo = v.getSegundaPrecioTotal();
            segundaTotal += segundaPeriodo;
            terceraPeriodo = v.getTerceraPrecioTotal();
            terceraTotal += terceraPeriodo;
            cuartaPeriodo = v.getCuartaPrecioTotal();
            cuartaTotal += cuartaPeriodo;
            quintaPeriodo = v.getQuintaPrecioTotal();
            quintaTotal += quintaPeriodo;
            totalmes = v.getVentaTotal();
            total += totalmes;
            String contador = "";
            if (tipo == ReporteAnual.POR_MES) {
                contador = DateTools.getMonth(i);
            } else if (tipo == ReporteAnual.POR_SEMANA) {
                contador = DateTools.getWeek_Short(c.getTime());
            } else {
                contador = DateFormatter.formatDateShort(c.getTime());
            }
            celda = new PdfPCell(new Phrase(contador, fuenteNormal));
            celda.setRowspan(2);
            celda.setColspan(2);
            tabla.addCell(celda);
            float[] sumas = {extraPeriodo, primeraPeriodo, segundaPeriodo, terceraPeriodo, cuartaPeriodo, quintaPeriodo, totalmes};
            //Agregar total venta de cada tipo de fresa
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
        float[] sumas = {extraTotal, primeraTotal, segundaTotal, terceraTotal, cuartaTotal, quintaTotal, total};
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
