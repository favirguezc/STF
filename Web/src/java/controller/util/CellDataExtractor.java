/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import java.util.Date;
import model.util.DateTools;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 *
 * @author fredy
 */
public class CellDataExtractor {

    public static String readString(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if ((int) cell.getNumericCellValue() < cell.getNumericCellValue()) {
                    return "" + cell.getNumericCellValue();
                } else {
                    return "" + (int) cell.getNumericCellValue();
                }
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BLANK:
                return "";
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() + "";
            case Cell.CELL_TYPE_ERROR:
                return "Error";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    public static Date parseDate(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        }
        return null;
    }

    public static Date parseTime(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return DateTools.getHour(cell.getDateCellValue());
        }
        return null;
    }

    public static double parseNumber(Cell cell) {
        if (cell.getCellType() != Cell.CELL_TYPE_NUMERIC) {
            return 0;
        }
        if (DateUtil.isCellDateFormatted(cell)) {
            return 0;
        }
        return cell.getNumericCellValue();
    }

    public static Long parseLong(Cell cell) {
        String stringCellValue = readString(cell);
        if (stringCellValue == null || stringCellValue.equals("")) {
            return 0l;
        }
        if (stringCellValue.matches("(\\d+)")) {
            return Long.parseLong(stringCellValue);
        }
        return 0l;
    }
}
