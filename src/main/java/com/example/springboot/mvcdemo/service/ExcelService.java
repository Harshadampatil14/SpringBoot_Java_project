package com.example.springboot.mvcdemo.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.example.springboot.mvcdemo.dao.EmployeeRepository;
import com.example.springboot.mvcdemo.entity.Employee;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {
    @Autowired
    EmployeeRepository repository;

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "First Name", "Last Name", "Email" };
    static String SHEET = "Employees";

    public ByteArrayInputStream employeeExcelReport() {
        List<Employee> employees = repository.findAll();

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ) {
            //Create sheet for workbook
            Sheet sheet = workbook.createSheet(SHEET);

            // create first Header Row indicating it by parameter 0
            Row headerRow = sheet.createRow(0);
            // Create 4 columns for the Header row
            for (int col = 0; col < HEADERs.length; col++) {
                headerRow.createCell(col).setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Employee emp : employees) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(emp.getId());
                row.createCell(1).setCellValue(emp.getFirstName());
                row.createCell(2).setCellValue(emp.getLastName());
                row.createCell(3).setCellValue(emp.getEmail());
            }
            // Write workbook to byteArrayOutputStream object
            workbook.write(byteArrayOutputStream);
            //convert it into ByteArrayInputStream
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
