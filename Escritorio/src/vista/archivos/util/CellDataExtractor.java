/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.archivos.util;

import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 *
 * @author fredy
 */
public class CellDataExtractor {

    public static Date leerFecha(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        }
        return null;
    }

    public static Date leerHora(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            Date hora = cell.getDateCellValue();
            return new Date(0, 0, 0, hora.getHours(), hora.getMinutes(), hora.getSeconds());
        }
        return null;
    }

    public static double leerNumero(Cell cell) {
        if (cell.getCellType() != Cell.CELL_TYPE_NUMERIC) {
            return Double.MIN_VALUE;
        }
        if (DateUtil.isCellDateFormatted(cell)) {
            return Double.MIN_VALUE;
        }
        return cell.getNumericCellValue();
    }
}
