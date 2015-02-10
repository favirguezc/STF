/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author fredy
 */
public class Validador {

    public static final int CONTRASEÑA_SIN_NUMERO = 3,

    /**
     *
     */
    CONTRASEÑA_MUY_CORTA = 1,

    /**
     *
     */
    CONTRASEÑA_MUY_LARGA = 2,

    /**
     *
     */
    OK = 0,

    /**
     *
     */
    CONTRASEÑA_VACIA = 4,

    /**
     *
     */
    CONTRASEÑA_SIN_MINUSCULAS = 5,

    /**
     *
     */
    CONTRASEÑA_SIN_MAYUSCULAS = 6;

    /**
     *
     * @param c
     * @param longitudmin
     * @param longitudmax
     * @param numero
     * @param letraM
     * @param letram
     * @return
     */
    public static int validarContraseña(String c, int longitudmin, int longitudmax, boolean numero, boolean letraM, boolean letram) {
        if (c == null || c.length() == 0) {
            return CONTRASEÑA_VACIA;
        }
        if (c.length() < longitudmin) {
            return CONTRASEÑA_MUY_CORTA;
        }
        if (c.length() > longitudmax) {
            return CONTRASEÑA_MUY_LARGA;
        }
        if (numero && !contieneNumero(c)) {
            return CONTRASEÑA_SIN_NUMERO;
        }
        if (letram && !contieneLetraMinuscula(c)) {
            return CONTRASEÑA_SIN_MINUSCULAS;
        }
        if (letraM && !contieneLetraMayuscula(c)) {
            return CONTRASEÑA_SIN_MAYUSCULAS;
        }
        return OK;
    }

    private static boolean contieneNumero(String c) {
        String buscar = "0123456789";
        for (int i = 0; i < buscar.length(); i++) {
            if (c.contains("" + buscar.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean contieneLetraMinuscula(String c) {
        String buscar = "abcdefghijklmnñopqrstuvwxyz";
        for (int i = 0; i < buscar.length(); i++) {
            if (c.contains("" + buscar.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean contieneLetraMayuscula(String c) {
        String buscar = "QWERTYUIOPASDFGHJKLÑMZNXBCV";
        for (int i = 0; i < buscar.length(); i++) {
            if (c.contains("" + buscar.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
