package com.cjava.demo.controllers;

import com.cjava.demo.domain.entities.Course;
import com.cjava.demo.services.CourseService;
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
public class CourseController {

    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "main";
    }

    @RequestMapping(value = "/listCourses", method = RequestMethod.GET)
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.list());
        return "listCoursesView";
    }

    @RequestMapping(value = "/form")
    public String create(Map<String, Object> model) {
        Course course = new Course();
        model.put("course", course);
        return "form";
    }

    @RequestMapping(value = "/form/{id}")
    public String update(@PathVariable(value = "id") Integer id, Map<String, Object> model){
        System.out.println("Id selected: " + id);
        Course course;
        if (id > 0) {
            course = courseService.search(id);
        } else {
            return "redirect:/listCourses";
        }
        System.out.println("Selected course: " + course.toString());
        model.put("course", course);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String save(@Valid Course course, BindingResult result, Model model, SessionStatus status) {
        System.out.println("Curso a modificar: " + course.toString());
        if (result.hasErrors()) {
            return "form";
        }
        courseService.save(course);
        status.setComplete();
        return "redirect:/listCourses";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id){
        if (id > 0) {
            courseService.deleteById(id);
        }
        return "redirect:/listCourses";
    }

    @RequestMapping(value = "/view")
    public String view(Model model) {
        model.addAttribute("courses", courseService.list());
        model.addAttribute("title", "List of courses");
        return "/course/view";
    }

}
