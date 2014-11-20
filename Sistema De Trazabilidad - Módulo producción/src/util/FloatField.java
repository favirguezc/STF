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
    
    public FloatField() {
        super();
        this.setInputVerifier(new InputVerifier() {
            
            @Override
            public boolean verify(JComponent jc) {
                return !(getText() == null || getText().equals(""));
            }
        });
    }
    
    public float getFloat() {
        if (getText() == null || getText().equals("")) {
            return Float.parseFloat(getText());
        }
        return 0;
    }
    
    public void setFloat(float v) {
        setText(v + "");
    }
}
