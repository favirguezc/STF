/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fredy
 */
public class Font {

    public static com.lowagie.text.Font getFont(int size) {
        try {
            return new com.lowagie.text.Font(BaseFont.createFont("C://Windows//Fonts//calibri.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), size);
        } catch (DocumentException ex) {
            Logger.getLogger(Font.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Font.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new com.lowagie.text.Font(size);
    }

}
