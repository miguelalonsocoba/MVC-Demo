package com.cjava.demo.domain.persistence;

import com.cjava.demo.domain.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<Course, Integer> {
}
