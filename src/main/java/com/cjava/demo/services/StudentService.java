package com.cjava.demo.services;

import com.cjava.demo.domain.entities.Student;

import java.util.List;

public interface StudentService {

    void save(Student student);

    void deleteById(int id);

    Student search(int id);

    List<Student> listAll();
}
