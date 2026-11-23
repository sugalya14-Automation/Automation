package com.orangehrm.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ExcelUtils {

    public static List<Map<String, String>> readExcel(
            String filePath, String sheetName) throws IOException {

        List<Map<String, String>> testData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName);
            }

            DataFormatter formatter = new DataFormatter();

            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                throw new IllegalArgumentException(
                        "Excel header row is missing");
            }

            int lastColumn = headerRow.getLastCellNum();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                Map<String, String> rowData =
                        new LinkedHashMap<>();

                boolean emptyRow = true;

                for (int j = 0; j < lastColumn; j++) {

                    String columnName = formatter.formatCellValue(
                            headerRow.getCell(j)).trim();

                    String cellValue = formatter.formatCellValue(
                            row.getCell(j)).trim();

                    if (!columnName.isEmpty()) {
                        rowData.put(columnName, cellValue);
                    }

                    if (!cellValue.isEmpty()) {
                        emptyRow = false;
                    }
                }

                if (!emptyRow) {
                    testData.add(rowData);
                }
            }
        }

        return testData;
    }
}
