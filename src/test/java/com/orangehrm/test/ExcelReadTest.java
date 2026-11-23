package com.orangehrm.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.orangehrm.utils.ExcelUtils;

public class ExcelReadTest {

    public static void main(String[] args) throws IOException {

        List<Map<String, String>> data =
                ExcelUtils.readExcel(
                    "src/test/resources/testdata/TestData.xlsx",
                    "TestData"
                );

        for (Map<String, String> row : data) {
            System.out.println(row);
        }
    }
}