/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dto;

/**
 *
 * @author John Fredy
 */
public class ConceptDTO {

    private String name;
    private float[] valuesByWeek;

    public ConceptDTO(int size) {
        valuesByWeek = new float[size];
        for(float f : valuesByWeek){
            f = 0;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float[] getValuesByWeek() {
        return valuesByWeek;
    }

    public void setValuesByWeek(float[] valuesByWeek) {
        this.valuesByWeek = valuesByWeek;
    }

    public float getTotal(){
        float total = 0;
        for(float f : valuesByWeek){
            total += f;
        }
        return total;
    }
}
