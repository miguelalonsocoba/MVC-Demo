package com.cjava.demo.controllers;

import com.cjava.demo.domain.entities.Student;
import com.cjava.demo.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/listStudents", method = RequestMethod.GET)
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.listAll());
        return "listStudentsView";
    }

    @RequestMapping(value = "/form_student")
    public String create(Map<String, Object> model) {
        Student student = new Student();
        model.put("student", student);
        return "form_student";
    }

    @RequestMapping(value = "/form_student/{id}")
    public String update(@PathVariable(value = "id") int id, Map<String, Object> model) {
        System.out.println("Id selected: " + id);
        Student student;
        if (id > 0) {
            student = studentService.search(id);
        } else {
            return "redirect:/listStudents";
        }
        System.out.println("Selected student: " + student.toString());
        model.put("student", student);
        return "form_student";
    }

    @RequestMapping(value = "/form_student", method = RequestMethod.POST)
    public String save(@Valid Student student, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "form_student";
        }
        studentService.save(student);
        status.setComplete();
        return "redirect:/listStudents";
    }

    @RequestMapping(value = "/delete_student/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        if (id > 0) {
            studentService.deleteById(id);
        }
        return "redirect:/listStudents";
    }

    @RequestMapping(value = "/view_students")
    public String viewStudent(Model model) {
        model.addAttribute("students", studentService.listAll());
        model.addAttribute("title", "List of students");
        return "/students/view";
    }
}
