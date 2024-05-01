package api.utilities;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

    private static FileInputStream inputStream;

    public static String getCellValue(String fileName, String sheetName, int rowNo, int cellNo) {
        try (Workbook workbook = openWorkbook(fileName)) {
            if (workbook != null) {
                Sheet excelSheet = workbook.getSheet(sheetName);
                if (excelSheet != null) {
                    Row row = excelSheet.getRow(rowNo);
                    if (row != null) {
                        Cell cell = row.getCell(cellNo);
                        if (cell != null) {
                            return getCellStringValue(cell);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getRowCount(String fileName, String sheetName) {
        try (Workbook workbook = openWorkbook(fileName)) {
            if (workbook != null) {
                Sheet excelSheet = workbook.getSheet(sheetName);
                if (excelSheet != null) {
                    return excelSheet.getLastRowNum() + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getColCount(String fileName, String sheetName) {
        try (Workbook workbook = openWorkbook(fileName)) {
            if (workbook != null) {
                Sheet excelSheet = workbook.getSheet(sheetName);
                if (excelSheet != null) {
                    Row row = excelSheet.getRow(0);
                    if (row != null) {
                        return row.getLastCellNum();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static Workbook openWorkbook(String fileName) throws IOException {
        if (fileName != null) {
            inputStream = new FileInputStream(fileName);
            return new XSSFWorkbook(inputStream);
        }
        return null;
    }

    private static String getCellStringValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                // Handle formula cells if needed
            case ERROR:
                // Handle error cells if needed
            case BLANK:
                // Handle blank cells if needed
            default:
                return ""; // Return empty string for other cell types
        }
    }
}
