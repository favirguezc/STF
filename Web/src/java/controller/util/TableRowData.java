/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

/**
 *
 * @author fredy
 */
public class TableRowData {

    private String value1;
    private Object value2;
    private Object value3;
    private Object value4;
    private Object value5;
    private Object value6;
    private Object value7;
    private Object value8;
    private Object value9;
    private Object value10;

    public TableRowData() {
    }

    public TableRowData(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public Object getValue2() {
        return value2;
    }

    public void setValue2(Object value2) {
        this.value2 = value2;
    }

    public Object getValue3() {
        return value3;
    }

    public void setValue3(Object value3) {
        this.value3 = value3;
    }

    public Object getValue4() {
        return value4;
    }

    public void setValue4(Object value4) {
        this.value4 = value4;
    }

    public Object getValue5() {
        return value5;
    }

    public void setValue5(Object value5) {
        this.value5 = value5;
    }

    public Object getValue6() {
        return value6;
    }

    public void setValue6(Object value6) {
        this.value6 = value6;
    }

    public Object getValue7() {
        return value7;
    }

    public void setValue7(Object value7) {
        this.value7 = value7;
    }

    public Object getValue8() {
        return value8;
    }

    public void setValue8(Object value8) {
        this.value8 = value8;
    }

    public Object getValue9() {
        return value9;
    }

    public void setValue9(Object value9) {
        this.value9 = value9;
    }

    public Object getValue10() {
        return value10;
    }

    public void setValue10(Object value10) {
        this.value10 = value10;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.value1 != null ? this.value1.hashCode() : 0);
        hash = 71 * hash + (this.value2 != null ? this.value2.hashCode() : 0);
        hash = 71 * hash + (this.value3 != null ? this.value3.hashCode() : 0);
        hash = 71 * hash + (this.value4 != null ? this.value4.hashCode() : 0);
        hash = 71 * hash + (this.value5 != null ? this.value5.hashCode() : 0);
        hash = 71 * hash + (this.value6 != null ? this.value6.hashCode() : 0);
        hash = 71 * hash + (this.value7 != null ? this.value7.hashCode() : 0);
        hash = 71 * hash + (this.value8 != null ? this.value8.hashCode() : 0);
        hash = 71 * hash + (this.value9 != null ? this.value9.hashCode() : 0);
        hash = 71 * hash + (this.value10 != null ? this.value10.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TableRowData other = (TableRowData) obj;
        if ((this.value1 == null) ? (other.value1 != null) : !this.value1.equals(other.value1)) {
            return false;
        }
        if (this.value2 != other.value2 && (this.value2 == null || !this.value2.equals(other.value2))) {
            return false;
        }
        if (this.value3 != other.value3 && (this.value3 == null || !this.value3.equals(other.value3))) {
            return false;
        }
        if (this.value4 != other.value4 && (this.value4 == null || !this.value4.equals(other.value4))) {
            return false;
        }
        if (this.value5 != other.value5 && (this.value5 == null || !this.value5.equals(other.value5))) {
            return false;
        }
        if (this.value6 != other.value6 && (this.value6 == null || !this.value6.equals(other.value6))) {
            return false;
        }
        if (this.value7 != other.value7 && (this.value7 == null || !this.value7.equals(other.value7))) {
            return false;
        }
        if (this.value8 != other.value8 && (this.value8 == null || !this.value8.equals(other.value8))) {
            return false;
        }
        if (this.value9 != other.value9 && (this.value9 == null || !this.value9.equals(other.value9))) {
            return false;
        }
        if (this.value10 != other.value10 && (this.value10 == null || !this.value10.equals(other.value10))) {
            return false;
        }
        return true;
    }
}
