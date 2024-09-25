package com.empolyees.springboot.mvcdemo.service;

import com.empolyees.springboot.mvcdemo.entity.Employee;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfService {
    private Logger logger = LoggerFactory.getLogger(PdfService.class);
    public ByteArrayInputStream createPdf() {
        logger.info("Create PDF Started..");
        String title = "Welcome to Learn Code with Harshada";
        String content = "We provide technical content. Please do suscribe.. ";

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,out);
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20);
        Paragraph titleParagraph = new Paragraph(title, fontTitle);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(titleParagraph);

        Font fontContent = FontFactory.getFont(FontFactory.HELVETICA,18);
        Paragraph contentPparagraph = new Paragraph(content, fontContent);
        contentPparagraph.add( new Chunk("Wow this text is added after creating.."));
        document.add(contentPparagraph);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());

    }

   public ByteArrayInputStream employeePDFReport(List <Employee> employees) {
        Document document = new Document();
       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       try {
           PdfWriter.getInstance(document, byteArrayOutputStream);
           document.open();

           Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD,22);
           Paragraph paragraph = new Paragraph("Employee Details", fontHeader);
           paragraph.setAlignment(Element.ALIGN_CENTER);
           document.add(paragraph);
           document.add(Chunk.NEWLINE);

           PdfPTable table = new PdfPTable(4);
           Stream.of("ID", "FIRST NAME", "LAST NAME", "EMAIL").forEach(headerTitle->{
               PdfPCell headerCell = new PdfPCell();
               Font headfont = FontFactory.getFont(FontFactory.TIMES_BOLD);
               headerCell.setBackgroundColor(RGBColor.CYAN);
               headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
               headerCell.setBorderWidth(2);
               headerCell.setPhrase(new Phrase(headerTitle, headfont));
               table.addCell(headerCell);
           });
           for (Employee employee : employees) {
               PdfPCell idCell = new PdfPCell();
               idCell.setPhrase(new Phrase("" + employee.getId()));
               idCell.setPaddingLeft(4);
               idCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
               idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(idCell);
               System.out.println(employee.getId());

               PdfPCell firstNameCell = new PdfPCell();
               firstNameCell.setPhrase(new Phrase(employee.getFirstName()));
               firstNameCell.setPaddingLeft(4);
               firstNameCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
               firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(firstNameCell);
               System.out.println(employee.getFirstName());

               PdfPCell lastNameCell = new PdfPCell();
               lastNameCell.setPhrase(new Phrase(employee.getLastName()));
               lastNameCell.setPaddingLeft(4);
               lastNameCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
               lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(lastNameCell);
               System.out.println(employee.getLastName());

               PdfPCell emailCell = new PdfPCell();
               emailCell.setPhrase(new Phrase(employee.getEmail()));
               emailCell.setPaddingLeft(4);
               emailCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
               emailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(emailCell);
               System.out.println(employee.getEmail());
           }
           document.add(table);
           document.close();
       } catch (Exception e) {
          e.printStackTrace();
       }
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
   }
}
