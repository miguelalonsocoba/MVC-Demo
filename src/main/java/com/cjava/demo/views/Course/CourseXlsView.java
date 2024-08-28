package com.cjava.demo.views.Course;

import com.cjava.demo.domain.entities.Course;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.util.List;
import java.util.Map;

@Component("/course/view.xlsx")
public class CourseXlsView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"curso_view.xlsx\"");
        List<Course> courses = (List<Course>)model.get("courses");
        Sheet sheet = workbook.createSheet((String) model.get("title"));

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue((String) model.get("title"));

        CellStyle theaderStyle = workbook.createCellStyle();
        theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        theaderStyle.setBorderTop(BorderStyle.MEDIUM);
        theaderStyle.setBorderRight(BorderStyle.MEDIUM);
        theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
        theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle tbodyStyle = workbook.createCellStyle();
        tbodyStyle.setBorderBottom(BorderStyle.THIN);
        tbodyStyle.setBorderTop(BorderStyle.THIN);
        tbodyStyle.setBorderRight(BorderStyle.THIN);
        tbodyStyle.setBorderLeft(BorderStyle.THIN);

        Row header = sheet.createRow(4);
        header.createCell(0).setCellValue("id");
        header.createCell(1).setCellValue("name");
        header.createCell(2).setCellValue("credits");

        header.getCell(0).setCellStyle(theaderStyle);
        header.getCell(1).setCellStyle(theaderStyle);
        header.getCell(2).setCellStyle(theaderStyle);

        int rownum = 5;

        for(Course course: courses) {
            Row fila = sheet.createRow(rownum ++);
            cell = fila.createCell(0);
            cell.setCellValue(course.getId());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(1);
            cell.setCellValue(course.getName());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(2);
            cell.setCellValue(course.getCredits());
            cell.setCellStyle(tbodyStyle);
        }



    }

}
