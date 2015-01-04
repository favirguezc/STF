/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author fredy
 */
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name = "tabIndex")
@SessionScoped
public class TabIndex {
    private int index = 0;
 
    public int getIndex() {
        return index;
    }
 
    public void setIndex(int index) {
        this.index = index;
    }
}
