package com.cjava.demo.views.Course;

import com.cjava.demo.domain.entities.Course;
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

@Component("/course/view")
public class CoursePdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        PdfPTable table = new PdfPTable(1);
        table.setSpacingAfter(20);
        PdfPCell cell = null;
        cell = new PdfPCell(new Phrase((String) model.get("title")));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(3);
        table.addCell("Id");
        table.addCell("Name");
        table.addCell("Credits");
        List<Course> courses = (List<Course>)model.get("courses");

        for(Course course : courses) {
            table.addCell("" + course.getId());
            table.addCell(course.getName());
            table.addCell("" + course.getCredits());
        }
        document.add(table);
    }
}
