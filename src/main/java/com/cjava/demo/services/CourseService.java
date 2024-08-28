package com.cjava.demo.services;

import com.cjava.demo.domain.entities.Course;

import java.util.List;

public interface CourseService {

    public void save(Course course);

    public void deleteById(int id);

    public Course search(Integer id);

    public List<Course> list();
}
