package com.empolyees.springboot.mvcdemo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import com.empolyees.springboot.mvcdemo.entity.Employee;
import com.empolyees.springboot.mvcdemo.service.EmployeeService;
import com.empolyees.springboot.mvcdemo.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/pdf")
public class PdfController {
    @Autowired
    PdfService pdfService;

    @Autowired
    private EmployeeService employeeService;
@GetMapping("/createPdf")
public ResponseEntity<InputStreamResource> createPdf() {
    ByteArrayInputStream pdf =pdfService.createPdf();
      System.out.println("pdf " + pdf);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline; filename=lcwd.pdf");
       //httpHeaders.add("Content-Disposition", "attachment; filename=testpdf.pdf");
       System.out.println("test controller " + pdf);
        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body( new InputStreamResource(pdf));

    }

    @GetMapping(value="/employeesPdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeeReport() throws IOException {
        List<Employee> employees = employeeService.findAll();
        ByteArrayInputStream byteArrayInputStream =pdfService.employeePDFReport(employees);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=employees.pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteArrayInputStream));
    }


}