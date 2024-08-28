package com.cjava.demo.services.impl;

import com.cjava.demo.domain.entities.Student;
import com.cjava.demo.domain.persistence.StudentDao;
import com.cjava.demo.services.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    @Override
    @Transactional
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        studentDao.deleteById(id);
    }

    @Override
    @Transactional
    public Student search(int id) {
        return studentDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> listAll() {
        return (List<Student>) studentDao.findAll();
    }
}
