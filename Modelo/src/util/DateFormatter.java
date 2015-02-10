/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author fredy
 */
public class DateFormatter {

    private static SimpleDateFormat dateFormatExtraShort = new SimpleDateFormat("MMM dd", new Locale("es", "CO"));
    private static SimpleDateFormat dateFormatShort = new SimpleDateFormat("MMM dd yyyy", new Locale("es", "CO"));
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    /**
     *
     * @param d
     * @return
     */
    public static String formatDate(Date d) {
        return dateFormatShort.format(d);
    }

    /**
     *
     * @param d
     * @return
     */
    public static String formatTime(Date d) {
        return timeFormat.format(d);
    }

    /**
     *
     * @param d
     * @return
     */
    public static String formatDateLong(Date d) {
        return dateFormat.format(d);
    }

    /**
     *
     * @param d
     * @return
     */
    public static String formatDateShort(Date d) {
        return dateFormatExtraShort.format(d);
    }
}
