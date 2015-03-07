/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author fredy
 */
public class DateTools {

    /**
     *
     * @param mes
     * @param año
     * @return
     */
    public static Date getFirstDayOfMonth(int mes, int año) {
        return getDate(año, mes, 1);
    }

    /**
     *
     * @param fecha
     * @return
     */
    public static String getWeek(Date fecha) {
        return DateFormatter.formatDateShort(getFirstDayOfWeek(fecha)) + " a " + DateFormatter.formatDate(getLastDayOfWeek(fecha));
    }

    /**
     *
     * @param fecha
     * @return
     */
    public static String getWeek_Short(Date fecha) {
        return DateFormatter.formatDateShort(getFirstDayOfWeek(fecha)) + " a " + DateFormatter.formatDateShort(getLastDayOfWeek(fecha));
    }

    /**
     *
     * @param fecha
     * @return
     */
    public static int getWeekInYear(Date fecha) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     *
     * @param fecha
     * @return
     */
    public static Date getFirstDayOfWeek(Date fecha) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DAY_OF_MONTH, -c.get(Calendar.DAY_OF_WEEK) + 2);
        return c.getTime();
    }

    /**
     *
     * @param fecha
     * @return
     */
    public static Date getLastDayOfWeek(Date fecha) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DAY_OF_MONTH, -c.get(Calendar.DAY_OF_WEEK) + 8);
        return c.getTime();
    }

    /**
     *
     * @param mes
     * @param año
     * @return
     */
    public static Date getLastDayOfMonth(int mes, int año) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(getFirstDayOfMonth(mes, año));
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    /**
     *
     * @param dia
     * @return
     */
    public static String getDayOfWeek(int dia) {
        switch (dia) {
            case 1: {
                return "Lunes";
            }
            case 2: {
                return "Martes";
            }
            case 3: {
                return "Miércoles";
            }
            case 4: {
                return "Jueves";
            }
            case 5: {
                return "Viernes";
            }
            case 6: {
                return "Sábado";
            }
            case 7: {
                return "Domingo";
            }
            default: {
                return "Error";
            }
        }
    }

    /**
     *
     * @param mes
     * @return
     */
    public static int getMonth(String mes) {
        mes = mes.toLowerCase();
        if (mes.equals("enero")) {
            return 0;
        }
        if (mes.equals("febrero")) {
            return 1;
        }
        if (mes.equals("marzo")) {
            return 2;
        }
        if (mes.equals("abril")) {
            return 3;
        }
        if (mes.equals("mayo")) {
            return 4;
        }
        if (mes.equals("junio")) {
            return 5;
        }
        if (mes.equals("julio")) {
            return 6;
        }
        if (mes.equals("agosto")) {
            return 7;
        }
        if (mes.equals("septiembre")) {
            return 8;
        }
        if (mes.equals("octubre")) {
            return 9;
        }
        if (mes.equals("noviembre")) {
            return 10;
        }
        if (mes.equals("diciembre")) {
            return 11;
        }
        return -1;
    }

    /**
     *
     * @param mes
     * @return
     */
    public static String getMonth(int mes) {
        switch (mes) {
            case 0: {
                return "Enero";
            }
            case 1: {
                return "Febrero";
            }
            case 2: {
                return "Marzo";
            }
            case 3: {
                return "Abril";
            }
            case 4: {
                return "Mayo";
            }
            case 5: {
                return "Junio";
            }
            case 6: {
                return "Julio";
            }
            case 7: {
                return "Agosto";
            }
            case 8: {
                return "Septiembre";
            }
            case 9: {
                return "Octubre";
            }
            case 10: {
                return "Noviembre";
            }
            case 11: {
                return "Diciembre";
            }
            default: {
                return "Error";
            }
        }
    }

    /**
     *
     * @param año
     * @return
     */
    public static int getDaysInYear(int año) {
        Calendar instance = GregorianCalendar.getInstance();
        instance.set(año, 11, 31);
        return instance.get(Calendar.DAY_OF_YEAR);
    }

    /**
     *
     * @param año
     * @return
     */
    public static int getWeeksInYear(int año) {
        Calendar instance = GregorianCalendar.getInstance();
        instance.set(año, 11, 31);
        return instance.get(Calendar.WEEK_OF_YEAR);
    }

    public static Date getDate(int año, int mes, int dia) {
        Calendar instance = GregorianCalendar.getInstance();
        instance.set(año, mes, dia);
        return instance.getTime();
    }

    public static Date getDate(int año, int mes, int dia, int hora, int minutos) {
        Calendar instance = GregorianCalendar.getInstance();
        instance.set(año, mes, dia, hora, minutos);
        return instance.getTime();
    }

    public static Date getDate(int año, int mes, int dia, int hora, int minutos, int segundos) {
        Calendar instance = GregorianCalendar.getInstance();
        instance.set(año, mes, dia, hora, minutos, segundos);
        return instance.getTime();
    }

    public static Date getDate() {
        Calendar instance = GregorianCalendar.getInstance();
        return instance.getTime();
    }

    public static Date getHour(Date dateCellValue) {
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTime(dateCellValue);
        return getDate(0, 0, 0, instance.get(Calendar.HOUR_OF_DAY), instance.get(Calendar.MINUTE), instance.get(Calendar.SECOND));
    }

    public static int getMonth() {
        Calendar instance = GregorianCalendar.getInstance();
        return instance.get(Calendar.MONTH);
    }

    public static int getYear() {
        Calendar instance = GregorianCalendar.getInstance();
        return instance.get(Calendar.YEAR);
    }
}
