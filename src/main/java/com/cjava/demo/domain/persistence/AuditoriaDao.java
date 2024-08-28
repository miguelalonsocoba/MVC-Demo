package com.cjava.demo.domain.persistence;

import com.cjava.demo.domain.entities.Audit;
import org.springframework.data.repository.CrudRepository;

public interface AuditoriaDao extends CrudRepository<Audit, Integer> {
}
