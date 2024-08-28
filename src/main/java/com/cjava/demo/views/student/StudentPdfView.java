package com.cjava.demo.views.student;

import com.cjava.demo.domain.entities.Student;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("/students/view")
public class StudentPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PdfPTable table = new PdfPTable(1);
        table.setSpacingAfter(20);
        PdfPCell cell = new PdfPCell(new Phrase((String) model.get("title")));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(5);
        table.addCell("Id");
        table.addCell("Name");
        table.addCell("Last Name");
        table.addCell("Date Of Birth");
        table.addCell("Gender");

        List<Student> students = (List<Student>) model.get("students");
        for (Student student : students) {
            table.addCell("" + student.getId());
            table.addCell(student.getName());
            table.addCell(student.getLastName());
            table.addCell(student.getDateOfBirth());
            table.addCell(student.getGender());
        }
        document.add(table);
    }
}
