/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 *
 * @author fredy
 */
public class FloatField extends JTextField {

    @Override
    protected Document createDefaultModel() {
        return new FloatTextDocument();
    }

    /**
     *
     */
    public FloatField() {
        super();
    }

    /**
     *
     * @return
     */
    public Float getFloat() {
        if (getText() == null || getText().equals("")) {
            return (float) 0;
        }
        return Float.parseFloat(getText());
    }

    /**
     *
     * @param v
     */
    public void setFloat(Float v) {
        setText(v + "");
    }
}
