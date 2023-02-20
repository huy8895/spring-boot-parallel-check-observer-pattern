package com.example.springbootbasecrud.helper.excel.impl;

import com.example.springbootbasecrud.common.ReflectUtils;
import com.example.springbootbasecrud.helper.excel.CellDTO;
import com.example.springbootbasecrud.helper.excel.ExcelHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Slf4j
@Service
public class ExcelHelperImpl<E> implements ExcelHelper<E> {

    public static final int COLUMN_INDEX_ID         = 0;
    public static final int COLUMN_INDEX_TITLE      = 1;
    public static final int COLUMN_INDEX_PRICE      = 2;
    public static final int COLUMN_INDEX_QUANTITY   = 3;
    public static final int COLUMN_INDEX_TOTAL      = 4;
    private static CellStyle cellStyleFormatNumber = null;
    @Override
    public List<E> readFile(MultipartFile file) {
        return null;
    }

    @Override
    public byte[] writeFile(List<E> list) {
        return writeExcel(list, "xls");
    }

    public byte[] writeExcel(List<E> list, String excelFilePath)  {
        try(Workbook workbook = getWorkbook(excelFilePath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            // Create sheet
            Sheet sheet = workbook.createSheet("sheet_name"); // Create sheet with sheet name

            int rowIndex = 0;

            // Write header
            writeHeader(sheet, rowIndex);

            // Write data
            rowIndex++;
            for (E element : list) {
                // Create row
                Row row = sheet.createRow(rowIndex);
                // Write data on row
                writeBook(element, row);
                rowIndex++;
            }

            // Write footer
            writeFooter(sheet, rowIndex);

            // Auto resize column witdth
            int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
            autosizeColumn(sheet, numberOfColumn);
            workbook.write(bos);
            return bos.toByteArray();

        } catch (Exception e) {
            log.error("error export ", e);
            throw new RuntimeException("error export");
        }
    }

    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new SXSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");

        cell = row.createCell(COLUMN_INDEX_TITLE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Title");

        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Price");

        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quantity");

        cell = row.createCell(COLUMN_INDEX_TOTAL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Total money");
    }

    // Write data
    private void writeBook(E element, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        List<CellDTO> cellDTOS = ReflectUtils.getCellDTO(element);
        for (CellDTO cellDTO : cellDTOS) {
            row.createCell(cellDTO.getIndex())
                    .setCellValue(cellDTO.getValue());
        }
    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Write footer
    private static void writeFooter(Sheet sheet, int rowIndex) {
        // Create row
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
        cell.setCellFormula("SUM(E2:E6)");
    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
