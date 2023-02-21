package com.example.springbootbasecrud.helper.excel.impl;

import com.example.springbootbasecrud.helper.excel.CellDTO;
import com.example.springbootbasecrud.helper.excel.ExcelHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class ExcelHelperImpl implements ExcelHelper{
    private static CellStyle cellStyleFormatNumber = null;


    @Override
    public <E> List<E> readFile(MultipartFile file, Class<E> eClass) {
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        return this.readFile(file, filenameExtension, eClass);
    }

    private <E> List<E> readFile(MultipartFile file, String filenameExtension, Class<E> eClass) {
        try (Workbook workbook = getWorkbook(file.getInputStream(), filenameExtension)) {
            // Get sheet
            Sheet sheet = workbook.getSheetAt(0);
            List<E> eList = new LinkedList<>();
            // Get all rows
            for (Row nextRow : sheet) {
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }

                // Get all cells
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                // Read cells and set value for <E> object
                //Book book = new Book();
                while (cellIterator.hasNext()) {
                    //Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString()
                                                      .isEmpty()) {
                        continue;
                    }
                    // Set value for book object
                    int columnIndex = cell.getColumnIndex();
                    // TODO: 21/2/2023 get cell value from column index

                }
//                eList.add(book);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
    @Override
    public <E> byte[] writeFile(List<E> list, Class<E> eClass) {
        return writeExcel(list, "xls", eClass);
    }

    private <E> byte[] writeExcel(List<E> list, String excelFilePath, Class<E> eClass) {
        try (Workbook workbook = getWorkbook(excelFilePath);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            // Create sheet
            Sheet sheet = workbook.createSheet("sheet_name"); // Create sheet with sheet name

            int rowIndex = 0;

            // Write header
            this.writeHeader(sheet, rowIndex, eClass);

            // Write data
            rowIndex++;
            for (E element : list) {
                // Create row
                Row row = sheet.createRow(rowIndex);
                // Write data on row
                writeBook(element, row);
                rowIndex++;
            }

            // Auto resize column witdth
            int numberOfColumn = sheet.getRow(0)
                                      .getPhysicalNumberOfCells();
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

    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) {
        if (excelFilePath == null) {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        try {
            if (excelFilePath.endsWith("xlsx")) {
                return new SXSSFWorkbook(new XSSFWorkbook(inputStream));
            } else if (excelFilePath.endsWith("xls")) {
                return new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("The specified file is not Excel file");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
    }

    // Write header with format
    private <E> void writeHeader(Sheet sheet, int rowIndex, Class<E> eClass) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);
        List<CellDTO> cellDTOS = this.getCellHeader(eClass);
        // Create row
        Row row = sheet.createRow(rowIndex);

        for (CellDTO cellDTO : cellDTOS) {
            row.createCell(cellDTO.getIndex())
               .setCellValue(cellDTO.getFieldName());
        }
    }


    // Write data
    private <E> void writeBook(E element, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet()
                                   .getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        List<CellDTO> cellDTOS = getCellDTOS(element);
        for (CellDTO cellDTO : cellDTOS) {
            row.createCell(cellDTO.getIndex())
               .setCellValue(cellDTO.getValue());
        }
    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook()
                         .createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook()
                                   .createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
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
