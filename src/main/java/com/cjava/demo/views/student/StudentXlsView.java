package com.cjava.demo.views.student;

import com.cjava.demo.domain.entities.Student;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.util.List;
import java.util.Map;

@Component("/students/view.xlsx")
public class StudentXlsView extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"student_view.xlsx\"");

        Sheet sheet = workbook.createSheet((String) model.get("title"));
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue((String) model.get("title"));

        row = sheet.createRow(1);
        cell = row.createCell(0);

        CellStyle tableHeaderStyle = workbook.createCellStyle();
        tableHeaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        tableHeaderStyle.setBorderTop(BorderStyle.MEDIUM);
        tableHeaderStyle.setBorderRight(BorderStyle.MEDIUM);
        tableHeaderStyle.setBorderLeft(BorderStyle.MEDIUM);
        tableHeaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        tableHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle tableBodyStyle = workbook.createCellStyle();
        tableBodyStyle.setBorderBottom(BorderStyle.THIN);
        tableBodyStyle.setBorderTop(BorderStyle.THIN);
        tableBodyStyle.setBorderRight(BorderStyle.THIN);
        tableBodyStyle.setBorderLeft(BorderStyle.THIN);

        Row header = sheet.createRow(3);
        header.createCell(0).setCellValue("Id");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Last Name");
        header.createCell(3).setCellValue("Date Of Birth");
        header.createCell(4).setCellValue("Gender");

        header.getCell(0).setCellStyle(tableHeaderStyle);
        header.getCell(1).setCellStyle(tableHeaderStyle);
        header.getCell(2).setCellStyle(tableHeaderStyle);
        header.getCell(3).setCellStyle(tableHeaderStyle);
        header.getCell(4).setCellStyle(tableHeaderStyle);

        List<Student> students = (List<Student>) model.get("students");
        int rowNum = 4;
        for (Student student : students) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(student.getId());
            cell.setCellStyle(tableBodyStyle);

            cell = row.createCell(1);
            cell.setCellValue(student.getName());
            cell.setCellStyle(tableBodyStyle);

            cell = row.createCell(2);
            cell.setCellValue(student.getLastName());
            cell.setCellStyle(tableBodyStyle);

            cell = row.createCell(3);
            cell.setCellValue(student.getDateOfBirth());
            cell.setCellStyle(tableBodyStyle);

            cell = row.createCell(4);
            cell.setCellValue(student.getGender());
            cell.setCellStyle(tableBodyStyle);
        }
    }
}
