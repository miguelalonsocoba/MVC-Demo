package com.cjava.demo.services.impl;

import com.cjava.demo.domain.entities.Course;
import com.cjava.demo.domain.persistence.CourseDao;
import com.cjava.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    @Transactional
    public void save(Course course) {
        courseDao.save(course);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        courseDao.deleteById(id);
    }

    @Override
    @Transactional
    public Course search(Integer id) {
        return courseDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> list() {
        return (List<Course>) courseDao.findAll();
    }
}
