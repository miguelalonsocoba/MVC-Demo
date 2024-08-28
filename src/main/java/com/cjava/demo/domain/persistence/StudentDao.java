package com.cjava.demo.domain.persistence;

import com.cjava.demo.domain.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<Student, Integer> {
}
